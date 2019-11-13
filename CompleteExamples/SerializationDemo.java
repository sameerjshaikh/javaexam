package basics;

import java.io.*;

/*Points:
 * Serialization is a mechanism of converting a state of object into a bytestream where it could be converted into a filedata. This data will be non appendable and will hold as it is. It can be reconverted back to the same object from which it was serialized. the process of saving the object as bytestream is called Serialization and the process of reconverting the stream to the object from which it was saved is called as Deserialization. 
 * Serialization process could be targeted on files, memory, pipes, serial ports, sockets and many more. 
 * Serialization has 3 points:
 * What to serialize: Any object of a class that implements an interface called Serializable
 * Where to serialize:Any File identifiable by UR OS....
 * Format of Serialization: Binary, XML and SOAP.
 * When serialization happens, the whole state of the object including its type info, metadata will be stored in the serialization location. Serialization is usefull for IPC, Socket communication and inter machine communication.
 * 
 * */
//serialize interface does not have any method to be implemented by the implementor class, it contains some additional logic that performs the process of serialization.
class Patient implements Serializable{
	private int patientID;
	private String patientName;
	private long phoneNo;
	private String docName;
	private double billAmount;
	
	public Patient(int id, String name, long no, String doc, double bill) {
		patientID = id;
		patientName = name;
		phoneNo = no;
		docName = doc;
		billAmount = bill;
	}
	public int getPatientID() {
		return patientID;
	}
	public String getPatientName() {
		return patientName;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public String getDocName() {
		return docName;
	}
	public double getBillAmount() {
		return billAmount;
	}
	@Override
	public String toString() {
		return String.format("Name:%s\nDocName:%s\nBillAmount:%f", patientName, docName, billAmount);
	}
	
}
public class SerializationDemo {

	public static void main(String[] args) {
		//serializationPart();
		deserializationPart();
	}

	private static void deserializationPart() {
		try {
			FileInputStream fs = new FileInputStream("Demo.ser");
			ObjectInputStream is = new ObjectInputStream(fs);
			Patient p = (Patient)is.readObject();
			if(p!= null)
				MyConsole.print(p);//toString method will be printed..
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void serializationPart() {
		try {
			Patient p = new Patient(123, "Phaniraj", 9945205684L, "Dr. Nagesh", 7600);
			FileOutputStream fs = new FileOutputStream("Demo.ser");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(p);
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
