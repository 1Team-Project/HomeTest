package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.AttachFileDTO;
import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.mapper.BoardAttachMapper;
import com.spring.mapper.BoardMapper;
import com.spring.mapper.ReplyMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private BoardAttachMapper attachMapper;
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Transactional
	@Override
	public boolean insert(BoardVO vo) {
		// 새글 등록
		boolean result = mapper.insert(vo)>0?true:false;
		
		//첨부파일 여부 확인
		if(vo.getAttachList()==null || vo.getAttachList().size()<=0) {
			return result;
		}
		
		// 첨부파일 등록
		vo.getAttachList().forEach(attach -> {
			attach.setBno(vo.getBno());
			attachMapper.insert(attach);
		});
		
		return result;
	}

	@Transactional
	@Override
	public boolean delete(int bno) {
		
		//댓글 삭제
		replyMapper.deleteAll(bno);
		//첨부파일 삭제
		attachMapper.delete(bno);
		//게시글 삭제
		return mapper.delete(bno)>0?true:false;
	}

	@Transactional
	@Override
	public boolean update(BoardVO vo) {
		//기존에 첨부파일 정보 모두 삭제 후 삽입
		attachMapper.delete(vo.getBno());
		//게시글 수정
		boolean modifyResult = mapper.update(vo)>0?true:false;
		//첨부파일 삽입
		if(modifyResult && vo.getAttachList().size()>0) {
			for(AttachFileDTO dto:vo.getAttachList()) {
				dto.setBno(vo.getBno());
				attachMapper.insert(dto);
			}
		}

		return modifyResult;
	}

	@Override
	public List<BoardVO> list(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.list(cri);
	}

	@Override
	public BoardVO read(int bno) {
		// TODO Auto-generated method stub
		return mapper.read(bno);
	}

	@Override
	public int total(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.totalCnt(cri);
	}

	@Override
	public List<AttachFileDTO> getAttachList(int bno) {
		// TODO Auto-generated method stub
		return attachMapper.findByBno(bno);
	}

}
