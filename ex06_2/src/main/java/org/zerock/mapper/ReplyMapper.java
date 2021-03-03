package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {

	public int insert(ReplyVO vo);
	
	public ReplyVO read(Long bno);
	
	public int update(ReplyVO vo);
	
	public int delete(Long rno);

	//매퍼 메소드가 여러개의 파라미터를 가진다면 이 애노테이션은 이름에 일치하는 매퍼 메소드 파라미터에 적용된다. 반면에 여러개의 파라미터는 순서대로 명명된다. 예를들어 #{param1}, #{param2} 등이 디폴트다. @Param(“person”)을 사용하면 파라미터는 #{person}로 명명된다.
	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno")Long bno);

	//댓글 개수
	public int getCountByBno(Long bno);
}
