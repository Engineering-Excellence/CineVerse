package kr.co.dbcs.controller;

import kr.co.dbcs.service.StatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
                break;
        }
        return "/home";
    }
}
