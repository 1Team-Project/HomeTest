package com.spring.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Criteria {
	private int pageNum;//����ڰ� Ŭ���ϴ� ������ ��ȣ
	private int amount; //�ϳ��� �������� ������ �Խù� ����
	
	//�˻� �κ� �߰�
	private String type; //�˻����� TCW
	private String keyword; //�˻��� DATABASE
	
	public Criteria() {
		this(1,10);
	}
	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum=pageNum;
		this.amount=amount;
	}
	
	public String[] getTypeArr() {
		//type=> return: null�̸� �� �迭, 
		//		TCW => 	�ƴϸ� string �迭 {"T","C","W"} ������ ���ҽ����� (.split)
		return type==null?new String[] {} :type.split("");
	}
}
