package kr.co.dbcs.controller;

import kr.co.dbcs.model.BoardList;
import kr.co.dbcs.model.BoardVO;
import kr.co.dbcs.service.BoardService;
import kr.co.dbcs.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping(value = "/board")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BoardController {

    private final ReplyService replyService;
    private final BoardService boardService;
    private final BoardList boardList;

    @GetMapping(value = "/{path}")
    public String handlePath(@PathVariable @NonNull String path, Model model, Principal principal,
                             @RequestParam(required = false, defaultValue = "1") int page,
                             @RequestParam(required = false, defaultValue = "") String keyword) {

        switch (path) {
            case "write":
                break;
            case "list":
                boardList.initBoardList(7, boardService.count(), page);
                Map<String, Integer> map = new HashMap<>();
                map.put("start", boardList.getStartNo());
                map.put("end", boardList.getEndNo());
                model.addAttribute("boardList", boardService.readAll(map));
                model.addAttribute("notice", boardService.readAllNotice());
                model.addAttribute("page", page);
                model.addAttribute("allPage", boardList.getTotalPage());
                model.addAttribute("block", boardList.getBLOCK());  // 한 페이지에 보여줄 범위
                model.addAttribute("fromPage", boardList.getStartPage());
                model.addAttribute("toPage", boardList.getEndPage());
                log.info("boardList: {}", boardList);
                break;
            default:
                return "/";
        }
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);

        return "/home";
    }

    @PostMapping(value = "/write")
    public String insertBoard(@ModelAttribute(value = "boardVO") @NonNull BoardVO boardVO, @NonNull Principal principal) {
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
    public String selectBoard(@PathVariable int boardNo, @NonNull Model model) {
        model.addAttribute(boardService.updateView(boardNo));
        model.addAttribute("data", boardService.read(boardNo));
        model.addAttribute("reply", replyService.readAllByBoardNo(boardNo));
        return "/home";
    }

    @GetMapping(value = "/update/{boardNo}")
    public String updateBoardForm(@PathVariable int boardNo, @NonNull Model model) {
        model.addAttribute("data", boardService.read(boardNo));
        return "/home";
    }

    @PostMapping(value = "/delete/{boardNo}")
    public String deleteBoard(@PathVariable int boardNo) {
        boardService.delete(boardNo);
        return "redirect:/board/list";
    }

    @GetMapping(value = "/search")
    public String searchBoard(@RequestParam String keyword,
                              @RequestParam Integer searchType, @NonNull Model model) {

        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("searchType", searchType);
        model.addAttribute("data", boardService.search(map));
        return "/home";
    }
}
