package basics;
//Inner Classes in Java: Classes can be nested inside a class to provide access security at the class level. Java does not provide access specifiers at class level except public and default. default makes it available for all the files within the package. If U want to create a class that should be private and restricted within the file, then U could create a class within another class and mark it private. 
//Inner classes can be even defined within a method so that its limited to that method itself and is not exposed to other parts of the program. The need to do this kind of classes is for complete encapsulation from the other parts of the program.
//This concept is one form Class Aggregation. Class's HAS-A Relation concept...
class OuterComponent{
	class InnerComponent{
		public void innerFunc() { MyConsole.print("Inner Component's func");}
	}
	
	void invokeInnerObject() {
		InnerComponent com = new InnerComponent();
		com.innerFunc();
	}
}

public class InnerClassDemo {

	public static void main(String[] args) {
		OuterComponent com = new OuterComponent();
		com.invokeInnerObject();
		
		//Creating the object instance of inner component thro Outer COmponent object...
		OuterComponent.InnerComponent iCom = com.new InnerComponent();
		iCom.innerFunc();
	}

}
