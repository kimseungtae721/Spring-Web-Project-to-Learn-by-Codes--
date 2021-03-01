package org.zerock.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/sample/*")
public class SampleController {

	
	@GetMapping("/all")
	public void doAll() {

		log.info("all--------");
	
	}

	@GetMapping("/member")
	public void doMember() {
		
		log.info("member--------");
		
	}
	
	@GetMapping("/admin")
	public void doAdmin() {
		
		log.info("doAdmin---------");

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping("/annoMember")
	public void domember2() {
		
		log.info("로그인 어노테이션 멤버");
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/annoAdmin")
	public void doAdmin2() {
		
		log.info("어드민 어노테이션 ");
		
	}
	
}
