package kr.co.dbcs.service;

import kr.co.dbcs.mapper.MemberMapper;
import kr.co.dbcs.model.CustomUser;
import kr.co.dbcs.model.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MemberVO memberVO = memberMapper.selectMemberByUsername(username);
        return memberVO == null ? null : new CustomUser(memberVO);
    }
}
