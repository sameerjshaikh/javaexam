package recap;
import java.sql.*;

import basics.MyConsole;

public class JDBCRecap {
	final static String url = "jdbc:mysql://localhost/dacexample";
	final static String user = "root";
	final static String pwd = "Mysql@123";
	final static String select = "SELECT * FROM EMPTABLE";
	
	private static Connection getConnection() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, pwd);
		return con;
	}
	public static void main(String[] args) {
		//usingStatement();
		usingPreparedStatement();
	}
	private static void usingPreparedStatement() {
		try {
			Connection con = getConnection();
			String name = MyConsole.getString("Enter the Name");
			String address = MyConsole.getString("Enter the Address");
			double salary = MyConsole.getDouble("Enter the salary");
			String insert = "Insert into EmpTable(EmpName, EmpAddress, EmpSalary) values(?,?,?)";
			PreparedStatement st  =con.prepareStatement(insert);
			st.setString(1, name);
			st.setString(2, address);
			st.setDouble(3, salary);
			st.executeUpdate();
			//PreparedStatement is more professional and convenient way of doing the JDBC Code and it will work well for Stored Procedure. 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void usingStatement() {
		
		
		
	}

}
