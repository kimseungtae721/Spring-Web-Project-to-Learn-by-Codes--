package org.zerock.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() {
		
		log.info("uploadform GET------");
		
	}
	
	//스프링 mvc에서 제공하는 MultipartFile 타입 사용해서 데이터받기 
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadfile, Model model) {
		
		String uploadFolder = "C:\\upload";
		
		
		//향상된 for문 (자료형  변수명 : 배열명)
		for(MultipartFile multipartfile : uploadfile) {
			
			log.info("------POST--------");
			log.info("Upload file Name : " + multipartfile.getOriginalFilename());
			log.info("Upload file Size : " + multipartfile.getSize());
			log.info("------POST--------");
			
			File saveFile = new File(uploadFolder, multipartfile.getOriginalFilename());
			
			try {
				multipartfile.transferTo(saveFile); //MultipartFile의 메서드 (파일저장)
			}catch (Exception e) {
				log.error(e.getMessage());
			} //end catch
			
		} //end for
	}

	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		
		log.info("------uploadAjax------");

	}
	
	@PostMapping("/uploadAjaxAction")
	public void uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("----Ajax Post----");
			
		String uploadFolder = "C:\\upload";

		for(MultipartFile multipartFile : uploadFile) {
		
			log.info("--------------");
			log.info("Upload File Name:" + multipartFile.getOriginalFilename());
			log.info("Upload file size:" + multipartFile.getSize());
			String uploadFileName = multipartFile.getOriginalFilename();

			//IE 경로 파일명 \ 짤라내기
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("IE 에서 파일 경로 짜른이름:" + uploadFileName);
	
			File saveFile = new File(uploadFolder, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
			}catch (Exception e) {
				log.error(e.getMessage());
			} //end catch
		
		}//end for
	}
	
	
}
