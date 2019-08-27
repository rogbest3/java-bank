package com.bitcamp.domains;

public class MemberBean {

	private String id, pass, name, ssn;
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPass() {
		return pass;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getSsn() {
		return ssn;
	}
	@Override
	public String toString() {
		return "회원정보 [ 아이디 = " + id
					+ ", 비번 = " + pass
					+ ", 이름 = " + name 
					+ ", 주민번호 = " + ssn
					+ " ]";
	}
}
