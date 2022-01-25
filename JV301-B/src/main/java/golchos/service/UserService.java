package golchos.service;

import java.util.Date;

import golchos.dao.*;
import golchos.domain.User;
import golchos.exception.*;


public class UserService {
	private UserDao udao = new UserDao();
	
	public UserService() {
		
	}
	
    public User addUser(String userId, String passwd, String userName, String ssn, String email, String addr, String phnum) throws RegisteredInfoException {
    	User user = new User(userId, passwd, userName, ssn, email, addr, phnum);
    	return addUser(user);
    }
    
    private User addUser(User user) throws RegisteredInfoException {
    	if(user == null) {
    		throw new RuntimeException("User is null");
    	}
    	user.setRegDate(new Date(System.currentTimeMillis()));
    	udao.addUser(user);
    	System.out.println("회원가입 되었습니다.");
    	return user;
	}
    
    public User findUser(String userId, String passwd) {
    	User user = udao.findUser(userId, passwd);
    	if(user == null) {
    		throw new RuntimeException("User is null");
    	}
    	return user;
    }
    
    public void updateUser(User user, String passwd, String email, String addr, String phnum) {
		user.setPasswd(passwd);
		user.setEmail(email);
		user.setAddr(addr);
		user.setPhnum(phnum);
		
		udao.updateUser(user);
		System.out.println("회원정보수정완료");
    }

}
