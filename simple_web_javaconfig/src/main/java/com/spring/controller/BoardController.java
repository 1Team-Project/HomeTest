package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board/*")
@Log4j2
public class BoardController {

	@RequestMapping(value="/read", method=RequestMethod.GET)
	public void read() {
		log.info("read ��û.....");
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public void insert() {
		log.info("insert ��û.....");
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.GET)
	public void remove() {
		log.info("remove ��û.....");
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public void update() {
		log.info("update ��û.....");
	}
}
