package projectJDBC;
import java.sql.*;//Package used for accessing JDBC APIs....
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;
import java.io.*;

class JDBCProgram
{	
	final static String url="jdbc:mysql://localhost/javaDB";
	final static String user="root";
	final static String pwd="root";
	final static String select="Select * from emptable";
	
	static Connection getConnection() throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con =DriverManager.getConnection(url,user,pwd);
		return con;
	}
	
	public static void main(String[] args) throws Exception
	{
		 Scanner s= new Scanner(System.in);
		 int choice=0;
		 int id;
		 String name,Addr,salary;
		do {
			MyConsole.print("1.Insert Data \n 2.Update Data\n 3.Fetch DATA\n 4.Delete DATA\n 5.Exit ");
			choice=s.nextInt();
			switch(choice)
			{
			case 1: 	
				 name=MyConsole.getString("Enter name");
				 Addr=MyConsole.getString("Enter Addr");
				 salary=MyConsole.getString("Enter salary");
				 insertdata(name,Addr,salary);
				 break;
			case 2: 	
				id=MyConsole.getNumber("Enter Id for Update");
				 name=MyConsole.getString("Enter name");
				 Addr=MyConsole.getString("Enter Addr");
				 salary=MyConsole.getString("Enter salary");
				 updatedata(id,name,Addr,salary);
				 break;	 
			case 3:
				MyConsole.print("ID-NAME-ADDR-SALARY-");
				readdata();
				break;
			case 4:
				id=MyConsole.getNumber("Enter Id for Delete Data");
				deletedata(id);
				break;
				
				
		
			}
			
		}while(choice!=5);
		
	}
	public static void updatedata(int id,String name,String Addr,String salary)
	{
		
		String query="update emptable set EMPNAME=?,EMPADDR=?,EMPSALARY=? where EMPID=?";
		try {
			
			Connection con =getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, Addr);
			ps.setString(3,salary);
			ps.setInt(4, id);
			
			
			int RowsAffected=ps.executeUpdate();
			if(RowsAffected!=1)
			{
			MyConsole.print("Row NOt found");
			}
			else
			{
				MyConsole.print("Data updated");
			}
			
			
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
	public static void insertdata(String name,String Addr,String salary)
	{
		try {
			Connection con=getConnection();
			Statement query=con.createStatement();
			String insert=String.format("insert into emptable(EMPNAME, EmpADDR, EMPSALARY) values('%s','%s', %s)", name, Addr, salary);
			//String insert="insert into emptable(EMPNAME, EmpADDR, EMPSALARY) values(name,Addr,salary)";
			int rowsAffected = query.executeUpdate(insert);
			//int result = query.executeUpdate(insert);
			//query.executeUpdate(insert);
			if(rowsAffected!=1)
			{
				MyConsole.print("Data NOt Inseted");
			
			}
			else
			{
				MyConsole.print("Data Inseted");
			}
			
			
		}
		catch(Exception e)
		{
			MyConsole.print(e);
		}
		}
		

	public static void deletedata(int i)
	{
		try {
			Connection con=getConnection();
			String deleteQuery="Delete from emptable where EMPID=?";
			PreparedStatement ps =con.prepareStatement(deleteQuery);
			ps.setInt(1,i);
			int rowsAffected=ps.executeUpdate();
			if(rowsAffected!=1)
			{
				MyConsole.print("Failed to Deleted Record");
			}
			else
			{
				MyConsole.print("Record Deleted");
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
	}
	
	public static void readdata() throws Exception
	{
		Connection con=getConnection();
		Statement query=con.createStatement();
		ResultSet rs=query.executeQuery(select);
		
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			
		}
		
		
	}
	
}
