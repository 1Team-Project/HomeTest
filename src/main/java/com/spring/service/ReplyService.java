package com.spring.service;

import java.util.List;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyPageVO;
import com.spring.domain.ReplyVO;

public interface ReplyService {

	public boolean replyInsert(ReplyVO reply);
	public ReplyVO replyGet(int rno);
	public boolean replyUpdate(ReplyVO reply);
	public boolean replyDelete(int rno);
	
	public ReplyPageVO getList(Criteria cri, int bno);
}
