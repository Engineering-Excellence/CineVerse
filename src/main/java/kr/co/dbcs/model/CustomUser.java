package kr.co.dbcs.model;

import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.stream.Collectors;

@Getter
public class CustomUser extends User {

    private static final long serialVersionUID = -2920740600930986186L;

    private MemberVO memberVO;

/*
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
*/

    public CustomUser(@NonNull MemberVO memberVO) {
        super(memberVO.getUsername(), memberVO.getPassword(), memberVO.getAuthList().stream().map(authVO -> new SimpleGrantedAuthority(authVO.getAuthority())).collect(Collectors.toList()));
        this.memberVO = memberVO;
    }
}
