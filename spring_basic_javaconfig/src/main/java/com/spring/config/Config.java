package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.basic.LgTV;
import com.spring.basic.SamsungTV;
import com.spring.basic.MessageBean;
import com.spring.basic.MessageBeanEn;


@Configuration //������Ʈ�� ���õ� ���� ���� => �ڹٷ� ��ü
public class Config {

	@Bean
	public MessageBean messageBean() {
		/*<bean id="messageBean" class="com.spring.basic.MessageBeanEn"/>*/
		return new MessageBeanEn();
	}
	@Bean
	public LgTV lg() {
		/*<bean id="lg" class="com.spring.basic.LgTV"/>*/
		return new LgTV();
	}
	
	@Bean
	public SamsungTV samsung() {
		/*<bean id="samsung" class="com.spring.basic.SamsungTV"/>*/
		return new SamsungTV();
	}
	
	
}
