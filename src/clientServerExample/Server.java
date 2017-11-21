package clientServerExample;

//Server.java
import java.net.*;
import java.io.*;

//******** SERVER ********

public class Server extends Thread { // each server call is treated like new thread
	protected ServerSocket serverSocket = null; 
	protected boolean end = false;
	
	public Server(int port){
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e){
			System.err.println("Couldn't listen on port: " + port);
			System.exit(1);
		}
		System.out.println("Server is running on port: " + port);
	}

	public void run(){
		System.out.println("Server is started and waiting for request...");
		
		while(!end){
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
				System.out.println("Request received!");
				Thread t = new RequestHandler(clientSocket);
				t.start();
			} catch (IOException e) {
				System.err.println("Error: " + e);
			}
		}
		closeSocket();
		System.out.println("Server stopped!");
	}

	public void closeSocket(){
		try{
			serverSocket.close();
		} catch(Exception e){
			System.err.println("Error: " + e);
		}
	}
	
	public void stopServer(){
		end = true;
	} 
	
	public static void main(String args[]){
		Server s = new Server(6001);
		s.start();
		
		try{
			Thread.sleep(1000);
		} catch(Exception e){
			System.err.println("Error: " + e);
		}
		
		s.stopServer();
	}
	
} 










