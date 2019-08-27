package com.bitcamp.service;
import com.bitcamp.domains.AccountBean;

public interface AccountService {
	public void createAccount(int money);
	public String createAccountNum();
	public String findDate();
	
	public int countAccounts();
	public AccountBean[] findAll();
	public AccountBean findByAccountNum(String accountNum);
	public boolean existAccountNum(String accountNum);
	

	public void depositMoney(AccountBean param);
	public void withdrawMoney(AccountBean param);
	public void deleteAccountNum(String accountNum);
}
