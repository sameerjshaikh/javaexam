package basics;

import java.util.*;/*ArrayList is a Collection class that is used to create dynamic Arrays in Java. It has 2 versions: Generic and Non Generic. Generic is more like a Templates of Cpp. ArrayList is more like vector of Cpp
*/

/*Points:
 * Exceptions are abnormal cases where the program fails to execute due some ambiguity or due to some incorrect inputs provided by the user during the program execution. JRE will raise the exception in the form of objects derived from Exception Class. 
 * Exception class is the base class for all exceptions in java. 
 * U could create custom exception classes to meet UR business requirements. 
 * Exceptions are handled with try...catch....finally blocks. 
 * try block contains the code that could raise the Exception. catch block contains the code to handle the exceptions. A try can have multiple catch blocks to catch different kinds of exceptions raised by the program. finally block is that which is used like clean up operation which will be executed on all conditions. 
 * Even though all kinds of exceptions can be caught by the base Exception Class, it is not advised to catch a general Exception. Instead we should catch specific exception to display specific exception messages to the user so that he or she will give the inputs as per the requirements of the Application. 
 * Every try is followed by either a catch, finally or both. 
 * try....catch....finally
 * throw and throws keywords
 * Custom Exceptions. 
 * */
class Student{
	int id;
	String studentName;
	int totalMarks;
	
	
	 @Override public int hashCode() { 
		 return Integer.hashCode(id); }
	 //equals is used to logical equate 2 reference types. String is a reference type.
	@Override
	public boolean equals(Object other) {
		//Unbox the other object..
		Student temp = (Student)other;
		return this.studentName.equals(temp.studentName);
	}
	public Student(int id, String name, int marks) {
		this.id = id;
		studentName =name;
		totalMarks = marks;
	}
	
	@Override
	public String toString() {
		return String.format("%d,%s,%d", id, studentName, totalMarks);
	}
}
class StudentInsertionFailedException extends Exception{
	public StudentInsertionFailedException() {
		
	}
	
	public StudentInsertionFailedException(String message) {
		super(message);//super will call the base class's specific constructor.....
	}
}

class StudentDB{
	private List<Student> _students = new ArrayList<Student>();
	public void addNewStudent(Student s) throws StudentInsertionFailedException {
		if(s == null) {
			throw new StudentInsertionFailedException("Student details are not set");
		}
		_students.add(s);//adds the element to the bottom of the list..
	}
}
public class ExceptionHandling {

	public static void main(String[] args) {
		//simpleExceptionHandling();
		//arrayExample();
		//arithematicExample();
		//handlingNullException();
		//throwsAndThrowExample();
		StudentDB db = new StudentDB();
		try {
			Student s = new Student(123,"Phaniraj", 567);
		db.addNewStudent(s);
		}catch(StudentInsertionFailedException ex) {
			MyConsole.print(ex.getMessage());
		}
	}

	private static void throwsAndThrowExample() {
	/*
		When a function is expected to raise an exception, the function must have a declaration with a throws keyword followed by the kind of the Exception class that the function might throw. This gives a clear indication to the caller of this method about the possible exception raised by the function and he could handle it appropriately. 
		The caller can handle the exception in 2 ways: a) Surround the fn call with a try..catch and handle/suppress the exception.  b) throw the exception to its caller, there by funneling the exception to the caller to this function. The caller of the current function would appropriately either handle it or funnel it his caller and so forth continue the call stack until the exception is handled. 
		The function must raise the exception in the code using throw keyword. throws is the declaration syntax and throw is the exception raising syntax.  
		*/
	}

	private static void handlingNullException() {
		//NullPointerException would be raised by the JRE if the program tries to access an object which is not instantiated. 
		try {
			Student student = new Student(123, "Phaniraj",567);//The object is not instantiated.
			MyConsole.print(String.format("The Name is %s with score of %d", student.studentName, student.totalMarks));
		} catch (NullPointerException e) {
			MyConsole.print("Object is not instantiated");
		}
	}

	private static void arithematicExample() {
		boolean failed = true;
		do {
			try {
				int first = MyConsole.getNumber("Enter the first value");
				int second = MyConsole.getNumber("Enter the second value");
				int result = first / second;
				MyConsole.print("The Result:" + result);
				failed = false;
			} catch (ArithmeticException e) {
				MyConsole.print("Division by 0 is not allowed");
				failed = true;
			}	
		} while (failed);
		
	}

	private static void arrayExample() {
		boolean validate = true;
		int [] elements = {45,56,66,34,78};
		do {
			try {
				int index = MyConsole.getNumber("Enter the index of the Array to get the value");
				MyConsole.print("The Value at the index " + index + " is " + elements[index]);
				validate = true;
			}catch (InputMismatchException iEx) {
				MyConsole.print("Invalid index no");
				validate = false;
			}catch(ArrayIndexOutOfBoundsException aEx) {
				MyConsole.print("Index is not within the bounds of the Array");
				validate = false;
			}
		}while(validate == false);
		//Java does not have goto statement. We use boolean variable to validate the data and move back to perform the operation if the validation fails. 
	}

	private static void simpleExceptionHandling() {
		System.out.println("Enter an integer to store");
		Scanner sn = new Scanner(System.in);
		try{
			int no = sn.nextInt();
			System.out.println(String.format("The no entered is %d", no));
		}catch(InputMismatchException ex) {
			System.out.println("invalid no");
		}finally {
			System.out.println("Executed on all conditions whether an exception occurs or not.This is the last statement that executes within the try..catch...finally blocks");
		}
	}
}
