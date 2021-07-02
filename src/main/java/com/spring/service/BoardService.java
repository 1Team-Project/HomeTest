package com.spring.service;

import java.util.List;

import com.spring.domain.AttachFileDTO;
import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;

public interface BoardService {

	public boolean insert(BoardVO vo);
	public boolean delete(int bno);
	public boolean update(BoardVO vo);
	public List<BoardVO> list(Criteria cri);	
	public BoardVO read(int bno);
	public int total(Criteria cri);
	
	public List<AttachFileDTO> getAttachList(int bno);
}
