package org.zerock.domain;

import lombok.Data;

@Data
public class AttachFileDTO {
	
	
	//브라우저로 데이터를 전송
	private String fileName;
	private String uploadPath;
	private String uuid;
	private boolean image;
}
