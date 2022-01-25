package golchos.domain;

//적금계좌?
public class SavingsAccount extends Account {
	private double interestRate;
	
	public SavingsAccount(double initBalance, double interestRate) {
		super(initBalance);
		this.interestRate = interestRate;
	}
	
	//계좌정보제공용
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
