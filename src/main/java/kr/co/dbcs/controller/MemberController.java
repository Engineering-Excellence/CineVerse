package kr.co.dbcs.controller;

import kr.co.dbcs.model.MemberVO;
import kr.co.dbcs.service.BoardService;
import kr.co.dbcs.service.MemberService;
import kr.co.dbcs.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberController {

    private final MemberService memberService;
    private final BoardService boardService;
    private final ReplyService replyService;

    @GetMapping(value = "/{path}")
    public String handlePath(@PathVariable @NonNull String path, Model model, @NonNull Principal principal) {

        switch (path) {
            case "chat":
                return "/member/chat";
            case "update":
                model.addAttribute("data", memberService.read(principal.getName()));
                model.addAttribute("board", boardService.readByUsername(principal.getName()));
                model.addAttribute("reply", replyService.readByUsername(principal.getName()));
                model.addAttribute("relPath", memberService.getRelPath(principal.getName()));
                break;
            default:
                break;
        }

        return "/home";
    }

    @PostMapping(value = "/join")
    public String joinSubmit(@ModelAttribute(value = "memberVO") MemberVO memberVO) {
        log.info("회원가입 {}", memberService.create(memberVO) ? "성공" : "실패");
        return "redirect:/login";
    }

    @ResponseBody
    @PostMapping("/check")
    public boolean idCheck(@RequestBody @NonNull MemberVO memberVO) {
        return memberService.loadUserByUsername(memberVO.getUsername()) != null;
    }

    @PostMapping(value = "/delete")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public String deleteMember(@ModelAttribute(value = "memberVO") @NonNull MemberVO memberVO, @NonNull Principal principal) {
		MemberVO vo = memberService.read(principal.getName()); //암호화된 비밀번호를 담기 위한 VO 선언
		memberVO.setUsername(principal.getName()); //그냥 정보를 가져오기위해 사용한 VO에서 setusername을 가져온다.
		if (memberService.deleteUserByPasswordChk(principal.getName(), memberVO.getPassword(), vo)) {
			return "redirect:/login";
		}
		else {
			return "/member/update";
		}
	}

    @PostMapping(value = "/update")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public String updateSubmit(@ModelAttribute(value = "memberVO") MemberVO memberVO) {

        log.info("회원수정 {}", memberService.update(memberVO) ? "성공" : "실패");
        return "redirect:/";
    }

    @PostMapping(value = "/updatePassword")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public String updatePassword(@RequestParam Map<String, Object> map, @NonNull Principal principal) {
        MemberVO vo = memberService.read(principal.getName());
        log.info("회원수정 {}", memberService.updatePassword(map, vo) ? "성공" : "실패");
        return "redirect:/";
    }

    @ResponseBody   // Ajax
    @PostMapping("/uploadProfile")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public boolean uploadProfile(@RequestParam("file") @NonNull MultipartFile file, @NonNull Principal principal) {
        return !file.isEmpty() && memberService.uploadProfile(file, principal.getName());
    }

    @ResponseBody
    @PostMapping("/deleteProfile")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public boolean deleteProfile(@NonNull Principal principal) {
        return memberService.deleteProfile(principal.getName());
    }
}
