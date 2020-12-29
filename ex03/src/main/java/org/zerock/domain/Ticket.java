package org.zerock.domain;

import lombok.Data;

@Data
public class Ticket {
   //번호 , 소유주, 등급 지정
	private int tno;  
	private String owner;
	private String grade;
}
