package basics;
import java.lang.*;
import java.lang.reflect.*;
import java.util.*;
/*Points:
 * Reflection is a process of examining or modifying the runtime behavior of a certain class. The object of the class is instantiated at runtime, so this is called as Late binding. The Class type is also obtained at runtime. Basically we read the metadata of the object which includes info about the object: package details, class details, methods, constructors, fields, callback functions and many more. 
 * All IDEs use Reflection to auto generate code and provide intellisense to the developers. 
 * */
class MathClass{
	public double addFunc(double v1, double v2) {
		return v1 + v2;
	}
	public double subFunc(double v1, double v2) {
		return v1 - v2;
	}
	public double mulFunc(double v1, double v2) {
		return v1 * v2;
	}
	public double divFunc(double v1, double v2) {
		return v1 / v2;
	}
	public void testFunc() {
		MyConsole.print("TestFunc");
	}
}
//Using reflection APIs we will read the info about this class and perform some operations on it...
public class ReflectionDemo {
	public static void main(String[] args) throws Exception{
		Map<String,Integer>functions = new HashMap<String, Integer>();
		/**Using Class.forName function*/
		Class<?> details = Class.forName("basics.MathClass");//U R getting the info about the class called MathClass. Class is a class defined in System.lang.reflect package that represents the metadata of a given class. 
		if(details == null) {
			MyConsole.print("No class info is found for this class");
			return;
		}
		MyConsole.print(details.getClass().getName());		
		Method[] allMethods = details.getMethods();
		for(Method m : allMethods) {
			MyConsole.print(m.getName());
			Parameter[] parameters = m.getParameters();
			functions.put(m.getName(),parameters.length);
			for (Parameter pm : parameters) {
				MyConsole.print(String.format("Name:%s\nParameter Type:%s", pm.getName(), pm.getType().getName()));
			}
		}
		String methodName = MyConsole.getString("Enter the name of the method to invoke");	
		Class[] pms = new Class[2];
		pms[0] = double.class;
		pms[1] = double.class;
		//If the method is overloaded, U must pass the parameters of that version of the method that U want to get the details....
		Method selectedMethod = details.getMethod(methodName, pms);
		if(selectedMethod == null) {
			MyConsole.print("No method found by that name");
			return;
		}
		//create the instance of the object
		Object instance = details.newInstance();//3rd way of creating object in Java.
		Object [] values = new Object[2];
		for(int i =0; i < values.length;i++) {
			Scanner sn = new Scanner(System.in);
			MyConsole.print("Enter the value of the type " + pms[i].getName());
			values[i] = sn.nextDouble();
			sn.nextLine();
		}
		Object result = selectedMethod.invoke(instance, values[0],values[1]);
		MyConsole.print(result);
	}
}
