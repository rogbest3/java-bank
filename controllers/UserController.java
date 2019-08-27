package com.bitcamp.controllers;
import javax.swing.JOptionPane;

import com.bitcamp.domains.AdminBean;
import com.bitcamp.domains.CustomerBean;
import com.bitcamp.domains.MemberBean;
import com.bitcamp.service.MemberService;
import com.bitcamp.serviceImpl.MemberServiceImpl;
public class UserController {
	public static void main(String[] args) {
		MemberService memberService = new MemberServiceImpl();
		CustomerBean customerBean = null;
		CustomerBean[] customerBeans = null;
		AdminBean adminBean = null;
		AdminBean[] adminBeans = null;
		MemberBean memberBean = null;
		MemberBean[] memberBeans = null;
		String[] arr = null;

		while(true) {
			switch (JOptionPane.showInputDialog("0.종료\n"
					+ "1.고객 가입\n"
					+ "2.사원 등록\n"
					+ "3.모든 고객 찾기\n"
					+ "4.아이디로 찾기\n"
					+ "5.이름으로 찾기\n"
					+ "6.비번 변경\n"
					+ "7.회원 삭제")) {
			case "0" :
				JOptionPane.showMessageDialog(null, "종료");
				return;
			case "1" :
				arr = JOptionPane
						.showInputDialog("아이디, 비번, 이름, 주민번호")
						.split(",");
				customerBean = new CustomerBean();
				customerBean.setId(arr[0]);
				customerBean.setPass(arr[1]);
				customerBean.setName(arr[2]);
				customerBean.setSsn(arr[3]);
				memberService.join(customerBean);
				break;

			case "2" :
				arr = JOptionPane.showInputDialog("아이디, 비번, 이름, 주민번호, 사번").split(",");
				adminBean = new AdminBean();
				adminBean.setId(arr[0]);
				adminBean.setPass(arr[1]);
				adminBean.setName(arr[2]);
				adminBean.setSsn(arr[3]);
				adminBean.setSabun(Integer.parseInt(arr[4]));
				memberService.register(adminBean);
				break;

			case "3" :
				String string = "";
				int custCount = memberService.countCustomers();
				int adminCount = memberService.countAdmins();
				customerBeans = new CustomerBean[custCount];
				customerBeans = memberService.findAllCustomers();
				for(int i=0; i<custCount; i++ ) {
					string += customerBeans[i].toString() + "\n";
				}
				adminBeans = new AdminBean[adminCount];
				adminBeans = memberService.findAllAdmins();
				for(int i=0; i<adminCount; i++ ) {
					string += adminBeans[i].toString() + "\n";
				}
				System.out.println(string);
				JOptionPane.showMessageDialog(null, string);
				break;

			case "4" :
				memberBean = new MemberBean();
				memberBean = memberService.findById(JOptionPane.showInputDialog("아이디"));
				JOptionPane.showMessageDialog(null, memberBean);
				break;

			case "5" :
				String temp = "";
				memberBeans = new MemberBean[10];
				memberBeans = memberService.findByName(JOptionPane.showInputDialog("이름"));
				int sameCount = memberService.getSameCount();
				for(int i=0;i<sameCount; i++) {
					temp += memberBeans[i] + "\n";
				}
				JOptionPane.showMessageDialog(null, temp);
				break;

			case "6" :
				arr = JOptionPane.showInputDialog("아이디, 기존 비번, 신규 비번").split(",");
				memberBean = new MemberBean();
				memberBean.setId(arr[0]);
				memberBean.setPass(arr[1] + "," + arr[2]);
				memberService.updatePass(memberBean);
				break;
			case "7" :
				arr = JOptionPane.showInputDialog("아이디, 비번").split(",");
				memberBean = new MemberBean();
				memberBean.setId(arr[0]);
				memberBean.setPass(arr[1]);
				memberService.deleteMember(memberBean);
				break;
			}
		}
	}
}
