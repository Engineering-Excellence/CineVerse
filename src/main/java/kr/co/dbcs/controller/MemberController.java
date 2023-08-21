package kr.co.dbcs.controller;

import kr.co.dbcs.model.MemberVO;
import kr.co.dbcs.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/{path1}")
    public String handlePath1() {
        return "/home";
    }

    @GetMapping(value = "/{path1}/{path2}")
    public String handlePath2() {
        return "/home";
    }

    @PostMapping(value = "/join")
    public String joinSubmit(@ModelAttribute(value = "memberVO") MemberVO memberVO) {
        log.info("회원가입 {}", memberService.create(memberVO) ? "성공" : "실패");
        return "redirect:/login";
    }

    @PostMapping(value = "/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public String deleteMember(@ModelAttribute(value = "memberVO") MemberVO memberVO) {
        log.info("회원탈퇴 {}", memberService.delete(memberVO.getUsername()) ? "성공" : "실패");
        return "redirect:/login";
    }

    @PostMapping(value = "/update")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public String updateSubmit(@ModelAttribute(value = "memberVO") MemberVO memberVO) {
        log.info("회원수정 {}", memberService.update(memberVO) ? "성공" : "실패");
        return "redirect:/";
    }
}
