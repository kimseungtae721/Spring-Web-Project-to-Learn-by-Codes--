package org.zerock.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {

	@GetMapping("/ex01")
	public String basicGet2(SampleDTO dto) {
		log.info(""+dto);
		return "ex01";
	}

	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids")ArrayList<String> ids) {
		log.info("ids:" + ids);
		return "ex02List";
	}

	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		log.info("array ids:" + Arrays.toString(ids));
		return "ex02Array";
	}
	
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos:"+ list);
		return "ex02Bean";
	}
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto,@ModelAttribute("page")int page) {
		log.info("dto"+ dto);
		log.info("page"+ page);
		return "/sample/ex04";
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload...");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		files.forEach(file ->{
			log.info("-----");
			log.info("name:" + file.getOriginalFilename());
			log.info("size:"+ file.getSize());
		});
	}

}
