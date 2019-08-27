package com.bitcamp.controllers;
import javax.swing.JOptionPane;
import com.bitcamp.service.AccountService;
import com.bitcamp.serviceImpl.AccountServiceImpl;
import com.bitcamp.domains.AccountBean;
public class AdminController {
	public static void main(String[] args) {
		AccountService accountService = new AccountServiceImpl();
		AccountBean accountBean = null;
		AccountBean[] accountBeans = null;
		String[] arr = null;
		while(true) {
			switch (JOptionPane.showInputDialog("0.종료 1.계좌생성 2.계좌번호 목록 3.계좌번호 조회 4.계좌번호 삭제 5.보증금 6.인출")) {
			case "0" :
				JOptionPane.showMessageDialog(null, "종료");
				return;
			case "1" :
				accountService.createAccount(Integer.parseInt(JOptionPane.showInputDialog("금액")));
				break;
			case "2" :
				String string = "";
				int num = accountService.countAccounts();
				accountBeans = new AccountBean[num];
				accountBeans = accountService.findAll();
				for(int i=0; i<num; i++) {
					string += accountBeans[i] + "\n";
				}
				System.out.println(string);
				JOptionPane.showMessageDialog(null, string);
				break;
				
			case "3" :
				accountBean = new AccountBean();
				accountBean = accountService.findByAccountNum(JOptionPane.showInputDialog("계좌번호"));
				if(accountBean.getAccountNum().equals(null)) {
					
				}else {
					JOptionPane.showMessageDialog(null, accountBean.toString());
				}
		
			case "4" :
				accountService.deleteAccountNum(JOptionPane.showInputDialog("계좌번호"));
				break;
			case "5" :
				arr = JOptionPane.showInputDialog("계좌번호, 보증금").split(",");
				accountBean = new AccountBean();
				accountBean.setAccountNum(arr[0]);
				accountBean.setMoney(Integer.parseInt(arr[1]));
				accountService.depositMoney(accountBean);
				break;
			case "6" :
				arr = JOptionPane.showInputDialog("계좌번호, 인출금액").split(",");
				accountBean = new AccountBean();
				accountBean.setAccountNum(arr[0]);
				accountBean.setMoney(Integer.parseInt(arr[1]));
				accountService.withdrawMoney(accountBean);
				break;
			}
		}
	}
}
