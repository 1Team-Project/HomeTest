package com.spring.except;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class CommonException {
//	
//	@ExceptionHandler(Exception.class)
//	public String except() {
//		log.info("Exception ¹ß»ý...");
//		
//		return "except/error";
//	}
}
