package basics;

import java.io.FileReader;

public class Common {
 public static String readFile(String filename) {
	 String content = "";
	 try {
		FileReader reader = new FileReader(filename);
		
		int loc = 0;
		while((loc = reader.read()) != -1) {
			content += (char)loc;
		}
		reader.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	 return content;
 }
}
