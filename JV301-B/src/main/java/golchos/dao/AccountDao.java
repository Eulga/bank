package golchos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import golchos.domain.Account;
import golchos.domain.CheckingAccount;
import golchos.domain.SavingsAccount;
import golchos.domain.User;
import golchos.exception.InsufficientBalanceException;
import golchos.exception.OverdraftException;

public class AccountDao {
	private DataSource ds;
	
	public AccountDao() {
		ds = DataSource.getInstance();
	}
	
	/**
	 * 계좌 등록
	 * @param acc
	 */
	public Account addAccount(User user, Account acc) {
		String sql = null;
		SavingsAccount svAcc = null;
		CheckingAccount chAcc = null;
		
		if(acc instanceof SavingsAccount) {
			sql = "INSERT INTO ACCOUNT(UID, BALANCE, ACCTYPE, INTERESTRATE, ACCNUMBER, REGDATE) VALUES (?, ?, ?, ?, ?, ?)";
			svAcc = (SavingsAccount)acc;
		} else {
			sql = "INSERT INTO ACCOUNT(UID, BALANCE, ACCTYPE, OVERAMOUNT, ACCNUMBER, REGDATE) VALUES (?, ?, ?, ?, ?, ?)";
			chAcc = (CheckingAccount)acc;
		}
		
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);)	
		{
			pstmt.setLong(1, user.getUid());
			pstmt.setDouble(2, acc.getBalance());
			
			if(acc instanceof SavingsAccount) {
				pstmt.setString(3, svAcc.toString());
				pstmt.setDouble(4, svAcc.getInterestRate());
			} else {
				pstmt.setString(3, chAcc.toString());
				pstmt.setDouble(4, chAcc.getOverdraftAmount());
			}
			
			pstmt.setString(5, acc.getAccNumber());
			pstmt.setDate(6, new java.sql.Date(acc.getRegDate().getTime()));
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(acc instanceof SavingsAccount) {
			return svAcc;
		} else {
			return chAcc;
		}
	}
	
	/**
	 * 고객의 전계좌 조회
	 * UID BALANCE ACCTYPE INTERESTRATE OVERAMOUNT ACCNUMBER REGDATE
	 */
	public List<Account> getAllAccount(User user) {
		String sql = "SELECT * FROM ACCOUNT WHERE UID = ?";
		
		List<Account> acclist = new ArrayList<>();
		
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);)	
		{
			pstmt.setLong(1, user.getUid());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				long id = rs.getLong("ID");
				long uid = rs.getLong("UID");
				double balance = rs.getDouble("BALANCE");
				String accType = rs.getString("ACCTYPE");
				double interestrate = rs.getDouble("INTERESTRATE");
				double overamount = rs.getDouble("OVERAMOUNT");
				String accnumber = rs.getString("ACCNUMBER");
				Date regdate = new java.util.Date(rs.getDate("REGDATE").getTime());

				Account acc = new Account(id, new User(uid), balance, interestrate, overamount, accnumber, regdate);
				//계좌종류에 계좌정보를 넣는 셀프클래스? 를 만들어서 super로 계좌정보를 담을거 생성해보자
				if(accType.equals("S")) {					
					acclist.add(new SavingsAccount(interestrate, acc));
				} else {
					acclist.add(new CheckingAccount(overamount, acc));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acclist;
	}
	
	/**
	 * 조건부 계좌 조회
	 * 계좌번호로 조회
	 */
	
	public Account findAccountByAccNumber(String accNum) {
		String sql = "SELECT * FROM ACCOUNT WHERE ACCNUMBER = ?";
		
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);)	
		{
			pstmt.setString(1, accNum);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				long id = rs.getLong("ID");
				long uid = rs.getLong("UID");
				double balance = rs.getDouble("BALANCE");
				String accType = rs.getString("ACCTYPE");
				double interestrate = rs.getDouble("INTERESTRATE");
				double overamount = rs.getDouble("OVERAMOUNT");
				String accnumber = rs.getString("ACCNUMBER");
				Date regdate = new java.util.Date(rs.getDate("REGDATE").getTime());

				Account acc = new Account(id, new User(uid), balance, interestrate, overamount, accnumber, regdate);
				
				if(accType.equals("S")) {
					return new SavingsAccount(interestrate, acc);
				} else {
					return new CheckingAccount(overamount, acc);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateAccount(Account acc) {
		String sql = "UPDATE ACCOUNT SET BALANCE=?, INTERESTRATE=?, OVERAMOUNT=? WHERE ID=?";
		
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);)	
		{
			pstmt.setDouble(1, acc.getBalance());
			pstmt.setDouble(2, acc.getInterestrate());
			pstmt.setDouble(3, acc.getOveramount());
			pstmt.setLong(4, acc.getId());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
