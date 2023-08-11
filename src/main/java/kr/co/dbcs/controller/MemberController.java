package kr.co.dbcs.controller;

import kr.co.dbcs.model.AuthVO;
import kr.co.dbcs.model.MemberVO;
import kr.co.dbcs.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm() {
        return "/member/join";
    }

    @PostMapping("/join")
    public String joinSubmit(@RequestParam Map<String, String> map) {

        log.info("회원가입 {}", memberService.insertMember(map) ? "성공" : "실패");
        return "redirect:/login";
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public String deleteMember(@ModelAttribute(value = "memberVO") AuthVO authVO) {

        log.info("회원탈퇴 {}", memberService.delete(authVO.getUsername()) ? "성공" : "실패");
        return "redirect:/login";
    }

    @GetMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public String updateForm(Model model, Principal principal) {
        model.addAttribute("data", memberService.read(principal.getName()));
        return "/member/update";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public String updateSubmit(@ModelAttribute(value = "memberVO") MemberVO memberVO) {

        log.info("회원수정 {}", memberService.update(memberVO) ? "성공" : "실패");
        return "redirect:/login";
    }
}
