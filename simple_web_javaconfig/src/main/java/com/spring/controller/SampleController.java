package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/sample/*")
public class SampleController {
	//http://localhost:8081/sample/basic
	@RequestMapping("/basic")
	public void basic() {
		log.info("basic ��û.....");
	}
	
}
