package kr.co.dbcs.service;

import kr.co.dbcs.model.MemberVO;
import kr.co.dbcs.model.MovieVO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MemberService extends UserDetailsService, CRUDService<MemberVO, String> {

    List<MovieVO> crawl(HashMap<String, String> map);

    boolean uploadFile(MultipartFile file, Principal principal);

    boolean updatePassword(Map<String, Object> map, MemberVO vo);

    boolean deleteUserByPasswordChk(String username, String password, MemberVO vo);
}
