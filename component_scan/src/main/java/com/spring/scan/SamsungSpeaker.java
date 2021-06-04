package com.spring.scan;

import org.springframework.stereotype.Component;

@Component("speaker2")
public class SamsungSpeaker implements Speaker{
	public SamsungSpeaker() {
		System.out.println("=== SamsungSpeaker ��ü ����");
	}
	

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		System.out.println("=== SamsungSpeaker soundUp");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		System.out.println("=== SamsungSpeaker soundDown");
	}
	
}
