package recap;

import java.io.*;
import basics.MyConsole;

/*
 * The object that should be serialized must have its class to be Serializable. The class implements an interface called Serializable. Technically the interface does not have any function to implement, it would contain internally some logic to allow the objects of this class storable as byte array into a storage device.  
 * ObjectInputStream and ObjectOutputStream for performing Serialization. 
 * The Entity class, A Functionality class and Main Class which represents the App UI.
 * */

class SBAccount implements Serializable{
	int accNo;
	String holderName;
	private double balance;
	public void creditAmount(double amount) { this.balance += amount;}
	public void debitAmount(double amount) throws InsufficientFundsException{ 
		if(amount > balance)
			throw new InsufficientFundsException("Insufficient Funds");
		balance -= amount;
	}
	public double getBalance() {
		return balance;
	}
}
//This class will serialize and deserialize the SBAccount class object...
class SBAccountSerialization{
	private SBAccount acc;
	public SBAccountSerialization(SBAccount acc) {
		this.acc = acc;
	}
	//save the object
	public void serialize(String filename) {
		try {
			FileOutputStream fs = new FileOutputStream(filename);
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(this.acc);
			os.close();
			fs.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}	
	}
	//returns the saved object...
	public SBAccount deSerialize(String filename) {
		FileInputStream fIn = null;
		ObjectInputStream oIn = null;
		try {
			fIn = new FileInputStream(filename);
			oIn = new ObjectInputStream(fIn);
			this.acc = (SBAccount)oIn.readObject();//UNBOX the readObject return value...
			fIn.close();
			oIn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.acc;		
	}
	
}
public class SerializationDemo {

	public static void main(String[] args) {
		//savingObject();
		loadingObject();
	}

	private static void loadingObject() {
		SBAccount acc = null;
		String name = MyConsole.getString("Enter the name of the Account holder");
		SBAccountSerialization serialization = new SBAccountSerialization(acc);
		acc = serialization.deSerialize(name + ".txt");
		MyConsole.print(acc.accNo + " and His Name: " + acc.holderName + "\nHis Balance Amount is  " + acc.getBalance());
	}

	private static void savingObject() {
		SBAccount acc = new SBAccount();
		acc.accNo = MyConsole.getNumber("Enter the Acc no");
		acc.holderName = MyConsole.getString("Enter the Name of the Acccount Holder");
		double initialAmount = MyConsole.getDouble("Enter the Initial Amount to deposit.\nPS: It should be min of Rs. 5000");
		if(initialAmount < 5000) {
			MyConsole.print("U cannot create the Account with out the min. Balance");
			return;
		}
		acc.creditAmount(initialAmount);
		
		String answer = MyConsole.getString("Do U want to save this info to our database? PressY for Yes and N for No");
		if(answer.toUpperCase().equals("Y")) {
			SBAccountSerialization serialization = new SBAccountSerialization(acc);
			serialization.serialize(acc.holderName + ".txt");
		}
	}

}
