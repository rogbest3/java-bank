package com.bitcamp.domains;

public class CustomerBean extends MemberBean {
	
	/**
	 * 고객: 신용도 (1등급)
	 * credit
	 */
	private String create;
	
	public void setCreate(String create) {
		this.create = create;
	}
	public String getCreate() {
		return create;
	}
	
	@Override
	public String toString() {
		return "고객정보 [아이디 = " + getId()
							+ ", 비번 = " + getPass()
							+ ", 이름 = " + getName()
							+ ", 주민번호 = " + getSsn()
							+ ", 신용도 = " + create 
							+ " ]";
	
	}
}
