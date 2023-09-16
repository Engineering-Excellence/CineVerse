package kr.co.dbcs.service;

import kr.co.dbcs.model.MemberVO;
import kr.co.dbcs.model.MovieVO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface MemberService extends UserDetailsService, CRUDService<MemberVO, String> {

    List<MovieVO> crawl(Map<String, String> map);

    boolean uploadProfile(MultipartFile file, String username);

    boolean deleteProfile(String username);

    String getUploadDirectory();

    String getRelPath(String username);

    boolean updatePassword(Map<String, Object> map, MemberVO vo);

    boolean deleteUserByPasswordChk(String username, String password, MemberVO vo);
}
