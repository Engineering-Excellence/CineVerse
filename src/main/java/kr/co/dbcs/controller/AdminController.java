package kr.co.dbcs.controller;

import kr.co.dbcs.service.StatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping(value = "/admin")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

    private final StatService statService;

    @GetMapping(value = "/{path}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String handlePath(@PathVariable String path, Model model) {
        switch (path) {
            case "stat":
                model.addAttribute("memberCount", statService.getMemberCount());
                model.addAttribute("boardCount", statService.getBoardCount());
                model.addAttribute("replyCount", statService.getReplyCount());
                model.addAttribute("genderData", statService.getGenderData());
                model.addAttribute("ageData", statService.getAgeData());
                model.addAttribute("boardViewData", statService.getBoardViewData());
                model.addAttribute("boardReplyData", statService.getBoardReplyData());
                model.addAttribute("boardLastWeekData", statService.getBoardLastWeekData());
                model.addAttribute("replyLastWeekData", statService.getReplyLastWeekData());
                break;
        }
        return "/home";
    }
}
