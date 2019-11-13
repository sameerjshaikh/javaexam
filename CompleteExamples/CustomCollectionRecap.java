package basics;

import java.io.*;
import java.util.*;

/*
 * A Class that represent the entity
 * A Class that represent a collection of that entity.
 * Main app that consumes the collection class to perform a CRUD operations...
 * */
class Employee implements Serializable {
	public int empID;
	public String name;
	public String address;
	public double salary;

	public Employee(int id, String name, String address, double salary) {
		empID = id;
		this.name = name;
		this.address = address;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return String.format("%d,%s,%s,%f", empID, name, address, salary);
	}
}

interface IEmpCollection extends Iterable<Employee> {
	void addNewEmployee(Employee emp);

	void updateEmployee(Employee emp) throws Exception;

	void deleteEmployee(int empId) throws Exception;

	List<Employee> getAllEmployees();
}

class EmpSerializationCollection implements IEmpCollection{
	String filename = "Employees.bin";
	List<Employee> employees = new ArrayList<Employee>();
	
	void saveRecords() {
		try {
			FileOutputStream writer = new FileOutputStream(filename);
			ObjectOutputStream os = new ObjectOutputStream(writer);
			os.writeObject(employees);
			os.close();
			writer.close();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void loadRecords() {
		try {
			FileInputStream fs = new FileInputStream(filename);
			ObjectInputStream oin = new ObjectInputStream(fs);
			employees = (List<Employee>)oin.readObject();
			fs.close();
			oin.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	@Override
	public Iterator<Employee> iterator() {
		// TODO Auto-generated method stub
		return employees.iterator();
	}

	@Override
	public void addNewEmployee(Employee emp) {
		employees.add(emp);
		saveRecords();
	}

	@Override
	public void updateEmployee(Employee emp) throws Exception {
		loadRecords();
		for (int i = 0; i < employees.size(); i++) {
			Employee temp =employees.get(i);
			if (temp.empID == emp.empID) {
				temp.name = emp.name;
				temp.address = emp.address;
				temp.salary = emp.salary;
				saveRecords();
				return;
			}
		}
		throw new Exception("Employee not found to update");
	}

	@Override
	public void deleteEmployee(int empId) throws Exception {
		loadRecords();
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).empID == empId) {
				employees.remove(i);
				saveRecords();
				return;
			}
		}
		throw new Exception("Employee not found to delete");
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		loadRecords();
		return employees;
	}
	
}

class EmpCSVCollection implements IEmpCollection {
	private String fileName = "Employees.csv";
	private List<Employee> _employees = new ArrayList<Employee>();

	private void saveRecords() {
		try {
			FileWriter writer = new FileWriter(fileName);
			for (Employee e : _employees) {
				writer.write(e.toString() + "\n");
			}
			writer.flush();
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void loadRecords() {
		_employees = new ArrayList<Employee>();
		try {
			FileReader reader = new FileReader(fileName);
			BufferedReader bf = new BufferedReader(reader);
			String line = "";
			while ((line = bf.readLine()) != null) {
				String[] words = line.split(",");
				int id = Integer.parseInt(words[0]);
				String name = words[1];
				String address = words[2];
				double salary = Double.parseDouble(words[3]);
				Employee emp = new Employee(id, name, address, salary);
				_employees.add(emp);
			}
			bf.close();
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void addNewEmployee(Employee emp) {
		//1loadRecords();
		_employees.add(emp);
		saveRecords();
	}

	@Override
	public void updateEmployee(Employee emp) throws Exception {
		loadRecords();
		for (int i = 0; i < _employees.size(); i++) {
			Employee temp = _employees.get(i);
			if (temp.empID == emp.empID) {
				temp.name = emp.name;
				temp.address = emp.address;
				temp.salary = emp.salary;
				saveRecords();
				return;
			}
		}
		throw new Exception("Employee not found to update");
	}

	@Override
	public void deleteEmployee(int empId) throws Exception {
		loadRecords();
		for (int i = 0; i < _employees.size(); i++) {
			if (_employees.get(i).empID == empId) {
				_employees.remove(i);
				saveRecords();
				return;
			}
		}
		throw new Exception("Employee not found to delete");
	}

	@Override
	public List<Employee> getAllEmployees() {
		loadRecords();
		return _employees;
	}

	@Override
	public Iterator<Employee> iterator() {
		loadRecords();
		return _employees.iterator();
	}

}

public class CustomCollectionRecap {
	 static IEmpCollection collection = null;
	public static void main(String[] args) {		
		try {
			collection = EmployeeFactory.createCollection(args[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MyConsole.print(e.getMessage());
		}
		 String menu = Common.readFile(args[0]); boolean condition = true; do { int
		 choice = MyConsole.getNumber(menu); condition = processMenu(choice); }
		 while(condition);
		 
		/*
		 * for(Employee emp : collection) { MyConsole.print(emp.address); }
		 */
	}

	private static boolean processMenu(int choice) {
		switch (choice) {
		case 1:
			addingRecord();
			break;
		case 2:
			updatingRecord();
			break;
		case 3:
			deletingRecord();
			break;
		case 4:
			findAllRecords();
			break;
		default:
			return false;
		}
		return true;
	}

	private static void findAllRecords() {
		//ask the user the name or part of name to search
		String name = MyConsole.getString("Enter name or part of name to search");
		//call the getall employees
		List<Employee> all = collection.getAllEmployees();
		//iterate thro the employees and display those names matching the condition
		for (Employee employee : all) {
			if(employee.name.contains(name))
				MyConsole.print(employee);
		}
	}

	private static void deletingRecord() {
		//take the id to delete...
		int id = MyConsole.getNumber("Enter Employee ID to delete");
		try {
			collection.deleteEmployee(id);//call the collection to delete.
		} catch (Exception e) {//handle exceptions if any
			MyConsole.print(e.getMessage());
		}
	}

	private static void updatingRecord() {
		//take inputs from the user
		int id = MyConsole.getNumber("Enter Employee ID to Update");
		String name = MyConsole.getString("Enter the name to update");
		String address = MyConsole.getString("Enter the Address to update");
		double salary = MyConsole.getDouble("Enter the salary to update");
		//create the employee object
		Employee emp = new Employee(id, name, address, salary);
		//pass this object to the update function and handle exceptions if any...
		try {
			collection.updateEmployee(emp);
		} catch (Exception e) {
			MyConsole.print(e.getMessage());
		}
		
	}

	private static void addingRecord() {
		//take inputs from the user
		int id = MyConsole.getNumber("Enter Employee ID to add");
		String name = MyConsole.getString("Enter the name");
		String address = MyConsole.getString("Enter the Address");
		double salary = MyConsole.getDouble("Enter the salary");				
		//create the employee object
		Employee emp = new Employee(id, name, address, salary);
		//call the addEmployee method of the collection..
		collection.addNewEmployee(emp);
	}
}
