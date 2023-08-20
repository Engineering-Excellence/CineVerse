package kr.co.dbcs.service;

import kr.co.dbcs.config.RootConfig;
import kr.co.dbcs.config.SecurityConfig;
import kr.co.dbcs.mapper.MemberMapper;
import kr.co.dbcs.model.CustomUser;
import kr.co.dbcs.model.MemberVO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
@RequiredArgsConstructor
class MemberServiceImplTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    void loadUserByUsername() {
        String testUsername = "admin";
        UserDetails userDetails = loadUserByUsername(testUsername);
        assertNotNull(userDetails);
        assertEquals(testUsername, userDetails.getUsername());
    }

    @Test
    void read() {
        String testUsername = "admin";
        MemberVO member = read(testUsername);
        assertNotNull(member);
        assertEquals(testUsername, member.getUsername());
    }

    private UserDetails loadUserByUsername(String username) {
        MemberVO memberVO = memberMapper.selectMemberByUsername(username);
        return memberVO == null ? null : new CustomUser(memberVO);
    }

    private MemberVO read(String username) {
        return ((CustomUser) loadUserByUsername(username)).getMemberVO();
    }
}
