package kr.co.dbcs.service;

import kr.co.dbcs.model.MemberVO;
import kr.co.dbcs.model.MovieVO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashMap;
import java.util.List;

public interface MemberService extends UserDetailsService, CRUDService<MemberVO, String> {

    List<MovieVO> crawl();

    boolean updatePassword(HashMap<String, Object> map, MemberVO vo);

    boolean deleteUserByPasswordChk(String username, String password, MemberVO vo);
}
