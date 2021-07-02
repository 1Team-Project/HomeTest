package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyPageVO;
import com.spring.domain.ReplyVO;
import com.spring.mapper.BoardMapper;
import com.spring.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper mapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Transactional
	@Override
	public boolean replyInsert(ReplyVO reply) {
		// TODO Auto-generated method stub
		boardMapper.replyCntUpdate(reply.getBno(), 1);
		return mapper.insert(reply)>0?true:false;
	}

	@Override
	public ReplyVO replyGet(int rno) {
		// TODO Auto-generated method stub
		return mapper.read(rno);
	}

	@Override
	public boolean replyUpdate(ReplyVO reply) {
		// TODO Auto-generated method stub
		return mapper.update(reply)>0?true:false;
	}

	@Transactional
	@Override
	public boolean replyDelete(int rno) {
		// TODO Auto-generated method stub
		
		//rno를 이용해서 bno 알아내기
		ReplyVO reply = mapper.read(rno);
		
		boardMapper.replyCntUpdate(reply.getBno(), -1);
		return mapper.delete(rno)>0?true:false;
	}

	@Override
	public ReplyPageVO getList(Criteria cri, int bno) {
		// TODO Auto-generated method stub
		return new ReplyPageVO(mapper.getCountByBno(bno), mapper.list(cri, bno));
	}

	
}
