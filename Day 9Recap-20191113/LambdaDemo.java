package recap;

import java.util.*;

import basics.MyConsole;

@FunctionalInterface
interface ITest{
	int data = 345;
	void testFunc();
}


public class LambdaDemo {
	
	public static void main(String[] args) {
		/*
		 * Java's new way of implementing Anonymous Classes since Java 8: Lambda Expression.....
		 * ITest tst = ()->{ MyConsole.print("Testing Func"); }; tst.testFunc();
		 */
		//Anonymous Inner Classes of Java 4.....
		ITest dataTest = new ITest() {
			@Override
			public void testFunc() {
				MyConsole.print("Testing Func");
			}
		};
		
		olderVersion();	
		//dataTest.testFunc();
	}

	private static void olderVersion() {
		Vector<String> data = new Vector<String>();
		data.add("Apple");
		data.add("Mango");
		data.add("Banana");
		data.add(2, "Gauva");
		
		Iterator<String> it = data.iterator();
		while(it.hasNext())
			MyConsole.print(it.next());
	}

}
