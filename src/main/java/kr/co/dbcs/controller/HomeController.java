package kr.co.dbcs.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping(value = "")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HomeController {

    @GetMapping(value = "/login")
    public String index() {
        return "/login";
    }

    @GetMapping(value = "/")
    public String home(Authentication authentication) {

//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//
//        // 권한에 따른 페이지 분기
//        if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
//            return "/admin/home";
//        } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
//            memberService.crawl();
//            return "/member/home";
//        } else {
//            return "/error";
//        }

        return "/index";
    }
}
