package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

	private ReplyMapper mapper;
	
	@Override
	public int insert(ReplyVO vo) {
		log.info("Service 삽입 -----" + vo);
		
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO read(Long bno) {
		log.info("Service 읽기 -----" + bno);
		
		return mapper.read(bno);
	}

	@Override
	public int update(ReplyVO vo) {
		log.info("Service 업데이트 -----" + vo);
		
		return mapper.update(vo);
	}

	@Override
	public int delete(Long rno) {
		log.info("Service삭제 -----" + rno);
		
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getListWithPaging(Criteria cri, Long bno) {
		log.info("Service리스트 -----" + bno);
		
		return mapper.getListWithPaging(cri, bno);
	}

}
