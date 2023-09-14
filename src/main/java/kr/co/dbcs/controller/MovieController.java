package kr.co.dbcs.controller;

import kr.co.dbcs.model.LovedVO;
import kr.co.dbcs.model.MovieVO;
import kr.co.dbcs.service.LovedService;
import kr.co.dbcs.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Log4j2
@Controller
@RequestMapping(value = "/movie")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovieController {

    private final LovedService lovedService;
    private final MemberService memberService;

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

    @ResponseBody
    @GetMapping(value = "/crawl")
    public List<MovieVO> crawl() {
        return memberService.crawl();
    }

    @ResponseBody
    @PostMapping("/loved/{username}/{movieId}")
    public boolean addLoved(@PathVariable String username,
                            @PathVariable String movieId) {
        return lovedService.create(new LovedVO(username, movieId));
    }

    @ResponseBody
    @DeleteMapping("/loved/{username}/{movieId}")
    public boolean deleteLoved(@PathVariable String username,
                               @PathVariable String movieId) {
        return lovedService.deleteByUsernameWithId(new LovedVO(username, movieId));
    }

    @ResponseBody
    @GetMapping("/loved/{username}")
    public List<String> getLovedByUsername(@PathVariable String username) {
        List<String> ret = lovedService.getLovedByUsername(username);
        log.info("TEST : {}", ret);
        return ret;
    }
}
