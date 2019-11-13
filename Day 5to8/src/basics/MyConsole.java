package basics;

import java.util.Date;
import java.util.Scanner;

public class MyConsole {
	private static Scanner sn = new Scanner(System.in);
	static void print(Object data) {
		System.out.println(data);
	}
	
	static String getString(String question) {
		print(question);
		return sn.nextLine();
	}
	
	static double getDouble(String question) {
		print(question);
		double dl = sn.nextDouble();
		sn.nextLine();
		return dl;
	}
	
	static int getNumber(String question) {
		print(question);
		int value = sn.nextInt();
		sn.nextLine();
		return value;
	}
	
	static Date getDate() {
		int dd = getNumber("Enter the Day");
		int mm = getNumber("Enter the Month as 0 to 11");
		int yyyy = getNumber("Enter the Year as yyyy");
		return new Date(yyyy,mm,dd);
	}
}
