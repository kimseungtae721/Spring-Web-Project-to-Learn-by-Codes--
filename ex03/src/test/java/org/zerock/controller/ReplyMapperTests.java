package org.zerock.controller;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	//게시물 bno값
	private Long[] bnoArr = {504L,505L,506L,507L,508L};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	//매퍼 주입 테스트
	@Test
	public void testMapper() {
		
		log.info(mapper);
	}
	
	//댓글생성
	@Test
	public void testCreate() {
		
		IntStream.rangeClosed(1, 10).forEach(i ->{ 
			
			ReplyVO vo = new ReplyVO();
			
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글테스트" + i);
			vo.setReplyer("Replyer" + i);
			
			mapper.insert(vo);
		});
	}
	
	//댓글 조회
	@Test
	public void testRead() {
		
		Long targetRno = 5L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		log.info(vo);
	}
	
	//댓글 업데이트
	@Test
	public void testUpdate() {
		
		Long targetRno = 10L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		vo.setReply("update Reply");
		
		int count = mapper.update(vo);
		
		log.info(count);
	}
	
	//댓글 삭제
	@Test
	public void testDelete() {
		
		Long targetRno = 9L;
		
		int count = mapper.delete(targetRno);
		
		log.info("삭제 count  -----------:" +count);
	}
	
	@Test
	public void testList() {
		
		Criteria cri = new Criteria();
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		
		replies.forEach(reply -> log.info(reply));
	}
}
