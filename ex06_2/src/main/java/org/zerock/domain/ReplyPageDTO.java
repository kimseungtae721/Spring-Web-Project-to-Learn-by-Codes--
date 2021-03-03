package org.zerock.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplyPageDTO {
	//댓글 목록 과, 전체댓글의 수를 같이전달
	private int replyCnt;
	private List<ReplyVO> list;

}
