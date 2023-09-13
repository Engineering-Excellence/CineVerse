package kr.co.dbcs.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.dbcs.mapper.MemberMapper;
import kr.co.dbcs.model.CustomUser;
import kr.co.dbcs.model.MegaboxVO;
import kr.co.dbcs.model.MemberVO;
import kr.co.dbcs.model.MovieVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

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
        List<MemberVO> ret = memberMapper.selectAllMember();
        return ret;
    }

    @Override
    @Transactional
    public boolean update(MemberVO memberVO) {//회원 정보 수정
        return memberMapper.updateMemberInfo(memberVO) >= 1;
    }

    public boolean updatePassword(HashMap<String, Object> map, MemberVO vo) {
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
    public List<MovieVO> crawl() {

//        String movieNm = "오펜하이머";
        String brchNo1 = "1372";    // 강남
        String playDe = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String url = "https://www.megabox.co.kr/on/oh/ohc/Brch/schedulePage.do?masterType=brch&detailType=area&firstAt=N&brchNo1=" + brchNo1 + "&playDe=" + playDe;

        try {
            RestTemplate restTemplate = new RestTemplate();

            // POST 요청 전송 및 응답을 Map으로 Parse
            Map<String, Object> response = restTemplate.postForObject(url, null, Map.class);

            // 응답으로부터 movieFormList 얻기
            List<Map<String, Object>> movieFormList = (List<Map<String, Object>>) ((Map<String, Object>) response.get("megaMap")).get("movieFormList");

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // ObjectMapper가 알 수 없는 속성을 만났을 때 실패하지 않고 무시

            List<MovieVO> movieList = new Vector<>();
            for (Map<String, Object> movieData : movieFormList) {
                // Map을 Movie 객체로 변환
                MegaboxVO movie = mapper.convertValue(movieData, MegaboxVO.class);

                // 영화 제목 확인 후 원하는 제목이 아니면 건너뜀
                /*if (!StringEscapeUtils.unescapeHtml4(movie.getMovieNm()).equals(movieNm)) {
                    continue;
                }*/

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
}
