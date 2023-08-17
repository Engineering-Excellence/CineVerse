package kr.co.dbcs.model;

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
@NoArgsConstructor
public class MemberVO {

    private String username;    // PK
    private String password;
    private boolean enabled;
    private String mobile;
    private String email;
    private boolean gender;
    private Date birthDate;
    private Date regDate;
    private List<AuthVO> authList;

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
