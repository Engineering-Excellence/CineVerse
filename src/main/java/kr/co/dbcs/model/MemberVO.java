package kr.co.dbcs.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Data
@Component
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberVO {

    private String username;    // 계정, PK
    private String password;    // 비밀번호
    private boolean enabled;    // 활성여부
    private String mobile;  // 휴대전화
    private String email;   // 이메일
    private boolean gender; // 성별
    private Date birthDate; // 생년월일
    private Date regDate;   // 가입일
    private List<AuthVO> authList;  // 권한

    @SneakyThrows(ParseException.class)
    public MemberVO(String username, String password, String mobile, String email, boolean gender, String birthDate) {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
        this.gender = gender;
        this.birthDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(birthDate).getTime());
    }
}
