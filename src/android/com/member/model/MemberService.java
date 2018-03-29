package android.com.member.model;

public class MemberService {
	
	private MemberDAO_interface dao;
	
	public MemberService(){
		dao=new MemberDAO();
	}
	public Boolean isMember(String account,String password){
		return dao.isMember(account, password);
		
	}

	
	
	
	
	
	

}
