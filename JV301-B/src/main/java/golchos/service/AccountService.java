package golchos.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import golchos.dao.AccountDao;
import golchos.domain.Account;
import golchos.domain.CheckingAccount;
import golchos.domain.SavingsAccount;
import golchos.domain.User;
import golchos.exception.BalanceInputErrorException;
import golchos.exception.InsufficientBalanceException;
import golchos.exception.NotFoundAccountException;
import golchos.exception.OverdraftException;
import golchos.util.AccountNumGenerator;

public class AccountService {
	private AccountDao adao;
	
	public AccountService () {
		adao = new AccountDao();
	}
	
	public Account addSavingsAccount(double balance, double interestrate, User user) {
		SavingsAccount saAcc = new SavingsAccount(balance, interestrate);
		return addAccount(saAcc, user);
	}
	
	public Account addCheckingAccounts(double balance, double overdraft, User user) {
		CheckingAccount chAcc = new CheckingAccount(balance, overdraft);
		return addAccount(chAcc, user);
	}
	
	private Account addAccount(Account acc, User user) {
		if(acc == null) {
    		throw new RuntimeException("Account is null");
    	}
    	acc.setRegDate(new Date(System.currentTimeMillis()));
    	
    	String random_accnumber = AccountNumGenerator.generateAccountNum();
    	while((adao.findAccountByAccNumber(random_accnumber)) != null) {
    		random_accnumber = AccountNumGenerator.generateAccountNum();
    	}
    	acc.setAccNumber(AccountNumGenerator.generateAccountNum());
    	
    	adao.addAccount(user, acc);
    	System.out.println("계좌개설완료.");
    	return acc;
	}
	
	public Account findAccountByAccnumber(String accnum) throws NotFoundAccountException {
		Account acc = adao.findAccountByAccNumber(accnum);
		
		if(acc == null) {
			throw new NotFoundAccountException("Not Found Account");
		}
		
		return acc;
	}
	
	public List<Account> getAllAccount(User user) throws NotFoundAccountException{
		List<Account> acclist = new ArrayList<>();
		
		acclist = adao.getAllAccount(user);
		if (acclist == null) {
			throw new NotFoundAccountException("Not Found Any Account");
		}
		
		return acclist;
	}
	
	
	public void depositAndwitdraw(Account acc, double dnwbalance, String dnw) throws InsufficientBalanceException, OverdraftException {
		if(dnw.equals("deposit")) {
			acc.deposite(dnwbalance);
		} else {
			acc.withdraw(dnwbalance);
		}
		
		updateAccount(acc);
	}
	
	public void updateAccount(Account acc) {
		adao.updateAccount(acc);
	}
	
	public boolean balanceCheck(double balance) {
		if(balance >= 0) {
			return true;
		}
		return false;
	}
}
