package basics;
import java.sql.*;//Package used for accessing JDBC APIs....

import com.mysql.cj.protocol.Resultset;

//JDBC stands for java database connectivity based on ODBC of Windows OS.  jdbc is a set of apis for performing data centric operations using SQL as the language. JDBC has classes to connect the database, commands to execute and read the results obtained from the database. These APIS will encapsulate lot of IPC features and code interactions with SQL Language. 
//JDBC uses high level APIs called Drivers to interact with the database. These drivers are vendor specific. These Apis are distributed in the JAR Files. U could download the JAR files from the Mysql Website. The JAR Files must be included into the Project settings folder..
//The jar file has to be referenced in the project by choosing project->preferences->java build path->libraries->Choose the external jar files and browse to the location of the jar file. All classes in java are distributed in the form of JAR files(Java ARchive).

public class JDBCProgram {
	final static String url = "jdbc:mysql://localhost/dacexample";
	final static String user = "root";
	final static String pwd = "Mysql@123";
	final static String select = "SELECT * FROM EMPTABLE";
	
	/*
	 * Connection: Used to connect the database and disconnect from it. 
	 * Statement: Used to provide SQL Statements to the connection.
	 * ResultSet: object used to obtain the result of the SELECT Statement and read the result. ResultSet has a CURSOR object in it which points to the first row of the Result set. U could move the cursor to the next row by using an API called next() which returns true if there is a row, else false if no rows are present...
	 * */
	static Connection getConnection() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");//Using REFLECTION...
		Connection con = DriverManager.getConnection(url, user, pwd);
		return con;
	}
	public static void main(String[] args) {
		try {
			//readRecords();
			//insertRecord("Banu Prakash", "New Delhi", 70000);
			//deleteRecord(3);
			//updateRecord(2,"Sunder R","Chennai", 34000);
		}catch(Exception ex) {
			MyConsole.print(ex.getMessage());
		}
	}
	private static void updateRecord(int i, String string, String string2, double j) {
		String query = "Update EmpTable Set EmpName =?, EmpAddress=?, EmpSalary = ? Where EmpID = ?";
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, string);
			ps.setString(2, string2);
			ps.setDouble(3, j);
			ps.setInt(4, i);
			ps.executeUpdate();
		} catch (SQLException e) {
			MyConsole.print(e.getMessage());
		} catch (Exception e) {
			MyConsole.print(e.getMessage());
		}
		
	}
	//We use preparedStatement as a better way of writing SQL Statements. it allows to take args which are variables to be passed into the query. They are represented by ? in the query. The PreparedStatement object has methods to store the values into the placeholders of the query through index starting with 1.
	private static void deleteRecord(int i) {
		try {
			Connection con = getConnection();
			String deleteQuery = "DELETE From EmpTable where EmpID = ?";
			PreparedStatement ps = con.prepareStatement(deleteQuery);
			ps.setInt(1, i);//set the value to the 1st ? of the query
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected != 1)
				MyConsole.print("Failed to delete the record");
		} catch (Exception e) {
			MyConsole.print(e.getMessage());
		}
		
	}
	private static void insertRecord(String name, String address, double salary) {
		try {
			Connection con = getConnection();
			Statement query = con.createStatement();
			String insert = String.format("insert into emptable(EmpName, EmpAddress, EmpSalary) values('%s','%s', %f)", name, address, salary);
			int rowsAffected = query.executeUpdate(insert);
	//executeUpdate is a function of the Statement class used to perform insertion, deletion and updation. In these cases, the data is not retrieved from the database like a SELECT statement. So U don't need a resultset. JDBC gives this function to perform one way communication of passing data into the database. However, U have a return value from the function which gives the no of rows affected by this query.		
		} catch (Exception e) {
			MyConsole.print(e.getMessage());
		}
		
	}
	private static void readRecords() throws Exception{
		Connection con = getConnection();
		if(con == null) MyConsole.print("Connection failed to establish");
		Statement query = con.createStatement();
		ResultSet rs = query.executeQuery(select);
		while(rs.next()) {
			MyConsole.print(rs.getInt(1));
			MyConsole.print(rs.getString(2));
			MyConsole.print(rs.getString(3));
			MyConsole.print(rs.getString(4));
			MyConsole.print("---------------------"); 
		}
	}

}
