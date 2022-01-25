package golchos.domain;

//���ݰ���?
public class SavingsAccount extends Account {
	private double interestRate;
	
	public SavingsAccount(double initBalance, double interestRate) {
		super(initBalance);
		this.interestRate = interestRate;
	}
	
	//��������������
	public SavingsAccount(double interestRate, Account acc) {
		super(acc);
		this.interestRate = interestRate;
	}
	
	public void accumulateInterest() {
		balance += (balance * interestRate);
	}
	
	public double getInterestRate() {
		return this.interestRate;
	}
	
	@Override
	public String toString() {
		return "S";
	}
}
