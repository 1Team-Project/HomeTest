package com.spring.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {

	
	private SpUser spUser;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomUser(SpUser spUser) {
		
		super(spUser.getUserid(), spUser.getPassword(), 
				spUser.getAuthorities()
						.stream() //stream���·� �ٲ�
						.map(auth -> new SimpleGrantedAuthority(auth.getAuthority())).collect(Collectors.toList())); //���ϴ� ���·� ����
		this.spUser=spUser;
	}
	
}
