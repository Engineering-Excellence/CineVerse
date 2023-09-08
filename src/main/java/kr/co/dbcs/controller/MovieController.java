package kr.co.dbcs.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
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

@Log4j2
@Controller
@RequestMapping(value = "/movie")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovieController {

//    private final MemberService memberService;

    @GetMapping(value = "/{path}")
    public String handlePath(@PathVariable String path, Model model, Principal principal,
                             @RequestParam(required = false, defaultValue = "1") int page,
                             @RequestParam(required = false, defaultValue = "") String keyword) {
        switch (path) {
//            case "chat":
//                // model.addAttribute("name", principal.getName());
//                return "/member/chatTest";
        }
        return "/member/home";
    }

}
