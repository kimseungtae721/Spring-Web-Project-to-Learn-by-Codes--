package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.adapter.HttpWebHandlerAdapter;
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
	
	//form 방식으로 파일업로드처리 
	@PreAuthorize("isAuthenticated()") //로그인 한사용자만 파일업로드 가능 2차 확인
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
	
	//ajax방식 파일업로드 처리
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
	
	
	//특정 파일이름을 받아서 이미지 데이터를 전송하는 코드를 생성
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		log.info("fileNmae:" + fileName);
		
		File file = new File("c:\\upload\\" + fileName);
		
		log.info("file :" + file);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file),header, HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//파일 다운로드 처리
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent")String userAgent, String fileName){
		
		log.info("download file: " + fileName);
		
		Resource resource = new FileSystemResource("c:\\upload\\" + fileName);
		
		//값이 있는지 여부 확인
		if(resource.exists() == false) {
			log.info("resource 값이없음");
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		
		//UUID 삭제
		String resourceName = resource.getFilename();
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);
		
		log.info("resource : " + resource);
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			String downloadName = "null";
			
			if(userAgent.contains("Trident")) {
				log.info("IE browser");
				downloadName = URLEncoder.encode(resourceOriginalName,"UTF-8").replaceAll("\\+"," ");
				log.info("IE browser" + downloadName);
				
			}else if(userAgent.contains("Edge")) {
				log.info("Edge browser");
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
				log.info("Edge Name: " + downloadName);
			
			}else {
				log.info("Chrome browser");
				downloadName = new String (resourceOriginalName.getBytes("UTF-8"),"ISO-8859-1");
			}
			
//			headers.add("Content-Disposition", "attachment; filename=" + downloadName);
			headers.add("Content-Disposition", "attachment; filename=" + downloadName); 
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	//서버에서 첨부파일 삭제
	@PreAuthorize("isAuthenticated()") //로그인 한사용자만 파일업로드 가능 2차 확인
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type){
		
		log.info("deleteFile :" + fileName);
		
		File file;
		
		try {
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));	
			
			file.delete();
			
			if(type.equals("image")) {
				String largeFileName = file.getAbsolutePath().replace("s_", "");
				
				log.info("largeFileName: " + largeFileName);
				
				file = new File(largeFileName);
				
				file.delete();
			}
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
		
	}
}
