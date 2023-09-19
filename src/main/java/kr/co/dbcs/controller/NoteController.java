package kr.co.dbcs.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.dbcs.model.NoteVO;
import kr.co.dbcs.service.MemberService;
import kr.co.dbcs.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping(value = "/note")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoteController {

	private final NoteService noteService;
	private final MemberService memberService;
	
	@GetMapping(value = "/{path}")
    public String handlePath(@PathVariable String path, Model model, Principal principal,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "") String keyword) {
		switch (path) {
			case "note":
				model.addAttribute("data", memberService.read(principal.getName()));
				model.addAttribute("notePartner",noteService.readDialog(principal.getName()));
				break;
		}
		return "/home";
	}

	@PostMapping(value = "/insertNote")
	@ResponseBody
	public boolean insertNote(@RequestBody NoteVO noteVO){
		return noteService.create(noteVO);//발신인의 쪽지함
	}
	
	@PostMapping(value = "/dialog")
	@ResponseBody
	public List<NoteVO> select(@RequestBody HashMap<String, String> map, Principal principal) {
		map.put("username", principal.getName());
		return noteService.detailDialog(map);
	}
	
}
