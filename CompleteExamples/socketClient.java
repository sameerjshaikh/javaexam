package basics;
import java.net.*;
import java.io.*;

public class socketClient {

	public static void main(String[] args) {
		try {
			//basicOutput();
			Socket s = new Socket("localhost", 4444);
			String str = "", str2 = "";
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			DataInputStream din = new DataInputStream(s.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			while(!str.equals("STOP")) {
				str = reader.readLine();
				dos.writeUTF(str);;
				dos.flush();
				str2 = din.readUTF();
				MyConsole.print("Server:" + str2);
			}
			
			dos.flush();//clearing the buffer to the destination
			dos.close();
			s.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void basicOutput() throws UnknownHostException, IOException {
		//Create a Socket to communicate...
		Socket s = new Socket("localhost", 4444);
		//write some data into the stream so that the other end reads the stream and do some operation on it
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		dos.writeUTF(MyConsole.getString("Enter some message to the socket"));
		dos.flush();//clearing the buffer to the destination
		dos.close();
		s.close();
		//finally close the stream
	}

}
