package kr.co.dbcs.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.dbcs.model.BoardVO;
import kr.co.dbcs.model.ReplyVO;
import kr.co.dbcs.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping(value = "/reply")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReplyController {
	
	private final ReplyService replyService;
	
	@PostMapping(value="/insert")
	public String insertReply(@ModelAttribute(value="replyVO" ) ReplyVO replyVO, Principal principal) {
		replyVO.setUsername(principal.getName());
		
		log.info("댓글쓰기{}", replyService.create(replyVO) ? "성공" : "실패");
		return "redirect:/";
	}
	
	@GetMapping(value="/selectAll")
	public String selectAllReply(@ModelAttribute(value = "replyVO") ReplyVO replyVO,Model model) {
	model.addAttribute("reply", replyService.readAll());
	return "redirect:/";
	}
	
	@PostMapping(value = "/delete/{replyNo}")
	public String deleteReply(@PathVariable int replyNo, @ModelAttribute BoardVO boardVO) {
		replyService.delete(replyNo);
		return "redirect:/board/view/" + boardVO.getBoardNo();
	}
}
