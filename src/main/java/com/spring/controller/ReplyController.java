package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyPageVO;
import com.spring.domain.ReplyVO;
import com.spring.service.ReplyService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/replies/*")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	@PostMapping("/new")
	public ResponseEntity<String> create(@RequestBody ReplyVO reply){
		
		log.info("´ñ±Û »ðÀÔ ¿äÃ»");
		
		return service.replyInsert(reply)?new ResponseEntity<String>("success",HttpStatus.OK):
			new ResponseEntity<String>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/{rno}")
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") int rno){
		log.info("´ñ±Û ÇÏ³ª °¡Á®¿À±â");
		
		return new ResponseEntity<>(service.replyGet(rno),HttpStatus.OK);
	}
	
	@PutMapping("/{rno}")
	@PreAuthorize("principal.username == #reply.replyer")
	public ResponseEntity<String> update(@PathVariable("rno") int rno,@RequestBody ReplyVO reply){
		
		log.info("´ñ±Û ¼öÁ¤ "+rno+" ³»¿ë "+reply);
		
		reply.setRno(rno);
		
		return service.replyUpdate(reply)?new ResponseEntity<String>("success",HttpStatus.OK):
			new ResponseEntity<String>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/{rno}")
	@PreAuthorize("principal.username == #reply.replyer")
	public ResponseEntity<String> delete(@PathVariable("rno") int rno,@RequestBody ReplyVO reply){
		
		log.info("´ñ±Û »èÁ¦ ¿äÃ»");
		
		return service.replyDelete(rno)?new ResponseEntity<String>("success",HttpStatus.OK):
			new ResponseEntity<String>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/pages/{bno}/{page}")
	public ResponseEntity<ReplyPageVO> getList(@PathVariable("bno")int bno,@PathVariable("page")int page){
		log.info("´ñ±Û °¡Á®¿À±â "+bno+" page "+page);
	
		Criteria cri = new Criteria(page, 10);
		
		return new ResponseEntity<ReplyPageVO>(service.getList(cri, bno),HttpStatus.OK);
	
	}
	
	
}
