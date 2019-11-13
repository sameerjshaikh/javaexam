package basics;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;//List of packages to be imported for NIO Channel operations...
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sun.corba.se.spi.ior.Writeable;

/*
 * Non Blocking IO is called as NIO introduced in Java 1.4. Its an alternate way of handling i/o operations. Most of the APIs are same as IO. Main difference is its non-blocking. It means its asynchronous in action. 
 * IO is based on Streams and NIO is based on buffers. 
 * Blocking IO will make a file or a resource locked while reading or writing. This creates a blocking situation for other programs that try to read the same file.  
 * In Non blocking IO, data is read into CHANNELS. Channels are units that hold the buffer of the data. The channels allow to read the data dynamically without blocking the file. The channels are a buffered piece of memory that gets filled in as the previous buffer data is read. This ensures that the file is not blocked during the complete reading or writing cycle. 
 * 3 Terms: Channels, Buffers and Selectors. 
 * Buffers: fixed piece of memory used to store data before its being read into a channel. Buffers ensure that the defined size of data is preloaded to speed up the reading. It is configurable in size on how much size U initially need(2^n). Depending on type of inputs U have different kinds of buffers:ByteBuffer, CharBuffer, DoubleBuffer, FloatBuffer and so forth
 * Channels: Channels are similar to Streams of blocking IO. The channels allow data over network as well as within the machine and processes. Unlike Streams that read data directly from resource(blocking it), channels read data from buffers. This is the one that makes it NON BLOCKING IO. There are various kinds of channels:FileChannel, DatagramChannel, SocketChannel, ServerSocketChannel and many more.. 
 * Selectors: Selectors are objects that can be used to monitor the multiple channels for various kinds of events like data arrival, connection opened, closed and so forth. 
 * Java IO vs. NIO:
 * NIO is a NON-BLOCKING IO Operation. 
 * IO is stream oriented and NIO is buffer Oriented. 
 * Channels are used for reading and writing to the buffers which is not found in Blocking IO.
 * NIO uses selectors to interact with multiple channels at a time. 
 * Streams are always one way communication, channels offer 2 way data transfer facility.
 * */
public class NIODemo {

	public static void main(String[] args) throws Exception {
		//channelsExample();
		//bufferReadingExample();
		//directoryFileInfo();
	}
	private static void directoryFileInfo() {
		File folder = new File("src\\basics");
		for(File entry : folder.listFiles()) {
			if(entry.isFile()) {
				MyConsole.print(entry.getName());
				MyConsole.print(entry.getAbsolutePath());
				File parent = entry.getParentFile();
				if(parent.isDirectory()) {
					MyConsole.print(parent.getPath());
				}
				MyConsole.print(entry.getParent());
			}
		}
		MyConsole.print("Files being read from the directory");
	}
	//Using BufferedReader object to push the data into a buffer from the File source.
	private static void bufferReadingExample() throws IOException {
		//To read a file, we use a class called Path that represents the path of a file to read. The Paths class contain static methods to read a directory, create them and access files of a given directory. Paths.get returns the file reference of the specfied string.
		Path file = Paths.get("MyChannel.txt");
		InputStream is = Files.newInputStream(file);
		BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
		MyConsole.print("Reading the content: " + bReader.readLine());
		bReader.close();
		is.close();
	}

	private static void channelsExample() throws FileNotFoundException, IOException {
		FileInputStream fs = new FileInputStream("MyChannel.txt");
		ReadableByteChannel source = fs.getChannel();
		
		FileOutputStream fo = new FileOutputStream("MyOutputChannel.txt");
		WritableByteChannel dest = fo.getChannel();
		
		copyData(source, dest);//function to copy the data from source to destination
		source.close();
		dest.close();
	}

	private static void copyData(ReadableByteChannel source, WritableByteChannel dest) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(20 * 1024);
		while(source.read(buffer) != -1) {
			buffer.flip();
			while(buffer.hasRemaining()) {
				dest.write(buffer);
			}
			buffer.clear();
		}
	}
}
