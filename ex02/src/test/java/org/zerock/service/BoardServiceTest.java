package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTest {
	
//	@Autowired
	@Setter(onMethod_ = {@Autowired})
	private BoardService serivce;

	@Test
	public void testExist() {
		log.info(serivce);
		assertNotNull(serivce);
	}
	
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("새로작성하는글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("NEW22");
		
		serivce.register(board);
		
		log.info("생성된 게시물의 번호 :" + board.getBno());
	}
	
	@Test
	public void testGetList() {
		serivce.getList().forEach(board -> log.info(board));
	}
	
	@Test
	public void testGet() {
		log.info(serivce.get(1L));
	}
	
	@Test
	public void testDelete() {
		log.info("remove result"+ serivce.remove(6L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = serivce.get(2L);
		if(board == null) {
			return;
		}
		board.setTitle("제목을수정합니다");
		log.info("modify result"+ serivce.modify(board));
	}
}
