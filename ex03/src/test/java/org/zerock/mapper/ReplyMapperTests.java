package org.zerock.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	private  Long[] bnoarr = { 194L,193L,192L,191L,190L,24L,23L,22L,21L };
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	@Test
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i ->{
			ReplyVO vo = new ReplyVO();
			
			//게시물번호
			vo.setBno(bnoarr[i % 5]);
			vo.setReply("댓글테스트 "+ i);
			vo.setReplyer("replyer" + i);

			mapper.insert(vo);
		});
	}
	
	@Test
	public void testRead() {
		Long targetRno = 5L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		log.info(vo);
	}
	
	@Test
	public void testDelete() {
		Long targetRno = 2L;
		
		mapper.delete(targetRno);
	}
	
	@Test
	public void testUpdate() {
		
		Long targetRno = 9L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		vo.setReply("UPDATE reply");
		vo.setReplyer("사용자변경");
		
		int count = mapper.update(vo);
		
		log.info("update count :" + count);
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
										
		List<ReplyVO> replies = mapper.getListWithPaing(cri, bnoarr[0]);
		
		replies.forEach(reply -> log.info(reply));
	}
	
}
