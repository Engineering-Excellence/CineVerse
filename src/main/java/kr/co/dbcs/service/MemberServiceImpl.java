package kr.co.dbcs.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.dbcs.mapper.MemberMapper;
import kr.co.dbcs.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.text.StringEscapeUtils;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

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
        return memberMapper.insertMember(memberVO) >= 1 && memberMapper.insertAuth(memberVO.getUsername()) >= 1;
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
    public List<MovieVO> crawl(HashMap<String, String> map) {
//        String movieNm = "오펜하이머";
        String brchNo1 = map.get("theaterNo");    // 강남
        String playDe = map.get("date");
//        String playDe = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String url = "https://www.megabox.co.kr/on/oh/ohc/Brch/schedulePage.do?masterType=brch&detailType=area&firstAt=N&brchNo1=" + brchNo1 + "&playDe=" + playDe;

        try {
            RestTemplate restTemplate = new RestTemplate();

            // POST 요청 전송 및 응답을 Map으로 Parse
            Map<String, Object> response = restTemplate.postForObject(url, null, Map.class);

            // 응답으로부터 movieFormList 얻기
            assert response != null;
            List<Map<String, Object>> movieFormList = (List<Map<String, Object>>) ((Map<String, Object>) response.get("megaMap")).get("movieFormList");

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // ObjectMapper가 알 수 없는 속성을 만났을 때 실패하지 않고 무시

            List<MovieVO> movieList = new Vector<>();
            for (Map<String, Object> movieData : movieFormList) {
                // Map을 Movie 객체로 변환
                MegaboxVO movie = mapper.convertValue(movieData, MegaboxVO.class);

                log.info(String.format("%s - %s%s [%s] (%d/%d)",
                        StringEscapeUtils.unescapeHtml4(movie.getMovieNm()),
                        movie.getBrchNm(),
                        movie.getTheabExpoNm(),
                        movie.getPlayStartTime(),
                        movie.getRestSeatCnt(),
                        movie.getTotSeatCnt()));
                movieList.add(new MovieVO(movie));
            }
            return movieList;
        } catch (Exception e) {
            log.error(e);
        }
        return Collections.emptyList();
    }

    public String getUploadDirectory() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:/");
        Path path = Paths.get(resource.getURI());
        Path parentPath = path.getParent();
        return parentPath.toString() + File.separator + "member";
    }

    @Override
//    @Transactional
    public boolean uploadFile(MultipartFile file, Principal principal) {

        File uploadDirectory;
        boolean result = false;
        try {
            uploadDirectory = new File(getUploadDirectory());
            log.info("uploadDirectory: {}", getUploadDirectory());
            if (!uploadDirectory.exists()) {    // 업로드 디렉토리가 존재하지 않을 경우 생성
                log.info("mkdir: {}", uploadDirectory.mkdir());
            }

            String username = principal.getName();
            String originalFilename = file.getOriginalFilename();
            String realFilename = username + "_" + originalFilename;
            String absPath = getUploadDirectory() + File.separator + realFilename; // username을 파일명 앞에 추가
            String relPath = File.separator + "member" + File.separator + realFilename; // 상대경로

            // DB에 파일정보 저장
            MemberImgVO memberImgVO = new MemberImgVO(username, absPath, relPath, originalFilename);
//            result = memberMapper.saveImg(memberImgVO) > 0;
//            log.info("result: {}", result);

            // 서버에 파일 저장
//            if (result) {
                Path path = Paths.get(absPath);
                file.transferTo(path);
//            }
        } catch (IOException e) {
            log.error(e);
        }
        return result;
    }
}
