package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

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
/*	
	@PostMapping("/uploadAjaxAction")
	public void uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("----Ajax Post----");
			
		String uploadFolder = "C:\\upload";

		// make folder -------
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("upload path: " + uploadFolder);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		// make folder end
		
		for(MultipartFile multipartFile : uploadFile) {
		
			log.info("--------------");
			log.info("Upload File Name:" + multipartFile.getOriginalFilename());
			log.info("Upload file size:" + multipartFile.getSize());
			String uploadFileName = multipartFile.getOriginalFilename();

			//IE 경로 파일명 \ 짤라내기
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("IE 에서 파일 경로 짜른이름:" + uploadFileName);
			
			//uuid 랜덤으로 생성해서 파일이름앞에 선언
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;

			
//			File saveFile = new File(uploadFolder, uploadFileName);
			
			
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				//이미지 타입 체크 (섬네일 이미지 생성)
				if(checkImageType(saveFile)) {
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					
					Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumbnail,100,100);
					
					thumbnail.close();
				}
			}catch (Exception e) {
				log.error(e.getMessage());
			} //end catch
		
		}//end for
	}
*/
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity <List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile){
		
		log.info("----Ajax Post----");

		List<AttachFileDTO> list = new ArrayList<AttachFileDTO>();
		String uploadFolder = "C:\\upload";
		
		String uploadFolderPath = getFolder();

		// make folder -------
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("upload path: " + uploadFolder);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		// make yyyy/MM/dd folder end
		
		for(MultipartFile multipartFile : uploadFile) {

			AttachFileDTO attachDTO = new AttachFileDTO();
			
			
			log.info("--------------");
			log.info("Upload File Name:" + multipartFile.getOriginalFilename());
			log.info("Upload file size:" + multipartFile.getSize());
			String uploadFileName = multipartFile.getOriginalFilename();
			
			
			//IE 경로 파일명 \ 짤라내기
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("IE 에서 파일 경로 짜른이름:" + uploadFileName);
			attachDTO.setFileName(uploadFileName);
			
			//uuid 랜덤으로 생성해서 파일이름앞에 선언
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;

			
//			File saveFile = new File(uploadFolder, uploadFileName);
			
			
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				
				//이미지 타입 체크 (섬네일 이미지 생성)
				if(checkImageType(saveFile)) {
					attachDTO.setImage(true);
					
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					
					Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumbnail,100,100);
					
					thumbnail.close();
				}
				//add to List
				list.add(attachDTO);
			}catch (Exception e) {
				log.error(e.getMessage());
			} //end catch
		
		}//end for
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	//년 월 일 폴더 생성
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
	
	//특정 파일이 이미지 타입인지 별도 검사
	private boolean checkImageType(File file) {
		
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		}catch (IOException e) {
			e.printStackTrace();
		}
	
		return false;
		
	}
	
}
