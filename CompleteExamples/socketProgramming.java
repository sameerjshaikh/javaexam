package basics;
import java.io.*;
import java.net.*;
/*
 * If u want to communicate b/w 2 Processes or 2 machines within a network, we can go for socket programming. Socket based classes are available at java.net.*. 
 * In this case, the apps must interact with 2 JREs(Apps run on their own JREs)
 * A built in class called Socket is used to communicate with the servers that are created using a class called ServerSocket. 
 * Client apps that consume the socket program will be made aware of 2 things: Address of the socket and the port no thro which the socket is available. address would typically be the location of the machine. If the socket is in the same machine, its called as LOCALHOST. Port no is an integer value that represents the point of contact within the OS. Every IPC based app uses the PORT no which is to be unique for the given time. Once the port no is registered to the OS, it will not be allocated to other app or request. 
 * Server sockets have the responsibility of creating server side components that make UR service, handle connections made to it by the clients. IT would have methods like accept, open and close to perform interactions with the client apps.
 * App that consumes the sockets are called as Client apps. Socket programming is also a part of the IO framework. 
*/
public class socketProgramming {

	public static void main(String[] args) {
		try {
			//basicInputDemo();
			ServerSocket ss = new ServerSocket(4444);
			Socket s = ss.accept();
			DataInputStream din = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String inMsg ="", outMsg ="";
			while(!inMsg.equals("End")) {
				inMsg = din.readUTF();
				MyConsole.print(inMsg);
				outMsg = reader.readLine();
				dos.writeUTF(outMsg);
				dos.flush();						
			}
			MyConsole.print("App is now terminating");
			dos.close();
			din.close();
			s.close();
			ss.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	private static void basicInputDemo() throws IOException {
		//Server is something that provides the service..
		ServerSocket ss = new ServerSocket(4444);//Port no is more like an extension no of UR machine IP Address..
		Socket s = ss.accept();//create a socket object thro which UR Clients wll communicate..
		//DataInputStream is used to communicate the data thro sockets..
		DataInputStream din = new DataInputStream(s.getInputStream());
		String msg = din.readUTF();//Reads the data sent thro the Socket...
		MyConsole.print(msg);
		ss.close();
		
	}

}
