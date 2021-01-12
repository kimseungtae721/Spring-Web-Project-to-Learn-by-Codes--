package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	
	//등록
	public int insert(ReplyVO vo); 
	
	//조회
	public ReplyVO read(Long bno);
	
	//삭제
	public int delete(Long bno);
	
	//수정
	public int update(ReplyVO vo);

	public List<ReplyVO> getListWithPaing(
			@Param("cri") Criteria cri, @Param("bno")Long bno);
}
