package basics;

public class multiThreadingDemo {
//What is a Thread? Process?
	/*
	 * A process is an instance of an application. Each process has its own private address space where UR code, classes objects are created and executed. 
	 * The flow of the execution is called as a Thread. technically its called as PATH OF EXECUTION. A process must have atleast one thread. However, if UR OS supports multiple threads, then U could create multiple paths of execution for pseudo parallel execution. 
	 * Thread that is created at the start of the process is called as the Main Thread, also called as UI Thread. If the Main thread stops, the Process gets killed and the App will be terminated. 
	 * Multi Threading is the feature of the OS and programming languages like CPP and Java will create APIs to create and work upon them.
	 * In Java, a Thread is an instance of a Thread class. A Thread class could be any one which would either extend the Thread class or implement a functional interface called Runnable. 
	 * In a given life cycle of a thread, it has 4 stages: A Thread is born(Instantiated), started(using start), runs(calls the run function) and finally dies(terminated).
	 * Only Runnable objects can run the thread. So U should either create a class that implements Runnable or extend the class called Thread.
	 * While running the Thread could have a waiting time. this is done with sleep method. Sleep is a static method that is makes the thread wait for a timed period. 
	 * U could suspend the thread using suspend method which waits a thread for a infinite amount of time until the caller calls resume
	 *  U could make the main thread terminate the application without waiting for the thread completion. The Thread should be set as daemon thread. This is done by a function called setDaemon(true) which makes the main thread not to wait for the thread completion. We call such threads as BACKGROUND-THREADS.
	 *  UR Thread can take arg of the type Object. 
	 * Any thread created could be suspended, resumed or terminated.
	 * In a multithreaded apps, its good to create variables(DATA) that are thread safe. Typically U could have a scenario where 2 or more threads are accessing the same resource.
	 *  U can overcome this using Synchronization. It could be done either by Critical section, Semaphore, Mutex, event or any other mechanism. Java has classes to represent any of the Synchronization mechanisms.
	 * */
	
		
	public static void main(String[] args)throws Exception {
		//usingRunnableInterface();
		//usingThreadClass();
		MyThreadClass cls = new MyThreadClass();
		cls.start();
		cls.start();
		cls.start();
		cls.start();
		cls.start();
	}

private static void usingThreadClass() {
	MyThreadClass cls = new MyThreadClass();//creating a thread using thread Class..
	cls.start();
	
}

private static void usingRunnableInterface() throws InterruptedException {
	RunnableThread th = new RunnableThread();//Create a Runnable object
	Thread otherThread = new Thread(th);//Pass it to the Thread class
	otherThread.start();//Start the thread
	//Runnable is a functional interface, U could implement it using lambda expression also...
	Runnable obj = ()->{
		try {
		for(int i=0; i <4; i++) {
			MyConsole.print("Using lambda Expression");
			Thread.sleep(5000);
		}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	};
	Thread t2 = new Thread(obj);
	t2.start();
	//Main continues to do its operations....	
	for(int i =0; i <10;i++) {
		MyConsole.print("Main thread execution at beep#" + i);
		Thread.sleep(1000);
	}
	MyConsole.print("The Main has ended, waiting for the thread to complete the task");
}

}

class MyThreadClass extends Thread{
	@Override
	public void run() {
		synchronized (this) {//internally uses mutex to achieve synchronization.
			RunnableThread th = new RunnableThread();
			th.run();
		}
	}
}
//A Class that implements Runnable interface. 
class RunnableThread implements Runnable{

	@Override
	public void run() {//Must be implemented by the Thread class...
		try {
			for (int i = 0; i < 15; i++) {
				MyConsole.print("The Thread with Beep#" + i);
				Thread.sleep(1000);//Suspends the thread for the specific time
			}
		}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

