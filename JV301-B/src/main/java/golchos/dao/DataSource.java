package golchos.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSource {
	private static DataSource dsmanager = new DataSource();
	private String user;
	private String passwd;
	private String url;
	
	private DataSource() {
		
	}
	
	public void initDB(String jdbcDriver, String url, String user, String passwd) {	
		try {
			Class.forName(jdbcDriver);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.url = url;
		this.user = user;
		this.passwd = passwd;
	}
	
	public static DataSource getInstance() {
		return dsmanager;
	}
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, passwd);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
