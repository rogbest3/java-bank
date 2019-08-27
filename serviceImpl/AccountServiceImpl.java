package com.bitcamp.serviceImpl;
import com.bitcamp.domains.AccountBean;
import com.bitcamp.service.AccountService;
import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;

public class AccountServiceImpl implements AccountService{
	private AccountBean[] accountBeans;				// 저장소, 커넥터 만들어서 DB로 정보를 봅아냄
	private int count;						// 멤버 변수에만 private 사용
	
	public AccountServiceImpl() {
		accountBeans = new AccountBean[10];	
		count = 0;
	}

	@Override
	public void createAccount(int money) {
		AccountBean accountBean = new AccountBean();		// 위에 넣고 공유하면 안됨, 메소드를 수행하고 나면 GC가 제거함.
		accountBean.setMoney(money);
		accountBean.setAccountNum(createAccountNum());
		accountBean.setToday(findDate());
		accountBeans[count] = accountBean;
		count++;

	}

	@Override
	public String createAccountNum() {
		Random random = new Random();
	/**	String accounNum = "";
		for(int i=0; i<9; i++) {
			accounNum += (i==4)?"-":random.nextInt(10);
		}
		return accounNum;
	*/
		return String.format("%d-%04d", random.nextInt(8999) + 1000, random.nextInt(9999) + 1);
	}

	@Override
	public AccountBean[] findAll() {
		return accountBeans;
	}

	@Override
	public AccountBean findByAccountNum(String accountNum) {
		AccountBean accountBean = new AccountBean();
		for(int i=0; i<count; i++) {
			if(accountNum
					.equals(accountBeans[i]
					.getAccountNum())) {
				accountBean = accountBeans[i];
				break;
			}
		}
		return accountBean;
	}

	@Override
	public int countAccounts() {
		return count;
	}

	@Override
	public boolean existAccountNum(String accountNum) {
		boolean flag = false;
		for(int i=0; i<count; i++) {
			if( accountNum
				.equals(accountBeans[i]
				.getAccountNum())) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	public String findDate() {
	//	Date date = new Date();
	//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	//	return String.format("%s", sdf.format(date));
		return new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date());
	}

	@Override
	public void depositMoney(AccountBean param) {	//	보증금
		if(existAccountNum(param.getAccountNum())) {
			for(int i=0; i<count; i++) {
				if(param.getAccountNum().equals(accountBeans[i].getAccountNum())) {
					accountBeans[i].setMoney(accountBeans[i].getMoney() + param.getMoney());
				}
			}
		}
	}

	@Override
	public void withdrawMoney(AccountBean param) {	// 인출
		if(existAccountNum(param.getAccountNum())) {
			for(int i=0;i<count;i++) {
				if(param.getAccountNum().equals(accountBeans[i].getAccountNum())
						&& accountBeans[i].getMoney() >= param.getMoney() ) {
					accountBeans[i].setMoney(accountBeans[i].getMoney() - param.getMoney());
				}
			}
		}
		
	}

	@Override
	public void deleteAccountNum(String accountNum) {
		if(existAccountNum(accountNum)) {
			for(int i=0; i<count; i++) {
				if(accountNum.equals(accountBeans[i].getAccountNum())) {
					for( ; i<count-1; i++) {
						accountBeans[i] = accountBeans[i+1];
					}
					count--;
					break;
				}
			}
		}
	}
}
