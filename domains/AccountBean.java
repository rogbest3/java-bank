package com.bitcamp.domains;

public class AccountBean {
	private String accountNum, today;
	private int money;
	
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public String getToday() {
		return today;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getMoney() {
		return money;
	}
	@Override
	public String toString() {
		return "계좌정보 [ 계좌번호 = " + accountNum
							+ ", 가입일 = " + today
							+ ", 금액 = " + money
							+ "원 ]";
	}
	
/**
	@Override
	public String toString() {
		return String.format("계좌번호 : %s\n"
							+ "가입일 : %s\n"
							+ "입금액 : %d원\n"
							, accountNum, today, money);
	}
*/
}
