package kr.co.dbcs.service;

import kr.co.dbcs.config.RootConfig;
import kr.co.dbcs.config.SecurityConfig;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class MemberServiceImplTest {

    private final MemberService memberService;

    @Test
    void loadUserByUsername() {
        String testUsername = "admin";
        UserDetails userDetails = memberService.loadUserByUsername(testUsername);
        assertNotNull(userDetails);
        assertEquals(testUsername, userDetails.getUsername());
    }

    @Test
    void read() {
        String testUsername = "user";
        MemberVO memberVO = memberService.read(testUsername);
        assertNotNull(memberVO);
        assertEquals(testUsername, memberVO.getUsername());
    }
}
