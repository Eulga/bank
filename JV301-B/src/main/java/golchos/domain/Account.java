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
	 * Acc �⺻����
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
	 * Acc ��� ����
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
	
	//���� Ŭ�������� �ö�� ������ �����ϴ� Ŭ����
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

	//�ܰ���ȸ
	public void deposite(double amount) {
		if (amount < 0) {
			System.out.println("�߸��� �Է��Դϴ�.");
		} else {
			this.balance += amount;						
		}
	}

	public void withdraw(double amount) throws InsufficientBalanceException, OverdraftException  {
		if(amount > this.balance) {
			throw new InsufficientBalanceException("�ܰ����");
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
