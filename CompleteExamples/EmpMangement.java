package basics;

class Employee{
	int id; 
	String name, address;
}
interface EmpDB{
	void addNewEmployee(int id, String name, String address);
	void updateEmployee(int id, String name, String address);
	void deleteEmployee(int id);
	Employee[] getAllEmployees();//function called getAllEmployees that returns an Array of Employee....
}

//Middleware component that implements...
class FileDB implements EmpDB {
	@Override
	public void addNewEmployee(int id, String name, String address) {
		MyConsole.print("Employee added to the file");			
	}

	@Override
	public void updateEmployee(int id, String name, String address) {
		MyConsole.print("Employee updated to the file");
	}

	@Override
	public void deleteEmployee(int id) {
		MyConsole.print("Employee deleted from the file");
		
	}

	@Override
	public Employee[] getAllEmployees() {
		// TODO Auto-generated method stub
		MyConsole.print("All Employees obtained from file");
		return new Employee[] {
				
		};
	}
	
}

class MySqlDB implements EmpDB {
	@Override
	public void addNewEmployee(int id, String name, String address) {
		MyConsole.print("Employee added to the MySql DB");			
	}

	@Override
	public void updateEmployee(int id, String name, String address) {
		MyConsole.print("Employee updated to the  MySql DB");
	}

	@Override
	public void deleteEmployee(int id) {
		MyConsole.print("Employee deleted from the  MySql DB");
		
	}

	@Override
	public Employee[] getAllEmployees() {
		// TODO Auto-generated method stub
		MyConsole.print("All Employees obtained from  MySql DB");
		return new Employee[] {
				
		};
	}	
}


class SqlServerDB implements EmpDB {
	@Override
	public void addNewEmployee(int id, String name, String address) {
		MyConsole.print("Employee added to the SqlServer DB");			
	}

	@Override
	public void updateEmployee(int id, String name, String address) {
		MyConsole.print("Employee updated to the  SqlServer DB");
	}

	@Override
	public void deleteEmployee(int id) {
		MyConsole.print("Employee deleted from the  SqlServer DB");
		
	}

	@Override
	public Employee[] getAllEmployees() {
		// TODO Auto-generated method stub
		MyConsole.print("All Employees obtained from  SqlServer DB");
		return new Employee[] {
				
		};
	}
	
}
class EmpFactory{
	static EmpDB createComponent(String storageType) {
		if(storageType.equals("File")) return new FileDB(); else
		 if(storageType.equals("MySQL")) return new MySqlDB(); else return new
		 SqlServerDB();	 
	}
}


//Filename is EmpMangement...
class EmpManagement {
	//field of EmpMangement Class
	static EmpDB empObj = EmpFactory.createComponent("MySQL");
	public static void main(String[] args) {
		empObj.addNewEmployee(123, "Phaniraj", "Bangalore");
		empObj.updateEmployee(123, "Phani raj B.N.", "Bengaluru");
		empObj.deleteEmployee(123);
	}
}
