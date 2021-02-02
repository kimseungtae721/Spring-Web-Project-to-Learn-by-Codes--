package org.zerock.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {

	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateDate;
	//댓글 개수
	private int replyCnt;
	
	//board 등록시 한번에 처리할수있도록 추가
	private List<BoardAttachVO> attachList;
}

