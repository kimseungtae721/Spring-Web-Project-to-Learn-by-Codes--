package org.zerock.controlle;

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
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController     //jsp와 달리 순수한 데이터를 반환하는 형태이므로 다양한 포맷의 데이터 전송가능 (일반문자열,JSON,XML)
@RequestMapping("/sample")
@Log4j
public class SampleController {

	//단순 문자열 반환
	@GetMapping(value = "/getText", produces ="text/plain; charset=UTF-8")
	public String getText() {
		
		log.info("mime type : " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕";
	}
	
	//객체의반환 (XML과 JSON 방식의 데이터 생성  produces 속성 생략가능.)
	@GetMapping(value = "/getSample",produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
	public SampleVO getSample() {
		
		return new SampleVO(111,"스타" ,"로드");
	}
	
	@GetMapping(value = "/getSample2")
	public SampleVO getSample2() {
		
		return new SampleVO(113,"로켓","라쿤");
	}
	
	//컬렉션 타입의 객체 반환 ( 여러데이터를 한번에 전송하기 위해서 배열이나,리스트 ,맵타입의 객체 전송)
	@GetMapping(value = "/getList")
	public List<SampleVO> getList(){
		//1부터 10미만까지 루프처리하면서, SampleVO 객체를 만들어서 List<SampleVO>로 만들어냄.
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i +"First", i+ "Last")).collect(Collectors.toList());
	}

	//맵의 경우에는 '키'와 '값'을 가지는 하나의 객체로 간주됨
	@GetMapping(value = "/getMap")
	public Map<String, SampleVO> getMap(){
		
		Map<String, SampleVO> map = new HashMap<>();
		map.put("First",new SampleVO(111, "그루트", "주니어"));
		
		return map;
	}
	
	//rest방식은 데이터 자체를 전송하는방식으로 처리되므로, 정상적인데이터인지 구분할수있는방법.
	//ResponseEntity는 데이터와 함께 http 헤더의 상태 메시지를 같이 전달하는용도(http 상태코드와 에러메시지를 함께전달가능)
	@GetMapping(value ="/check", params = { "height","weight" })
	public ResponseEntity<SampleVO> check(Double height, Double weight){
		// height,weight 파라미터 를 전달받고 
		SampleVO vo = new SampleVO(0, ""+height, ""+ weight);
		
		ResponseEntity<SampleVO> result = null;
		//만일 height값이 150작다면 502상태코드와 데이터전송 그렇지않다면 200코드
		if(height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		
		return result;
	}
	
	
	//@PathVariable - Rest방식에서 자주사용됨, URL 경로의 일부를 파라미터로 사용할때 이용
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") Integer pid) {
	
		return new String[] { "category :" + cat , "productid :" + pid } ;
	}	
	
	//@RequestBody - 전달된요청(request)의 내용(body)을 이용해서 해당 파라미터의 타입으로 변환을 요구
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		
		log.info("ticket!@#!@#" + ticket);
		
		return ticket;
	}
	
}
