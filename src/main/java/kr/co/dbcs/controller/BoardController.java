package kr.co.dbcs.controller;

import kr.co.dbcs.model.BoardVO;
import kr.co.dbcs.service.BoardService;
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
@RequestMapping(value = "/board")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BoardController {

    private final BoardService boardService;
    private final ReplyService replyService;

    @GetMapping(value = "/{path}")
    public String handlePath(@PathVariable String path, Model model, Principal principal,
                             @RequestParam(required = false, defaultValue = "1") int page,
                             @RequestParam(required = false, defaultValue = "") String keyword) {
        switch (path) {
            case "write":
                break;
            case "list":
                model.addAttribute("data", boardService.readAll());
                break;
            default:
                break;
        }
        return "/member/home";
    }

    @PostMapping(value = "/write")
    public String insertBoard(@ModelAttribute(value = "boardVO") BoardVO boardVO, Principal principal) {
        boardVO.setUsername(principal.getName());
        log.info("글쓰기 {}", boardService.create(boardVO) ? "성공" : "실패");
        return "redirect:/board/list";
    }

    @PostMapping(value = "/update")
    public String updateBoard(@ModelAttribute(value = "boardVO") BoardVO boardVO) {
        log.info("게시판 업데이트 {}", boardService.update(boardVO) ? "성공" : "실패");
        return "redirect:/board/view/" + boardVO.getBoardNo();
    }

    @GetMapping(value = "/view/{boardNo}")
    public String selectBoard(@PathVariable int boardNo, Model model) {
        model.addAttribute("data", boardService.read(boardNo));
        model.addAttribute("reply", replyService.readAllByBoardNo(boardNo));
        return "/member/home";
    }

    @GetMapping(value = "/update/{boardNo}")
    public String updateBoardForm(@PathVariable int boardNo, Model model) {
        model.addAttribute("data", boardService.read(boardNo));
        return "/member/home";
    }

    @GetMapping(value = "/selectAll")
    public String selectAllBoard(@ModelAttribute(value = "boardVO") BoardVO boardVO) {

        return "redirect:/";
    }

    @PostMapping(value = "/delete/{boardNo}")
    public String deleteBoard(@PathVariable int boardNo) {
        boardService.delete(boardNo);
        return "redirect:/board/list";
    }
}
