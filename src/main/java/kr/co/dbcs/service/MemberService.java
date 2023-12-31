package kr.co.dbcs.service;

import kr.co.dbcs.model.MemberVO;
import kr.co.dbcs.model.MovieVO;
import kr.co.dbcs.model.PwdResetQueueVO;
import lombok.SneakyThrows;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface MemberService extends UserDetailsService, CRUDService<MemberVO, String> {

    boolean uploadProfile(MultipartFile file, String username);

    boolean deleteProfile(String username);

    @SneakyThrows
    @Transactional
    List<MovieVO> crawl(@NonNull Map<String, Object> paramsMap);

    String getUploadDirectory();

    String getRelPath(String username);

    boolean updatePassword(Map<String, Object> map, MemberVO vo);

    boolean deleteUserByPasswordChk(String username, String password, MemberVO vo);

	boolean findPwCheck(MemberVO memberVO);

	boolean insertPwdResetQueue(PwdResetQueueVO pwdResetQueueVO);

	boolean selectPwdResetQueue(Map<String, String> map);

	boolean updatePasswordByEmail(MemberVO vo);

	boolean deletePwdResetQueue(Map<String, String> map);

    List<String> getUsernameList(String username);
}
