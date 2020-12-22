package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}
	
	@Test
	public void testPaging() {
		Criteria cri = new Criteria();

		cri.setPageNum(3);
		cri.setAmount(10);
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board));
	}

	@Test
	public void testPageDTO() {
		Criteria cri = new Criteria();
		cri.setPageNum(11);
		PageDTO dto = new PageDTO(cri, 123);
		
		log.info(dto);
	}
	
	@Test
	public void testInser() {
		BoardVO board = new BoardVO();
		board.setTitle("새로작성글");
		board.setContent("새로작성내용");
		board.setWriter("newbiew");
		mapper.insert(board);
		log.info(board);
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로작성글2");
		board.setContent("새로작성내용2");
		board.setWriter("newbiew2");
		
		mapper.insertSelectKey(board);
		log.info(board);
	}
	
	@Test
	public void testRead() {
		BoardVO board = mapper.read(5L);
		log.info(board);
	}
	
	@Test
	public void testDelete() {
		log.info("delete count :" +  mapper.delete(3L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		//실행전 존재하는 번호인지 확인할것
		board.setBno(4L);
		board.setTitle("수정된제목222");
		board.setContent("수정된내용333");
		board.setWriter("수정된작성자333");
		
		int count = mapper.update(board);
		log.info("update count:" + count);
	}
}
