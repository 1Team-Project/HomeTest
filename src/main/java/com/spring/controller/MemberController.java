package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/member/*")
public class MemberController {

	@GetMapping("/login")
	public String loginGet() {
		log.info("로그인 폼 요청");
		
		return "/member/loginForm";
	}
	
	@GetMapping("/admin-page")
	public String adminPage() {
		log.info("admin");
		return "/member/AdminPage";
	}
	
	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError","아이디나 비밀번호가 올바르지 않습니다.");
		
		return "/member/loginForm";
	}
	
}
