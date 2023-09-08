package kr.co.dbcs.service;

import kr.co.dbcs.model.MemberVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService, CRUDService<MemberVO, String> {

    void crawl();
}
