package org.zerock.domain;

import lombok.Data;

@Data
public class BoardAttachVO {

	//브라우저로 데이터를 전송
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	
	private Long bno;
}
