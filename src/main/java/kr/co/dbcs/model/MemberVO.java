package kr.co.dbcs.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class MemberVO {
    private String username, password, authorities;
    private boolean enabled;
    private Date regdate;

}
