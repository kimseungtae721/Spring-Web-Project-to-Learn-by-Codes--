package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RequestMapping("/sample")
@Log4j
@RestController
public class SampleController {
	
	//단순 문자열 반환
	@GetMapping(value = "/getText" , produces = "text/plain; charset=UTF-8")
	public String getText() {
		
		log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세용~";
	}
	
	//객체의 반환 (json,xml타입)
	@GetMapping(value = "/getSample", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
		
		return new SampleVO(112,"스타","로드");
				
	}
	//객체의 반환 (produces는  xml,json타입 생략가능)
	@GetMapping(value = "/getSample2")
	public SampleVO getList2(){
		
		return new SampleVO(114,"로드","스타");
	
	}
	// 컬렉션 타입 객체 반환 (1~10까지 반복 해서 VO 객체 생성)
	@GetMapping(value = "/getList")
	public List<SampleVO> getList(){

		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i+ "First", i + "Last")).collect(Collectors.toList());
	}
	
	// 컬렉션 타입 map 반환
	@GetMapping(value = "/getMap")
	public Map<String, SampleVO> getMap(){
		
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		map.put("first", new SampleVO(111,"그루트","주니어"));
		
		return map;
	}
	
	//ResponseEntity 타입 반환 (데이터와, HTTP 헤더 상태 메시지 같이 전달하는용도)
	@GetMapping(value = "/check", params = {"height", "weight"})
	public ResponseEntity<SampleVO> check(int height, int weight){
		
		SampleVO vo = new SampleVO(0, ""+ height, ""+weight);
		
		ResponseEntity<SampleVO> result = null;
		
		if(height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		return result;
	}
	
	//RestController의 파라미터 @PathVariable(url 상에 경로를 파라미터로 사용가능) 
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") int pid) {
		
		return new String[] {"category: " +cat, "productid: " +pid};
	}
	
	@PostMapping(value = "ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		
		log.info("convert.....ticket" + ticket);
		
		return ticket;
	}
}
