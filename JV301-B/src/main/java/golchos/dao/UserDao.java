package golchos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import golchos.domain.*;
import golchos.exception.*;


public class UserDao {
	DataSource ds;
	public UserDao() {
		ds = DataSource.getInstance();
	}
	
	/**
	 * 유저 등록
	 */
	public User addUser(User user) throws RegisteredInfoException {
		String sql = "INSERT INTO BANKUSER(USERID, PASSWD, NAME, SSN, EMAIL, ADDRESS, PHNUM, REGDATE) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		
		if((findUserId(user.getUserName(), user.getSsn())) != null) {
			throw new RegisteredInfoException("이미 등록된 정보");
		}
		
		User result = null;
		
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPasswd());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getSsn());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getAddr());
			pstmt.setString(7, user.getPhnum());
			pstmt.setDate(8, new java.sql.Date(user.getRegDate().getTime()));
			pstmt.executeUpdate();
			
			result = findUserId(user.getUserName(), user.getSsn());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 계정확인 아이디 비밀번호
	 */
	public User findUser(String id, String password) {
		String sql = "SELECT * FROM BANKUSER WHERE USERID = ? AND PASSWD = ?";
		User user = null;
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				long uid = rs.getLong("ID");
				String userId = rs.getString("USERID");
				String passwd = rs.getString("PASSWD");
				String userName = rs.getString("NAME");
				String userSsn = rs.getString("SSN");
				String email = rs.getString("EMAIL");
				String addr = rs.getString("ADDRESS");
				String phnum = rs.getString("PHNUM");
				Date regDate = new Date(rs.getDate("REGDATE").getTime());
				
				user = new User(uid, userId, passwd, userName, userSsn, email, addr, phnum, regDate);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	/**
	 * 유저조회 @param id
	 */
	public User findUserByUserId (String id) {
		String sql = "SELECT * FROM BANKUSER WHERE USERID=?";	
		User user = null;
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				long uid = rs.getLong("ID");
				String userId = rs.getString("USERID");
				String passwd = rs.getString("PASSWD");
				String userName = rs.getString("NAME");
				String userSsn = rs.getString("SSN");
				String email = rs.getString("EMAIL");
				String addr = rs.getString("ADDRESS");
				String phnum = rs.getString("PHNUM");
				Date regDate = new Date(rs.getDate("REGDATE").getTime());
				
				user = new User(uid, userId, passwd, userName, userSsn, email, addr, phnum, regDate);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	/**
	 * 비밀번호 찾기 @param id, @param ssn
	 */
	public String findUserPasswd (String id, String ssn) {
		String sql = "SELECT * FROM BANKUSER WHERE USERID=? AND SSN=?";
		
		
		String passwd = null;
		
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.setString(2, ssn);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				passwd = rs.getString("PASSWD");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return passwd;
	}

	/**
	 * 유저조회 이름 주민번호
	 */
	public User findUserId (String name, String ssn) {
		String sql = "SELECT * FROM BANKUSER WHERE NAME=? AND SSN=?";
		
		User user = null;
		
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setString(2, ssn);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				long uid = rs.getLong("ID");
				String userId = rs.getString("USERID");
				String passwd = rs.getString("PASSWD");
				String userName = rs.getString("NAME");
				String userSsn = rs.getString("SSN");
				String email = rs.getString("EMAIL");
				String addr = rs.getString("ADDRESS");
				String phnum = rs.getString("PHNUM");
				Date regDate = new Date(rs.getDate("REGDATE").getTime());
				
				user = new User(uid, userId, passwd, userName, userSsn, email, addr, phnum, regDate);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return user;
	}
	
	public void updateUser(User user) {
		String sql = "UPDATE BANKUSER SET PASSWD=?, EMAIL=?, ADDRESS=?, PHNUM=? WHERE ID=?";
		
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql))
		{
			pstmt.setString(1, user.getPasswd());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getAddr());
			pstmt.setString(4, user.getPhnum());
			pstmt.setLong(5, user.getUid());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}