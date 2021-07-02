package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;

public interface BoardMapper {

	public int insert(BoardVO vo);
	
	public int delete(int bno);
	
	public int update(BoardVO vo);
	
	public List<BoardVO> list(Criteria cri);	
	
	public BoardVO read(int bno);

	public int totalCnt(Criteria cri);
	
	public int replyCntUpdate(@Param("bno") int bno, @Param("amount") int amount);
}
