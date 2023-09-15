package kr.co.dbcs.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.dbcs.mapper.MemberMapper;
import kr.co.dbcs.model.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final ResourceLoader resourceLoader;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO memberVO = memberMapper.selectMemberByUsername(username);
        return memberVO == null ? null : new CustomUser(memberVO);
    }

    @Override
    @Transactional
    public boolean create(MemberVO memberVO) {
        memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
        return memberMapper.insertMember(memberVO) >= 1
                && memberMapper.insertAuth(memberVO.getUsername()) >= 1
                && memberMapper.insertMemberImg(memberVO.getUsername()) >= 1;
    }

    @Override
    public MemberVO read(String username) {
        return ((CustomUser) loadUserByUsername(username)).getMemberVO();
    }

    @Override
    public List<MemberVO> readAll() {
        return memberMapper.selectAllMember();
    }

    @Override
    @Transactional
    public boolean update(MemberVO memberVO) {//회원 정보 수정
        return memberMapper.updateMemberInfo(memberVO) >= 1;
    }

    public boolean updatePassword(Map<String, Object> map, MemberVO vo) {

        if (!passwordEncoder.matches(map.get("oldPassword").toString(), vo.getPassword())) {
            return false;
        }
        MemberVO memberVO = new MemberVO();
        memberVO.setUsername(vo.getUsername());
        memberVO.setPassword(passwordEncoder.encode(map.get("newPassword").toString()));
        return memberMapper.updatePassword(memberVO) > 0;
    }

    @Override
    public boolean delete(String id) {
        return memberMapper.deleteMember(id) > 0;
    }

    @Override
    public boolean deleteUserByPasswordChk(String username, String password, MemberVO vo) {
        if (!passwordEncoder.matches(password, vo.getPassword())) return false;
        return delete(username);
    }

    @Override
    @Transactional
    public List<MovieVO> crawl(Map<String, String> map) {

        String brchNo1 = map.get("theaterNo");    // 지점
        String playDe = map.get("date");    // 상영일
        String url = "https://www.megabox.co.kr/on/oh/ohc/Brch/schedulePage.do?masterType=brch&detailType=area&firstAt=N&brchNo1=" + brchNo1 + "&playDe=" + playDe;

        RestTemplate restTemplate = new RestTemplate();

        // POST 요청 전송 및 응답을 Map으로 Parse
        Map<String, Object> response = restTemplate.postForObject(url, null, Map.class);

        // 응답으로부터 movieFormList 얻기
        assert response != null;
        List<Map<String, Object>> movieFormList = (List<Map<String, Object>>) ((Map<String, Object>) response.get("megaMap")).get("movieFormList");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // ObjectMapper가 알 수 없는 속성을 만났을 때 실패하지 않고 무시

        List<MovieVO> movieList = new ArrayList<>();
        for (Map<String, Object> movieData : movieFormList) {
            MegaboxVO movie = mapper.convertValue(movieData, MegaboxVO.class);
            movieList.add(new MovieVO(movie));
        }
        return movieList;
    }

    @Override
    @SneakyThrows(IOException.class)
    public String getUploadDirectory() {
        Resource resource = resourceLoader.getResource("classpath:/");
        Path path = Paths.get(resource.getURI());
        Path parentPath = path.getParent();
        return parentPath.toString() + File.separator + "images" + File.separator + "profile";
    }

    @Override
    public String getRelPath(String username) {
        return memberMapper.getRelPath(username);
    }

    @Override
    @Transactional
    @SneakyThrows(IOException.class)
    public boolean uploadFile(MultipartFile file, Principal principal) {

        File uploadDirectory;
        uploadDirectory = new File(getUploadDirectory());
        log.info("uploadDirectory: {}", getUploadDirectory());
        if (!uploadDirectory.exists()) {    // 업로드 디렉토리가 존재하지 않을 경우 생성
            log.info("mkdir: {}", uploadDirectory.mkdir());
        }

        String username = principal.getName();
        String originalFilename = file.getOriginalFilename();
        String realFilename = username + "_" + originalFilename;
        String absPath = getUploadDirectory() + File.separator + realFilename;
        String relPath = File.separator + "images" + File.separator + "profile" + File.separator + realFilename;

        // 기존에 존재하는 파일 삭제
        DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(getUploadDirectory()), username + "_*.*");
        for (Path entry : stream) {
            Files.delete(entry);
        }
        stream.close();

        // DB에 파일정보 저장
        MemberImgVO memberImgVO = new MemberImgVO(username, absPath, relPath, originalFilename);
        boolean result = memberMapper.saveImg(memberImgVO) > 0;
        log.info("result: {}", result);

        // 서버에 파일 저장
        if (result) {
            Path path = Paths.get(absPath);
            file.transferTo(path);
        }

        return result;
    }
}
