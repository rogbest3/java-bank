package com.bitcamp.serviceImpl;
import com.bitcamp.domains.AdminBean;
import com.bitcamp.domains.CustomerBean;
import com.bitcamp.domains.MemberBean;
import com.bitcamp.service.MemberService;
public class MemberServiceImpl implements MemberService {

	// memberBeans[] 사라지고 customerBeans, adminBeans 2EA로 나뉨, 저상소가 1개에서 2개로 나뉨
	private CustomerBean[] customerBeans;
	private AdminBean[] adminBeans;
	private int custCount, adminCount;
	private int sameCount;
	
	public MemberServiceImpl() {
		customerBeans = new CustomerBean[10]; 
		adminBeans = new AdminBean[10];
		custCount = 0;
		adminCount = 0;
		sameCount = 0;
	}
	@Override
	public void join(CustomerBean param) {	//	create
		customerBeans[custCount] = param;
		custCount++;
	}
	@Override
	public void register(AdminBean param) {
		adminBeans[adminCount] = param;
		adminCount++;
	}

	@Override
	public CustomerBean[] findAllCustomers() {
		return customerBeans;
	}

	@Override
	public AdminBean[] findAllAdmins() {
		return adminBeans;
	}

	@Override
	public MemberBean[] findByName(String name) {
		int count1 = 0, count2 = 0;
		sameCount = 0;
		for(int i=0;i<custCount; i++) {					//	customerBean = adminBeans[i]; 둘다 안되기 때문에 
			if(name.equals(customerBeans[i].getName())) {	//	MemberBean으로 사용
				count1++;
			}
		}
		for(int i=0;i<adminCount; i++) {
			if(name.equals(adminBeans[i].getName())) {
				count2++;
			}
		}
		sameCount = count1 + count2;	// sameCount는  memberBeans
		MemberBean[] memberBeans = new MemberBean[sameCount];
		int j = 0;
		for(int i=0;i<custCount; i++) {						//	customerBean = adminBeans[i]; 둘다 안되기 때문에 
			if(name.equals(customerBeans[i].getName())) {	//	MemberBean으로 사용
				memberBeans[j] = customerBeans[i];			//	memberBean에 없는 값은 누락됨.
				j++;
				if(count1 == j) {
					break;
				}
			}
		}
		int k = 0;
		for(int i=0;i<adminCount; i++) {
			if(name.equals(adminBeans[i].getName())) {
				memberBeans[j] = adminBeans[i];
				k++;
				j++;
				if(count2 == k) {
					break;
				}
			}
		}
		return memberBeans;
/**		int num = 0;
		for(int i=0; i<count; i++) {
			if(name.equals(this.memberBeans[i].getName())) {
				num++;
			}
		}
		MemberBean[] memberBeans = new MemberBean[num];
		num = 0;
		for(int i=0; i<count; i++) {
			if(name.equals(this.memberBeans[i].getName())) {
				memberBeans[num] = this.memberBeans[i];
				num++;
				if(memberBeans.length <= num ) {
					break;
				}
			}
		}
	return memberBeans;
*/		
	}
		
	@Override
	public MemberBean findById(String id) {				//	상속 이용 CustomerBean customerBean 는
		MemberBean customerBean = new MemberBean();		//	customerBean = customerBeans[i];
		for(int i=0;i<custCount; i++) {					//	customerBean = adminBeans[i]; 둘다 안되기 때문에 
			if(id.equals(customerBeans[i].getId())) {	//	MemberBean으로 사용
				customerBean = customerBeans[i];
				break;
			}
		}
		for(int i=0;i<adminCount; i++) {
			if(id.equals(adminBeans[i].getId())) {
				customerBean = adminBeans[i];
				break;
			}
		}
		return customerBean;
		
	/**	MemberBean memberBean = new MemberBean();
		for(int i=0; i<count; i++) {
			if(id.equals(memberBeans[i].getId())) {
				memberBean = memberBeans[i];
				break;
			}
		}
		return memberBean;
	*/
	}

	@Override
	public boolean login(MemberBean param) {	//	MemberBean으로 받은 이유는 id, pw이 있다는 가정하에 코딩해야되기 때문에
		// boolean은 컨디션을 리턴해도 됨.				//	findById()에서 id가 있으니까 재사용하여
		return findById(param.getId())
				.getPass()
				.equals(param.getPass());
		
	/**	boolean flag = false;
		MemberBean memberBean = findById(param.getId());	
		if(memberBean.getPass().equals(param.getPass())) {
			flag = true;
		}
		return flag;
	*/	
		
	/**	for(int i=0; i<count; i++) {
			if(param.getId().equals(memberBeans[i].getId())
					&& param.getPass().equals(memberBeans[i].getPass())) {
				flag = true;
				break;
			}
		}
		return flag;
*/	}

	@Override
	public int countCustomers() {
		return custCount;
	}

	@Override
	public int countAdmins() {
		// TODO Auto-generated method stub
		return adminCount;
	}

	@Override
	public boolean existId(String id) {
		boolean flag = false;
		for(int i=0;i<custCount; i++) {					//	customerBean = adminBeans[i]; 둘다 안되기 때문에 
			if(id.equals(customerBeans[i].getId())) {	//	MemberBean으로 사용
				flag = true;
				break;
			}
		}
		for(int i=0;i<adminCount; i++) {
			if(id.equals(adminBeans[i].getId())) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	public void updatePass(MemberBean param) {
		String id = param.getId();
		String[] passwords = param.getPass().split(",");
		String oldPass = passwords[0];
		String newPass = passwords[1];
		param.setPass(oldPass);		//	입력받은 param은 비번이 old와 new가 같이 있기 때문에 pw에 old만 입력한 후 login(param) 실행
		if(login(param)) {
			//findById(param.getId()).setPass(newPass);
			for(int i=0;i<custCount; i++) {					//	customerBean = adminBeans[i]; 둘다 안되기 때문에 
				if(oldPass.equals(customerBeans[i].getPass())) {	//	MemberBean으로 사용
					customerBeans[i].setPass(newPass);
					break;
				}
			}
			for(int i=0;i<adminCount; i++) {
				if(oldPass.equals(adminBeans[i].getPass())) {
					customerBeans[i].setPass(newPass);
					break;
				}
			}
		}
	}

	@Override
	public void deleteMember(MemberBean param) {	//	삭제한 자리에 끝에 데이터를 넣고 count--가 best 코딩
		if(login(param)) {
			for(int i=0;i<custCount; i++) {			
				if(customerBeans[i].getId().equals(param.getId())) {
					customerBeans[i] = customerBeans[custCount-1];	//	덮어쓰기 때문에 초기화 안해도 됨.
					custCount--;
					break;
				}
				
			}
			for(int i=0;i<adminCount; i++) {
				if(adminBeans[i].getId().equals(param.getId())) {
					adminBeans[i] = adminBeans[adminCount-1];
					adminCount--;
					break;
				}
			}
		}
	}
	@Override
	public int getSameCount() {
		return sameCount;
	}
}
