package kr.co.dbcs.service;

import java.util.HashMap;

import org.springframework.security.core.userdetails.UserDetailsService;

import kr.co.dbcs.model.MemberVO;

public interface MemberService extends UserDetailsService, CRUDService<MemberVO, String> {

    void crawl();
    
    boolean updatePassword(HashMap<String, Object> map, MemberVO vo);

	boolean deleteUserByPasswordChk(String username, String password, MemberVO vo);
}
