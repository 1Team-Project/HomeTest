package com.spring.service;

import com.spring.domain.MemberVO;

public interface MemberService {
	//�α���, ȸ������, ��й�ȣ����, ȸ��Ż��
	public MemberVO isLogin(String userid, String currentPassword);
	public boolean insert(MemberVO vo);
	public boolean update(String userid, String currentPassword, String changePassword);
	public boolean remove(String userid,String currentPassword);
}
