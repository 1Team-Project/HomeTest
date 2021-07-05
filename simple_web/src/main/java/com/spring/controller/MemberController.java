package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.domain.LoginVO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/member/*")
public class MemberController {

   
   @RequestMapping("/register")
   public void register() {
      log.info("register ��û");  
  }   
   @RequestMapping("/modify")
   public String update() {
      log.info("update ��û");
      
      return "member/member_modify";
   }
   
   //@RequestMapping("/login") => ����� �������� �ʾ� get/post  �� �� ����, �Ѵ� ����
   @GetMapping("/login") //http://localhost:8081/member/login
   public void loginGet() {
	   log.info("login ��û"); // 	member/login jsp�� ã�µ� �״�� ����
   }
   
   //������� �Է°� �������� -1) HttpServletRequest req 2) ������(�� �ʵ� �̸��� ��ġ) 3)VO
   
   //1)
//   @PostMapping("/login") //http://localhost:8081/member/login +post
//   public void loginPost(HttpServletRequest req) {
//	   log.info("login form ������ ��������"); // 405�ȶ�	member/login jsp�� ã�µ� �״�� ����
//	   log.info("userid :" +req.getParameter("userid")); 
//	   log.info("password :" +req.getParameter("password")); 
//   }
   
   //2)
//	   @PostMapping("/login") //http://localhost:8081/member/login +post
//	   public void loginPost(@RequestParam("userid")String userid, String password) {
//		   log.info("login form ������ ��������"); // 405�ȶ�	member/login jsp�� ã�µ� �״�� ����
//		   log.info("userid :" +userid);  //login.jsp�� name�� userid, password
//		   log.info("password :" +password); 
//   }
   
   //3)VO
//	   @PostMapping("/login") //http://localhost:8081/member/login +post
//	   public String loginPost(LoginVO vo, Model model) {
//		   log.info("login form ������ ��������"); // 405�ȶ�	member/login jsp�� ã�µ� �״�� ����
//		   log.info("userid :" +vo.getUserid());  //login.jsp�� name�� userid, password
//		   log.info("password :" +vo.getPassword()); 
//		   
//		   model.addAttribute("page",1);
//		   
//		   return "result"; //	forward ��� /	result ������ ���� /WEB-INF/views/result.jsp
//}
	   @PostMapping("/login") 
	   public String loginPost(@ModelAttribute("vo")LoginVO vo, Model model) {
		   //LoginVO vo=>�̸��� �����ϰ� �ʹٸ�?
		   log.info("login form ������ ��������"); 
		   log.info("userid :" +vo.getUserid());  
		   log.info("password :" +vo.getPassword()); 
		   
		   model.addAttribute("page",1);
		   
		   return "result"; 
	}
   
   
}