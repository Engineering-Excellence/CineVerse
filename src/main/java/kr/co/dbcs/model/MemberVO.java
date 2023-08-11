package kr.co.dbcs.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberVO {

    private String username;
    private String password;
    private boolean enabled;
    private Date regDate;
    private Date updDate;
    private List<AuthVO> authList;
}
