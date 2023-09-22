package kr.co.dbcs.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.dbcs.mapper.MemberMapper;
import kr.co.dbcs.mapper.PwdResetQueueMapper;
import kr.co.dbcs.model.CustomUser;
import kr.co.dbcs.model.LotteCinemaVO;
import kr.co.dbcs.model.MegaboxVO;
import kr.co.dbcs.model.MemberImgVO;
import kr.co.dbcs.model.MemberVO;
import kr.co.dbcs.model.MovieVO;
import kr.co.dbcs.model.PwdResetQueueVO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final PwdResetQueueMapper pwdResetQueueMapper;
    private final PasswordEncoder passwordEncoder;
    private final ResourceLoader resourceLoader;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO memberVO = memberMapper.selectMemberByUsername(username);
        return memberVO == null ? null : new CustomUser(memberVO);
    }

    @Override
    @Transactional
    public boolean create(@NonNull MemberVO memberVO) {
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
    public boolean update(@NonNull MemberVO memberVO) {//회원 정보 수정
        return memberMapper.updateMemberInfo(memberVO) >= 1;
    }

    public boolean updatePassword(@NonNull Map<String, Object> map, @NonNull MemberVO vo) {

        if (!passwordEncoder.matches(map.get("oldPassword").toString(), vo.getPassword())) {
            return false;
        }
        MemberVO memberVO = new MemberVO();
        memberVO.setUsername(vo.getUsername());
        memberVO.setPassword(passwordEncoder.encode(map.get("newPassword").toString()));
        return memberMapper.updatePassword(memberVO) > 0;
    }

    
    @Override
    public boolean findPwCheck(@NonNull MemberVO memberVO) {
    	return memberMapper.findPwCheck(memberVO) >= 1;
    }

    @Override
    public boolean delete(String id) {
        return memberMapper.deleteMember(id) > 0;
    }
    
    @Override
    public boolean insertPwdResetQueue(@NonNull PwdResetQueueVO pwdResetQueueVO) {
    	return pwdResetQueueMapper.insertPwdResetQueue(pwdResetQueueVO) >= 1;
    }
    
    public boolean selectPwdResetQueue(Map<String, String> map) {
    	return pwdResetQueueMapper.selectPwdResetQueue(map) >= 1;
    }

    @Override
    public boolean deleteUserByPasswordChk(String username, String password, @NonNull MemberVO vo) {
        if (!passwordEncoder.matches(password, vo.getPassword())) return false;
        return delete(username);
    }

    @Override
    @Transactional
    @SneakyThrows(Exception.class)
    public List<MovieVO> crawl(@NonNull Map<String, Object> paramsMap) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.6 Safari/605.1.15");

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // ObjectMapper가 알 수 없는 속성을 만났을 때 실패하지 않고 무시

        List<MovieVO> movieList = new ArrayList<>();

        for (String theaterNo : (List<String>) paramsMap.get("megabox")) {
            // 메가박스 크롤링
            String brchNo1 = theaterNo;   // 지점
            String playDe = paramsMap.get("date").toString();    // 상영일
            String url = "https://www.megabox.co.kr/on/oh/ohc/Brch/schedulePage.do?masterType=brch&detailType=area&firstAt=N&brchNo1=" + brchNo1 + "&playDe=" + playDe;

            // GET 요청 전송 및 응답을 Map으로 Parse
            HttpEntity<String> request_megabox = new HttpEntity<>("parameters", headers);
            ResponseEntity<Map> response_megabox = restTemplate.exchange(url, HttpMethod.GET, request_megabox, Map.class);

            // 응답으로부터 movieFormList 얻기
            Map<String, Object> response_megabox_map = response_megabox.getBody();
            List<Map<String, Object>> movieFormList_megabox = (List<Map<String, Object>>) ((Map<String, Object>) response_megabox_map.get("megaMap")).get("movieFormList");

            for (Map<String, Object> movieData : movieFormList_megabox) {
                MegaboxVO movie = mapper.convertValue(movieData, MegaboxVO.class);
                movieList.add(new MovieVO(movie));
            }
        }

        for (String theaterNo : (List<String>) paramsMap.get("lottecinema")) {
            // 롯데시네마 크롤링
            JSONObject obj = new JSONObject();
            obj.put("MethodName", "GetPlaySequence");
            obj.put("channelType", "MA");
            obj.put("osVersion", "");
            obj.put("osType", "");
            obj.put("cinemaID", "1|1|" + theaterNo);
            obj.put("representationMovieCode", "");
            obj.put("playDate", paramsMap.get("date").toString().replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$1-$2-$3"));

            String url = "https://www.lottecinema.co.kr/LCWS/Ticketing/TicketingData.aspx";

            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("paramList", obj.toJSONString());

            HttpEntity<MultiValueMap<String, String>> request_lotte = new HttpEntity<>(map, headers);

            ResponseEntity<String> response_lotte = restTemplate.postForEntity(url, request_lotte, String.class);

            JSONParser parser = new JSONParser();
            JSONObject res = (JSONObject) parser.parse(response_lotte.getBody());
            Map<String, Object> ret = new ObjectMapper().readValue(res.get("PlaySeqs").toString(), Map.class);

            List<Map<String, Object>> movieFormList_lotte = (List<Map<String, Object>>) ret.get("Items");

            for (Map<String, Object> movieData : movieFormList_lotte) {
                LotteCinemaVO movie = mapper.convertValue(movieData, LotteCinemaVO.class);
                movieList.add(new MovieVO(movie));
            }
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
    public boolean uploadProfile(MultipartFile file, String username) {

        File uploadDirectory;
        uploadDirectory = new File(getUploadDirectory());
        if (!uploadDirectory.exists()) {    // 업로드 디렉토리가 존재하지 않을 경우 생성
            log.info("mkdir: {}", uploadDirectory.mkdir());
        }

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

    @Override
    @Transactional
    @SneakyThrows(IOException.class)
    public boolean deleteProfile(String username) {

        // 기존에 존재하는 파일 삭제
        DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(getUploadDirectory()), username + "_*.*");
        for (Path entry : stream) {
            Files.delete(entry);
        }
        stream.close();

        // DB 파일정보 삭제
        return memberMapper.deleteImg(username) > 0;
    }

    @Override
    public List<String> getUsernameList() {
        return memberMapper.getUsernameList();
    }

	@Override
	public boolean updatePasswordByEmail(MemberVO vo) {
		vo.setPassword(passwordEncoder.encode(vo.getPassword()));
		return memberMapper.updatePassword(vo) >= 1;
	}

	@Override
	public boolean deletePwdResetQueue(Map<String, String> map) {
		return pwdResetQueueMapper.deletePwdResetQueue(map) >= 1;
	}
  
    
}
