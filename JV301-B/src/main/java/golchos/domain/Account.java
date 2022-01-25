package golchos.domain;

import java.util.Date;

import golchos.exception.InsufficientBalanceException;
import golchos.exception.OverdraftException;

public class Account {
	private long id;
	private User user;
	private double interestrate;
	private double overamount;
	private String accNumber;
	private Date regDate;
	
	protected double balance;
	
	
	public Account() {
		super();
	}
	
	/**
	 * Add_Account
	 */
	public Account(double balance) {
		super();
		this.balance = balance;
	}
	
	/**
	 * Acc 기본정보
	 */
	public Account(User user, double balance, double interestrate, double overamount, Date regDate, String accNumber) {
		super();
		this.user = user;
		this.interestrate = interestrate;
		this.overamount = overamount;
		this.regDate = regDate;
		this.accNumber = accNumber;
		this.balance = balance;
	}
	
	/**
	 * Acc 모든 정보
	 */
	public Account(long id, User user, double balance, double interestrate, double overamount, String accNumber, Date regDate) {
		super();
		this.id = id;
		this.user = user;
		this.balance = balance;
		this.interestrate = interestrate;
		this.overamount = overamount;
		this.accNumber = accNumber;
		this.regDate = regDate;
	}
	
	//하위 클래스에서 올라온 정보를 저장하는 클래스
	public Account(Account acc) {
		super();
		this.id = acc.getId();
		this.user = acc.getUser();
		this.balance = acc.getBalance();
		this.interestrate = acc.getInterestrate();
		this.overamount = acc.getOveramount();
		this.accNumber = acc.getAccNumber();
		this.regDate = acc.getRegDate();
	}

	//잔고조회
	public void deposite(double amount) {
		if (amount < 0) {
			System.out.println("잘못된 입력입니다.");
		} else {
			this.balance += amount;						
		}
	}

	public void withdraw(double amount) throws InsufficientBalanceException, OverdraftException  {
		if(amount > this.balance) {
			throw new InsufficientBalanceException("잔고부족");
		} else {
			this.balance -= amount;			
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getInterestrate() {
		return interestrate;
	}

	public void setInterestrate(double interestrate) {
		this.interestrate = interestrate;
	}

	public double getOveramount() {
		return overamount;
	}

	public void setOveramount(double overamount) {
		this.overamount = overamount;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
