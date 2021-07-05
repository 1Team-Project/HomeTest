package com.spring.member;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.domain.MemberVO;
import com.spring.service.MemberService;

public class MemberClient {

	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("config.xml");
		MemberService service=(MemberService)ctx.getBean("service");

		
		
		//ȸ������ insert -1
		System.out.println("-----ȸ������-----");
//		MemberVO vo =new MemberVO();
//		vo.setUserid("user1234");
//		vo.setPassword("1234");
//		vo.setName("������");
//		vo.setGender("��");
//		vo.setEmail("user1234@gamil.com");
//		
//		boolean insertFlag=service.insert(vo);
//		System.out.println(insertFlag?"����":"����");
		
		//ȸ�� ����-2
//		MemberVO vo =new MemberVO();
//		vo.setUserid("user56789");
//		vo.setPassword("5678");
//		vo.setName("������");
//		vo.setGender("��");
//		vo.setEmail("user5678@gamil.com");
//		
//		boolean insertFlag=service.insert(vo);
//		System.out.println(insertFlag?"����":"����");
		
		//��й�ȣ ����
//		System.out.println("-----��й�ȣ ����-----");
//		System.out.println(service.update("user1234", "user", "1234")?"����":"����");
		
		
		//ȸ�� Ż��
		System.out.println("-----ȸ�� Ż��-----");
//		boolean deleteFlag=service.remove("user1234", "1234");
//		System.out.println(deleteFlag?"����":"����");
		
		//�α���,,,,,,,����,,,,,,,,
		System.out.println("-----�α���-----");
		MemberVO vo =new MemberVO();
		vo.setUserid("user1234");
		vo.setPassword("user");
		
		System.out.println(service.isLogin("user1234", "1234") != null?"����":"����");
		
	}

}
