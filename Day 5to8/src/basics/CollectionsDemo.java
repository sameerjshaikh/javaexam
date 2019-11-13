package basics;

//Collections are an important part of the Java Application development as it involves a lot of data structures that can be used to make an Application. 
//Arrays are fixed in size, so in real time apps, it cannot be used where the data is dynamically added, removed and modified. Java provides Collection classes grouped under the package called java.util and called as Collections Framework.
//Available in 2 versions: Generic and Non Generic versions. Our course focusses on Generic version. Generic version is similar to the STL of CPP. It is more specific, type safe and easy to code with a need to box or unbox the objects. Non generic Collections store the data as object which U must unbox to read the data.
//All collection classes are implementing a set of interfaces which provide some of the common operations for all the collection classes.
/*
 * List: Provides the basic collection capabilities. 
 * Set: Provides unique members within the collection
 * Map: Provides the data in the form key-value pairs where key is unique to the collection. 
 * */

/*The important classes of Collections:
 * ArrayList: Works like Array with data inserted to the bottom of the list, could be deleted, inserted anywhere in the list. 
 * HashSet: Similar to ArrayList but stores only unique data. This class will implement Set interface
 * HashMap: Stores the data in the form of key- value pairs. Key is unique for the collection but value may not be unique for the collection.
 * Queue which stores the data in the form of First In First out basis where in this case, U cannot add, remove elements in b.w the collection.  
 * */
import java.util.*;
import java.util.concurrent.ConcurrentHashMap.KeySetView;
public class CollectionsDemo {

	public static void main(String[] args) {
		//arrayListExample();
		//setExample();
		//customSetExample();
		//mapExample();
		queueExample();
	}

	private static void queueExample() {
		Queue<String> cart = new LinkedList<String>();
		cart.add("Apples");
		cart.add("PineApples");
		cart.add("Mangos");
		MyConsole.print(cart);
		//U can only remove the first item in the queue...
		MyConsole.print("The Item removed is : " + cart.remove());
		MyConsole.print("Now the first item in the cart is " + cart.peek());
	}

	private static void mapExample() {
		//Dictionary concept of php. 
		Map<String, String> users = new HashMap<String, String>();
		try {
			users.put("phaniraj", "welcome123");//The uniqueness of the key is determined by the hashCode and the equals method of the object that is stored as key....
			users.put("kumar", "apple123");
			users.put("gopal", "test123");
			users.put("sumath", "simple123");
		} catch (IllegalArgumentException e) {
			MyConsole.print(e.getMessage());
		}
		MyConsole.print("The no of users: " + users.size());
		Set<String> keys = users.keySet();
		for(String key : keys) {//Displays keys and its associated values....
			MyConsole.print("Username:" + key);
			MyConsole.print("Password: " + users.get(key));
		}
		MyConsole.print(users);//Displays all the elements as a string representation
	}

	private static void customSetExample() {
		Set<Student> dac = new HashSet<Student>();
		dac.add(new Student(123,"Phaniraj",560));
		dac.add(new Student(123,"Phaniraj",560));
		dac.add(new Student(123,"Phaniraj",560));
		dac.add(new Student(123,"Phaniraj",560));
		dac.add(new Student(123,"Phaniraj",560));
		for(Student s : dac) {
			MyConsole.print(s.hashCode());
		}
		MyConsole.print("The total no of students: " + dac.size());
		/*
		 * Set<int[]> scores = new HashSet<int[]>(); scores.add(new int[]
		 * {45,56,5,45,45,45}); scores.add(new int[] {45,56,5,45,45,45}); scores.add(new
		 * int[] {45,56,5,45,45,45}); scores.add(new int[] {45,56,5,45,45,45});
		 * scores.add(new int[] {45,56,5,45,45,45}); scores.add(new int[]
		 * {45,56,5,45,45,45});
		 * 
		 * for(int [] arg : scores) { for(int x : arg) { System.out.print(x + " "); }
		 * System.out.println(""); }
		 */
	}

	private static void setExample() {
		Set<String>basket = new HashSet<String>();
		basket.add("Apple");
		basket.add("PineApple");
		basket.add("Mango");
		basket.add("Orange");
		if(!basket.add("Mango"))
			MyConsole.print("Duplicate");//it will not add the elements as its duplicate...
		basket.add("Orange");
		MyConsole.print(String.format("The No of Elements:%d", basket.size()));
		for (String string : basket) {
			MyConsole.print(string);
		}
		
	}

	private static void arrayListExample() {
		ArrayList<String> data = new ArrayList<String>();
		data.add("Apple");
		MyConsole.print("The no of elements: " + data.size());//size gets the no of elements.
		data.add("Mango");
		MyConsole.print("The no of elements: " + data.size());
		data.add("Orange");
		MyConsole.print("The no of elements: " + data.size());
		data.add("PineApple");
		MyConsole.print("The no of elements: " + data.size());
		data.add("Grapes");
		MyConsole.print("The no of elements: " + data.size());
		data.add("Custard Apples");
		MyConsole.print("The no of elements: " + data.size());
		data.add(4, "Gauva");//Inserting the data in b/w the collection...
		MyConsole.print("The no of elements: " + data.size());
		//Iterating thro the elements of the collection...
		for (String string : data) {
			MyConsole.print(string);
		}
		
		data.remove(3);
	}

}
