package org.zerock.controller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/replies/")
@Log4j
@AllArgsConstructor
public class ReplyController {
	
	private ReplyService service;
	
	//댓글 등록
	@PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){ 
		
		log.info("create ------" + vo);
		int count = service.insert(vo);
		log.info("count ------ " + count);
		return count == 1 ?  new ResponseEntity<String>("success1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//특정 게시물 조회
	@GetMapping(value = "/pages/{bno}/{page}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ReplyVO>> getList(@PathVariable Long bno,@PathVariable int page){
		
		log.info("get List -------");
		Criteria cri = new Criteria(page, 10);
		log.info("get cri-------" + cri);
		
		return new ResponseEntity<List<ReplyVO>>(service.getListWithPaging(cri, bno),HttpStatus.OK);
	}
	
	//댓글조회
	@GetMapping(value = "/{rno}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ReplyVO> getUpdate(@PathVariable Long rno){
		log.info("getUpdate ------" + rno);
		
		return new ResponseEntity<ReplyVO>(service.read(rno),HttpStatus.OK);
	}
	
	//댓글수정
	@RequestMapping(value = "/{rno}", method = {RequestMethod.PUT, RequestMethod.PATCH}, consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> update(@PathVariable Long rno, @RequestBody ReplyVO vo){

		vo.setRno(rno);
		log.info("rno ------ " + rno);
		log.info("update --------" + vo);
		
		return service.update(vo) == 1 ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//댓글삭제
	@DeleteMapping(value ="/{rno}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> delete(@PathVariable Long rno){
		log.info("삭제 ------ " + rno);
		
		return service.delete(rno) == 1 ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
