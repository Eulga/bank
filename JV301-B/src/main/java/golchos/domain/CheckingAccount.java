package golchos.domain;

import golchos.exception.OverdraftException;

//����ݰ���?
public class CheckingAccount extends Account {
	private double overdraftAmount;
	
	public CheckingAccount(double initBalance, double overdraftAmount) {
		super(initBalance);
		this.overdraftAmount = overdraftAmount;
	}
	
	public CheckingAccount(double overdraftAmount, Account acc) {
		super(acc);
		this.overdraftAmount = overdraftAmount;
	}
	
	public void withdraw(double amount) throws OverdraftException {
		if (balance < amount) {
			double overdratfNeeded = amount - balance;
			if (overdraftAmount < overdratfNeeded) {
				throw new OverdraftException("CA����: ����� �ʰ�");
			} else {
				balance = 0.0;
				overdraftAmount -= overdratfNeeded;
				super.setOveramount(this.overdraftAmount);
				System.out.println("���(over)" + amount);
			}
		} else {
			balance = balance - amount;
			System.out.println("���(c)" + amount);
		}
	}
	
	public double getOverdraftAmount() {
		return this.overdraftAmount;
	}
	
	@Override
	public String toString() {
		return "C";
	}
}
