package org.zerock.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {

	// Authentiacion 타입의 파라미터를 받도록해, 필요한경우 사용자 정보를 확인할수 있도록.
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		
		log.info("access Denied :" + auth);
		
		model.addAttribute("msg", "Access Denied");
	}
	
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		
		log.info("error" + error);
		log.info("logout:" + logout);
		
		if(error != null) {
			model.addAttribute("error", "error check");
		}
		
		if(logout != null) {
			model.addAttribute("logout", "logout!");
		}
	}

	@GetMapping("/customLogout")
	public void logoutGet() {
		
		log.info("custom logout");
	}
	
	
}
