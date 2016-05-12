package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connect {

	private static final String db_ip = "ip";
	private static final String db_name = "db";
	private static final String db_user = "un";
	private static final String db_pw = "pw";	
	
	private static PreparedStatement ps;
	
	public Connect() throws Exception {
		
		//registerUser();
		deleteUserEntry();
		
		
	}
		
	public Connection dbConnect() throws Exception {
		
		Connection conn = null;
		
		try {
			String urlString = "jdbc:mysql://" + db_ip + ":3306/" + db_name + "";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(urlString, db_user, db_pw);
			
			if (conn != null) {
				System.out.println("Connected!");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}	
		return conn;
	}
	
	public void registerUser() throws Exception {
			
		Connection conn = null;
		String query = "insert into login (username, password, email) values (?, ?, ?)";
		
		try {
			conn = dbConnect();
			ps = conn.prepareStatement(query);
			
			ps.setString(1, "namn");
			ps.setString(2, "enamn");
			ps.setString(3, "em@ail");
			
			ps.executeUpdate();
			System.out.println("User inserted");
				
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
	}
		
	public void deleteUserEntry() throws Exception {
				
		Connection conn = null;
		String query = "delete from login where username =?";
		
		try {
			conn = dbConnect();
			ps = conn.prepareStatement(query);
			ps.setString(1, "mia");
			
			ps.executeUpdate();
			System.out.println("User deleted");
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}		
}
