package org.zerock.mapper;


import java.util.List;

import org.zerock.domain.BoardAttachVO;

public interface BoardAttachMapper {

	public void insert(BoardAttachVO vo);
	
	public void delete(String uuid);

	//특정게시물의 번호로 첨부파일을 찾기위한 작업에 필요한 메서드
	public List<BoardAttachVO> findByBno(Long bno);
}
