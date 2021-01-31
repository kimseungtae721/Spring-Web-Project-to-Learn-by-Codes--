package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyService {

	public int insert(ReplyVO vo);
	
	public ReplyVO read(Long bno);
	
	public int update(ReplyVO vo);
	
	public int delete(Long rno);
	
	public List<ReplyVO> getListWithPaging(Criteria cri, Long bno);
	
}
