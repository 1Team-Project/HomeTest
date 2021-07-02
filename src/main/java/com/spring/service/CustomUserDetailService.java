package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.domain.CustomUser;
import com.spring.domain.MemberVO;
import com.spring.mapper.MemberMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("userid : "+username);
		
		MemberVO member = mapper.read(username);
		
		log.info("인증 정보 : "+member);
		
		return new CustomUser(member);
	}

}
