package com.spring.mapper;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.MemberVO;

public interface MemberMapper {
	//�α���
	public MemberVO isLogin(@Param("userid")String userid, @Param("currentPassword")String currentPassword);
	//ȸ������
	public int insert(MemberVO vo);
	//��й�ȣ ����
	public int update(@Param("userid")String userid, @Param("currentPassword")String currentPassword, @Param("changePassword")String changePassword);
	//ȸ��Ż��
	public int remove(@Param("userid")String userid,@Param("currentPassword")String currentPassword);
	
}
