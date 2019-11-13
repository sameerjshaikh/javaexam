package basics;
/*
 *Before Java 8, interfaces could not have implementation methods. But from JDK 8.0, we can now add default implementations for interface methods. It means that U could provide simple implementations to one or more methods to which the implementor class need not implement those methods. This is required in scenarios where an interface is already published and new requirement comes to the interface. In this case, the interface is added with new method and a common implementation so that the existing class that has already implemented will not break down.... 
 *In JDK 8, we could create static methods for the interface
 *Functional interface are interfaces are interfaces which contain only one method in it. 
 * */
@FunctionalInterface
interface IFunctionalInterface{
	void OneFunc();//U can have any no of defualt methods if required...
	//void TestFunc();
	default void another() { MyConsole.print("Another func");}
}

interface ISum{
	double addFunc(double v1, double v2);
}
interface ITest{
	void testFunc();//Non implemented methods, std in an interface
	default void retestFunc() {//New feauture applied on existing interface...
		MyConsole.print("Conducted only if the testFunc fails");
	}
	//New in Java 8.0, static methods are not the part of the inheritance hierarchy
	static void staticMethod() {
		MyConsole.print("Static method");
	}
}
//Functional interfaces and lamdba Expression in java...
class TestImplementor implements ITest{
	public void testFunc() {
		MyConsole.print("test func");
	}
}

public class NewInterfaceFeatures {
	public static void main(String[] args) {
		/*
		 * ITest t = new TestImplementor(); t.testFunc();
		 * 
		 * ITest t2 = new TestImplementor(); t2.retestFunc(); t2.testFunc();
		 * 
		 * ITest.staticMethod();//Calling the static method by the name of the
		 * interface...
		 */		
			
			IFunctionalInterface fn = () -> {
				MyConsole.print("One Function implemenation only");
			};
			fn.OneFunc();
			fn.another();
			
			ISum op = (double v1, double v2)->{
				return v1 + v2;
			};
			MyConsole.print(op.addFunc(123, 234));
	}
}
