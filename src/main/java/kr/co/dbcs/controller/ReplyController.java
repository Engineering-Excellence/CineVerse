package kr.co.dbcs.controller;

import kr.co.dbcs.model.BoardVO;
import kr.co.dbcs.model.ReplyVO;
import kr.co.dbcs.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Log4j2
@Controller
@RequestMapping(value = "/reply")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping(value = "/insert")
    public String insertReply(@ModelAttribute(value = "replyVO") ReplyVO replyVO, Principal principal, @ModelAttribute BoardVO boardVO) {
        replyVO.setUsername(principal.getName());

        log.info("댓글쓰기{}", replyService.create(replyVO) ? "성공" : "실패");
        return "redirect:/board/view/" + boardVO.getBoardNo();
    }

    @GetMapping(value = "/selectAll")
    public String selectAllReply(@ModelAttribute(value = "replyVO") ReplyVO replyVO, Model model) {
        model.addAttribute("reply", replyService.readAll());
        return "redirect:/";
    }

    @PostMapping(value = "/delete/{replyNo}")
    public String deleteReply(@PathVariable int replyNo, @ModelAttribute BoardVO boardVO) {
        replyService.delete(replyNo);
        return "redirect:/board/view/" + boardVO.getBoardNo();
    }
}
