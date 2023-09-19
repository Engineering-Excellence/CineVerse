package kr.co.dbcs.controller;

import kr.co.dbcs.model.LovedVO;
import kr.co.dbcs.model.MovieVO;
import kr.co.dbcs.service.LovedService;
import kr.co.dbcs.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping(value = "/movie")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovieController {

    private final LovedService lovedService;
    private final MemberService memberService;

    @GetMapping(value = "/{path}")
    public String handlePath(@PathVariable String path, @NonNull Model model, Principal principal,
                             @RequestParam(required = false, defaultValue = "") String keyword) {

        model.addAttribute("keyword", keyword);

        return "/home";
    }

    @ResponseBody
    @PostMapping(value = "/crawl")
    public List<MovieVO> crawl(@RequestBody Map<String, String> paramsMap) {
        return memberService.crawl(paramsMap);
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
