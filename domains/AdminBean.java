package com.bitcamp.domains;

public class AdminBean extends MemberBean {
/**
 * 관리자: 사번( 1234)	랜덤 생성
	sabun	
 */
	private int sabun;
	
	public void setSabun(int sabun) {
		this.sabun = sabun;
	}
	public int getSabun() {
		return sabun;
	}
	@Override
	public String toString() {
		return "사원정보 [ 아이디 = " + getId()
				+ ", 비번 = " + getPass()
				+ ", 이름 = " + getName()
				+ ", 주민번호 = " + getSsn()
				+ ", 사번 = " + sabun
				+ " ]";
	}
}
