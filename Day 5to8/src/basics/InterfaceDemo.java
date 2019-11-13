package basics;
/*Points:
 * Interfaces are similar to abstract classes but will contain only abstract methods in them.
 * Interfaces contains declarations but no implementations. Its more like a plan and implementation done thro classes that implement the interface.
 * A Class that implements the interface must implement all the methods of the interface in the public scope only, else the class must be marked as abstract. 
 * In java classes are extended and interfaces are implemented.  
 * Interfaces provide a clean abstraction from its implementation. Many frameworks are built on the concept of interfaces only.
 * U cannot have implementations in an interface, however U could create fields for the interfaces but it must be either final, static and public.
 * interface members are always public, so no need to specify access specifier within the interface declaration. But the class that implements the interface must specify the public for the methods. 
 * A class can implement multiple interfaces at the same level. 
 * */
public class InterfaceDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Example ex = new ExampleImpl();
		ex.testExample();
		
		Simple sim = new ExampleImpl();
		sim.simpleExample();
	}
}

//interface members are public....
interface Example{
	static int exId = 123;
	void testExample();
}

interface Simple{
	void simpleExample();
}

class ExampleImpl implements Example, Simple{
	@Override
	public void testExample() {
		MyConsole.print("test Func implemented here");		
	}

	@Override
	public void simpleExample() {
		MyConsole.print("simple Func implemented here");
	}	
}
