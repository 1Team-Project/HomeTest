package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.CalcVO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/calc/*")
public class AddController {
	
	@GetMapping("/add")
//	@RequestMapping(value="/add", method=RequestMethod.GET)
	public void add() {
		log.info("add.jsp ��û");
	}
	
	//add.jsp �� ��������
	@PostMapping("/add")
	public void addPost(int num1, int num2) {
		log.info("add �� ��������");
		log.info("num1 :"+num1);
		log.info("num2 :"+num2);
	}
	
	@GetMapping("/four")
	public void fourGet() {
		log.info("four form �����ֱ�");
	}
	
//	@PostMapping("/four")
//	public void fourPost(int num1,String op, int num2) {
//		log.info("four �Է°� ��������");
//		log.info("num1 :" +num1);
//		log.info("num2 :" +num2);
//		log.info("op :"+op);
//	}
	
	@PostMapping("/four")
	public void fourPost(CalcVO calc,@ModelAttribute("page") int page) {
		log.info("four �Է°� ��������");
		log.info("num1 :" +calc.getNum1());
		log.info("num2 :" +calc.getNum2());
		log.info("op :"+calc.getOp());
		
		log.info("page :"+page);
		
		//page ���� ������Ű�� �ʹٸ�?
		//model.addAttribute("page",page);
	}
	
}
