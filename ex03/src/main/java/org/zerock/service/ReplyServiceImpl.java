package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

	private ReplyMapper mapper;
	
	@Override
	public int register(ReplyVO vo) {
		log.info(vo);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long bno) {
		log.info("get.."+ bno);
		return mapper.read(bno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("modify..."+ vo);
		return mapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		log.info("remove..."+ rno);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {

		log.info("get reply list of a board " + bno);
		
		return mapper.getListWithPaing(cri, bno);
	}

}
