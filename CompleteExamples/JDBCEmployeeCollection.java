package basics;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JDBCEmployeeCollection implements IEmpCollection {
	final static String url = "jdbc:mysql://localhost/dacexample";
	final static String user = "root";
	final static String pwd = "Mysql@123";
	final static String select = "SELECT * FROM EMPTABLE";
	
	private Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(url, user, pwd);
	}
	@Override
	public Iterator<Employee> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewEmployee(Employee emp) {
		try {
			Connection con = getConnection();
			String query = "Insert into Emptable(EmpName, EmpAddress, EmpSalary) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, emp.name);
			ps.setString(2, emp.address);
			ps.setDouble(3, emp.salary);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void updateEmployee(Employee emp) throws Exception {
		try {
			Connection con = getConnection();
			String query = "Update EmpTable Set EmpName =?, EmpAddress=?, EmpSalary = ? Where EmpID = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, emp.name);
			ps.setString(2, emp.address);
			ps.setDouble(3, emp.salary);
			ps.setInt(4, emp.empID);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void deleteEmployee(int empId) throws Exception {
		throw new Exception("Do it URSELF");
	}

	@Override
	public List<Employee> getAllEmployees() {
		//Create a List
		List<Employee> list = new ArrayList<Employee>();
		try {
			//Connect to database and extract the RecordSet
			Connection con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(select);
			//Iterate thro the record set to create an EMployee for every iteration.
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				double salary = rs.getDouble(4);
				Employee emp = new Employee(id, name, address, salary);
				list.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//add the Employee object to the List
		//return the list...
		return list;
	}

}

class EmployeeFactory{
	public static IEmpCollection createCollection(String arg) throws Exception{
		switch(arg) {
		case "CSV":
			return new EmpCSVCollection();
		case "SER":
			return new EmpSerializationCollection();
		case "MYSQL":
			return new JDBCEmployeeCollection();
		default:
				throw new Exception("Invalid Collection type");
				
		}
	}
}
