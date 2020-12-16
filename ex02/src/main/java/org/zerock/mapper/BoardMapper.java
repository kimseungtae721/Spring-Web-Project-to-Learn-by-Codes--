package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardMapper {

	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	// insert만되고 생성된 pk값을 알필요가 없는경우
	public void insert(BoardVO board);
	// insert가되고 생성된 pk값을 알필요가 있는경우	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(long bno);
	
	public int delete(long bno);
	
	public int update(BoardVO board);
}
