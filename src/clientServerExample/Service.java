package clientServerExample;

import java.io.*;
import java.net.*; 

// ******** CLIENT ********

public class Service {

	// *** All that user needs to know ***

	public int setI(int ni){
		String message = "#setI#"+ ni +"#"; 
		sendMessage(message);
		return receiveIntMessage(); // return i = ni;
	} 

	public int sum(int a, int b){
		String message = "#sum#" + a + "#" + b + "#";
		sendMessage(message);
		return receiveIntMessage(); // return a+b;
	}

	public Service(String host, int port){ // constructor
		try {
			sock = new Socket(host, port);
			out = new PrintWriter(sock.getOutputStream(), true); // object to send data to server, output stream
			in = new BufferedReader(new InputStreamReader(sock.getInputStream())); // object to receive data from server, input stream
			System.out.println("Socket is open...");
		} catch (UnknownHostException e) {
			System.err.println("Unknown host: " + host);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't connect to: " + host + ":" + port);
			System.exit(1);
		}
	}

	protected void sendMessage(String message){ // from client to server send data
		System.out.println("Sending message: " + message);
		out.println(message);
	}

	protected String receiveMessage() { // from server to client receive data
		try {
			return in.readLine(); // read from buffer
		} catch(IOException exc){
			System.out.println("Error in communication: " + exc);
			System.exit(1);
		}
		return "";
	}

	protected int receiveIntMessage(){ // parsing data from string to integer
		String answer = receiveMessage();
		try { 
			return Integer.parseInt(answer);
		} catch(Exception exc){
			System.out.println("Error in communication: " + exc);
			System.exit(1);
		}
		return 0;
	}

	// *** Things that are not important to end user, socket handler things ***
	
	protected Socket sock = null;
	protected PrintWriter out = null;
	BufferedReader in = null;

	public void closeSocket(){
		try {
			out.close();
			in.close();
			sock.close();
		} catch(Exception e){
			System.out.println("Error in socket closing: " + e);
		}
	}

	protected void finalize() throws Throwable{
		super.finalize();
		closeSocket();
	}
	
} // End Service.java 








