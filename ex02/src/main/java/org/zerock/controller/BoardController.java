package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/board/*")
@Controller
@AllArgsConstructor
@Log4j
public class BoardController {
	
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
		model.addAttribute("list", service.getList());
	}
	
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno")Long bno, Model model) {
		
		model.addAttribute("board", service.get(bno));
	}
	
	@GetMapping("/register")
	public void registerGET() {
		log.info("registerGET...");
	}
	
	@PostMapping("/register")
	public String register(BoardVO board,RedirectAttributes rttr) {
		log.info("register:"+board);
		
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list";
		}
	
//	@PostMapping("/modify")
//	public String modify(BoardVO board,RedirectAttributes rttr) {
//		int count = service.modify(board);
//		if(count == 1) {
//			rttr.addFlashAttribute("result","succes");
//		}
//		return "redirect:/board/list";
//	}
	
	
//	@PostMapping("/remove")
//	public String remove(@RequestParam("bno")Long bno,RedirectAttributes rttr) {
//		service.remove(bno);
//		return "redirect:/board/list";
//	}
}
