package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardAttachMapper;
import org.zerock.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Setter(onMethod_ = @Autowired)
	private BoardAttachMapper attachMapper;
	
	//트랜잭션 처리
	@Transactional
	@Override
	public void register(BoardVO board) {
		log.info("서비스register...." + board);
		mapper.insertSelectKey(board);
		
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return;
		}
		
		board.getAttachList().forEach(attach ->{
			
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("bno....."+ bno);
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify....." + board);
		return mapper.update(board) == 1;
	}
	
	//트랜잭션처리
	@Transactional
	@Override
	public boolean remove(Long bno) {
		log.info("remove.."+ bno);
		
		attachMapper.deleteAll(bno);
		
		return mapper.delete(bno) == 1;
	}

//	@Override
//	public List<BoardVO> getList() {
//		log.info("getlist.....");
//		return mapper.getList();
//		}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("get List :" + cri);
		
		return mapper.getListWithPaging(cri);
	
	}

	@Override
	public int getTotal(Criteria cri) {
		log.info("getTotal:" + cri);
		
		return mapper.getTotalCount(cri);
	}

	@Override
	public List<BoardAttachVO> getAttachList1(Long bno) {
		
		log.info("get attach list by bno" + bno);
		
		return attachMapper.findByBno(bno);
	}
}

