package basics;
class Temp{
	private class Simple{
		public void simpleFunc() { MyConsole.print("simpleFunc");}
	}
	
	void simple_access() {
		Simple sim = new Simple();
		sim.simpleFunc();
	}
	
}
public class InnerClassDemo {

	public static void main(String[] args) {
		Temp apple = new Temp();
		apple.simple_access();
		/*
		 * Temp.Simple simple =apple.new Simple(); simple.simpleFunc()
		 */;
	}

}
