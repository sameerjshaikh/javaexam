package recap;
import java.io.*;
import java.util.*;

import basics.MyConsole;

//Reading and Writing a CSV file is more like file reading and writing...
class Student{
	String name;
	int mathScore;
	int scienceScore;
	int socialScore;
	int engScore;
	
	@Override
	public String toString() {
		return String.format("%s,%d,%d,%d,%d", name, mathScore, scienceScore, socialScore, engScore);
	}
}

class StudentCSVDemo{
	List<Student> allStudents = null;
	
	public void addStudent(Student s) {
		//loadExistingStudentsFrom File
		loadData();
		//add to the list
		allStudents.add(s);
		//save back to the file..
		saveData();
	}
	
	public List<String>getAllNames(){
		loadData();
		List<String> names = new ArrayList<String>();
		for(Student s : allStudents) {
			names.add(s.name);
		}
		return names;
	}

	private void saveData() {
		try {
			FileWriter writer  =new FileWriter("Students.csv");
			for(Student s : allStudents) {
				writer.write(s.toString() + "\n");
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private void loadData() {
		allStudents = new ArrayList<Student>();
		try {
			//get the CSV file 
			FileReader rd = new FileReader("Students.csv");
			BufferedReader bf = new BufferedReader(rd);
			//read each line
			String line = "";
			while((line = bf.readLine()) != null) {
				String [] words = line.split(",");//split each line into words
				Student s = new Student();
				s.name = words[0];//take each word to fill data into student object
				s.mathScore = Integer.parseInt(words[1]);
				s.scienceScore = Integer.parseInt(words[2]);
				s.socialScore = Integer.parseInt(words[3]);
				s.engScore = Integer.parseInt(words[4]);
				allStudents.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
public class CsvExample {

	public static void main(String[] args) {
		//addingStudentExample();
		readingNamesExample();
	}

	private static void readingNamesExample() {
		StudentCSVDemo demo = new StudentCSVDemo();
		List<String>names = demo.getAllNames();
		for(String name : names)
			MyConsole.print(name);
	}

	private static void addingStudentExample() {
		StudentCSVDemo demo = new StudentCSVDemo();
		Student s = new Student();
		s.name = MyConsole.getString("Enter the Student Name");
		s.mathScore = MyConsole.getNumber("Enter the Maths score");
		s.scienceScore = MyConsole.getNumber("Enter the Science score");
		s.socialScore = MyConsole.getNumber("Enter the Social score");
		s.engScore = MyConsole.getNumber("Enter the English score");
		demo.addStudent(s);
	}

}
