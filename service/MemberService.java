package com.bitcamp.service;
import com.bitcamp.domains.MemberBean;
import com.bitcamp.domains.CustomerBean;
import com.bitcamp.domains.AdminBean;
public interface MemberService {
												// 작업 순서
	public void join(CustomerBean param);		// create
	public void register(AdminBean param);

	public CustomerBean[] findAllCustomers();
	public AdminBean[] findAllAdmins();
	public int countCustomers();
	public int countAdmins();
	
	public MemberBean findById(String id);
	public MemberBean[] findByName(String name);
	public boolean login(MemberBean param);
	public boolean existId(String id);
	
	public void updatePass(MemberBean param);	// update
	public void deleteMember(MemberBean param);
	
	public int getSameCount();
	


}
