package golchos.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long uid;
	private String userId;
	private String passwd;
	private String userName;
	private String ssn;
	private String email;
	private String addr;
	private String phnum;
	private Date regDate;
	
	public User() {
		super();
	}
	
	/**
	 * 유저 정보(일반)
	 */
	public User(String userId, String passwd, String userName, String ssn, String email, String addr, String phnum) {
		super();
		this.userId = userId;
		this.passwd = passwd;
		this.userName = userName;
		this.ssn = ssn;
		this.email = email;
		this.addr = addr;
		this.phnum = phnum;
	}

	/**
	 * 유저의 모든정보
	 * 
	 * 일번정보+
	 * @param uid
	 * @param regDate
	 */
	public User(long uid, String userId, String passwd, String userName, String ssn, String email, String addr, String phnum, Date regDate) {
		super();
		this.uid = uid;
		this.userId = userId;
		this.passwd = passwd;
		this.userName = userName;
		this.ssn = ssn;
		this.email = email;
		this.addr = addr;
		this.phnum = phnum;
		this.regDate = regDate;
	}

	public User(long uid) {
		this.uid = uid;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhnum() {
		return phnum;
	}

	public void setPhnum(String phnum) {
		this.phnum = phnum;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
