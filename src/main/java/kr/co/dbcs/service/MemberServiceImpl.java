package kr.co.dbcs.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.dbcs.mapper.MemberMapper;
import kr.co.dbcs.model.CustomUser;
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

import java.util.List;
import java.util.Map;

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
        return null;
    }

    @Override
    public boolean update(MemberVO memberVO) {
        return false;
    }

    @Override
    public boolean delete(String username) {
        return false;
    }

    @Override
    public void crawl() {
        String url = "https://www.megabox.co.kr/on/oh/ohc/Brch/schedulePage.do?masterType=brch&detailType=area&brchNo=1372&firstAt=N&brchNo1=1372&crtDe=20230908&playDe=20230908";

        try {
            RestTemplate restTemplate = new RestTemplate();

            // POST 요청 전송 및 응답을 Map으로 Parse
            Map<String, Object> response = restTemplate.postForObject(url, null, Map.class);

            // 응답으로부터 movieFormList 얻기
            List<Map<String, Object>> movieFormList = (List<Map<String, Object>>) ((Map<String, Object>) response.get("megaMap")).get("movieFormList");

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // ObjectMapper가 알 수 없는 속성을 만났을 때 실패하지 않고 무시

            for (Map<String, Object> movieData : movieFormList) {
                // map을 Movie 객체로 변환
                MovieVO movie = mapper.convertValue(movieData, MovieVO.class);

                log.info(String.format("%s [%s] (%d/%d)",
                        StringEscapeUtils.unescapeHtml4(movie.getMovieName()),
                        movie.getPlayStartTime(),
                        movie.getRestSeatCnt(),
                        movie.getTotSeatCnt()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
