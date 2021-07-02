package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyVO;

public interface ReplyMapper {

	public int insert(ReplyVO reply);
	
	public ReplyVO read(int rno);
	
	public int update(ReplyVO reply);
	
	public int delete(int rno);
	
	//´ñ±Û
	public int deleteAll(int bno);
	
	
	public int getCountByBno(int bno);
	
	public List<ReplyVO> list(@Param("cri")Criteria cri,@Param("bno")int bno);
}
