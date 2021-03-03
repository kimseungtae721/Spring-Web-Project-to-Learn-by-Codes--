package org.zerock.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Criteria {
	//페이징처리를 위한변수
	private int pageNum;
	private int amount;
	
	//검색 조건을 위한 변수
	private String type;
	private String keyword;
	
	public Criteria() {
		this.pageNum = 1;
		this.amount = 10;
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type == null? new String[] {}: type.split("");
	}
	
	//게시물 삭제후 페이지번호,검색조건 유지
	public String getListLink() {
	
			//get방식 등의 파라미터 전송에 사용되는 문자열을 손쉽게 처리할수 있는 클래스
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
			.queryParam("pageNum", this.pageNum)
			.queryParam("amount", this.getAmount())
			.queryParam("type", this.getType())
			.queryParam("keyword", this.getKeyword());
			
			return builder.toUriString();
	}
}
