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

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
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
            case "reply":
                model.addAttribute("reply", replyService.readByUsername(principal.getName()));
                break;
            default:
                break;
        }

        return "/home";
    }

    @ResponseBody
    @PostMapping(value = "/join")
    public boolean joinSubmit(@RequestBody @NonNull MemberVO memberVO) {
        return memberService.create(memberVO);
    }

    @ResponseBody
    @PostMapping(value = "/check")
    public boolean idCheck(@RequestBody @NonNull MemberVO memberVO) {
        return memberService.loadUserByUsername(memberVO.getUsername()) != null;
    }

    @ResponseBody
    @PostMapping(value = "/delete")
    @PreAuthorize("hasRole('ROLE_USER')")
    public boolean deleteMember(@RequestBody @NonNull MemberVO memberVO, @NonNull Principal principal) {
        MemberVO vo = memberService.read(principal.getName()); //암호화된 비밀번호를 담기 위한 VO 선언
        memberVO.setUsername(principal.getName()); //그냥 정보를 가져오기위해 사용한 VO에서 setusername을 가져온다.
        return memberService.deleteUserByPasswordChk(principal.getName(), memberVO.getPassword(), vo);
    }

    @ResponseBody
    @PostMapping(value = "/update")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public String updateSubmit(@RequestBody MemberVO memberVO) {
        log.info("회원수정 {}", memberService.update(memberVO) ? "성공" : "실패");
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping(value = "/updatePassword")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public boolean updatePassword(@RequestBody Map<String, Object> map, @NonNull Principal principal) {
        MemberVO vo = memberService.read(principal.getName());
        return memberService.updatePassword(map, vo);
    }

    @ResponseBody
    @PostMapping(value = "/uploadProfile")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public boolean uploadProfile(@RequestParam("file") @NonNull MultipartFile file, @NonNull Principal principal) {
        return !file.isEmpty() && memberService.uploadProfile(file, principal.getName());
    }

    @ResponseBody
    @PostMapping(value = "/deleteProfile")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public boolean deleteProfile(@NonNull Principal principal, HttpServletRequest request) {
        boolean ret = memberService.deleteProfile(principal.getName());
        if (ret) {
            request.getSession().invalidate();
        }
        return ret;
    }

    @ResponseBody
    @PostMapping(value = "/lists")
    public List<String> getUsernameList() {
        return memberService.getUsernameList();
    }
}
