package basics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*Java IO is used to perform input/output operations on storage devices like files, memory. 
 * Java uses streams to perform IO operations. Any thing that comes into the application is called input stream and anything that comes out of the app is called the output stream. 
 * Stream is a sequence of data that flows from one end(Source) to another(Destination).
 * Streams are composed of bytes. Everything is bytes, so reading a text file is similar to read a image file. However the data of the image files will be very huge, hence we call them as BLOB (Binary large Objects).
 * By default, java gives 3 streams: System.out, System.in and System.err 
 * in and out are the std input and output streams. err is the error stream used to raise or handle errors within the application.
 * Most of the streams are derived from 2 abstract classes: InputStream and OutputStream. When a stream object is created, it must be opened, worked and closed. Any stream object that is opened must be closed.
 * InputStream->FileInputStream, BufferedInputStream, ObjectInputStream,DataInputStream
 * OUtputStream->Similar Output Counterparts.  
 * */
public class FileIO {

	public static void main(String[] args) throws Exception{
		//basicIOOperations();
		//fileIOOperations();
		String content = Common.readFile(args[0]);
		int choice = MyConsole.getNumber(content);
	}

	private static void fileIOOperations() {
		//For file related operations U have 2 classes called FileWriter and FileReader. These objects are used to perform character specific operations, basically for text based files..
		//fileWritingOperation();	
		//fileReadingOperation();
		//bufferedReaderExample();
	}

	private static void bufferedReaderExample() {
		//The improvised version of the filereader is the bufferedreader which contains additional apis that can be performed on the text based files.
		List<Student> students =null;
		try {
			String filepath = "Sample.txt";
			FileReader reader = new FileReader(filepath);
			BufferedReader bReader = new BufferedReader(reader);
			students = new ArrayList<Student>();
			String line = "";
			while((line = bReader.readLine()) != null) {
				String[] words = line.split(",");
				Student s = new Student(Integer.parseInt(words[0]), words[1], Integer.parseInt(words[2]));
				students.add(s);
			}
			bReader.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Student s : students)
			System.out.println(s.studentName);
	}

	private static void fileReadingOperation() {
		FileReader reader;
		try {
			reader = new FileReader("Sample.txt");
			int index = 0;
			while((index = reader.read()) != -1) {
				System.out.print((char)index);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	private static void fileWritingOperation() {
		try {
			FileWriter writer = new FileWriter("Sample.txt",true);//true appends the file
			int id = MyConsole.getNumber("Enter the ID of the student");
			String name = MyConsole.getString("Enter the name of the Student");
			int marks = MyConsole.getNumber("Enter the total marks of the student");
			Student s = new Student(id, name, marks);
			writer.write(s.toString()+ "\n");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void basicIOOperations() throws Exception {
		System.out.println("Simple message on the std output device(console)");
		System.err.println("OOPs! Something wrong happened!!!");
		//Taking inputs:
		int asciiVal = System.in.read();
		System.out.println(String.format("The Ascii value:%d\tThe char value %s", asciiVal, (char)asciiVal));
		
	}

}
/*Create a Console Application that stores the student records into a file and perform CRUD operations on it, the file will be a CSV File. U should be able to insert, delete, update and find records from the data obtained from the file.
 * The App should be a menu driven application. This time, the menu is a Text file and the contents to be read from the file...
 * */

