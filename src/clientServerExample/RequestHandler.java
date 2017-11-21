package clientServerExample;

//RequestHandler.java
import java.net.*;
import java.util.StringTokenizer;
import java.io.*;

// ******** SEVER PROCESS ********

public class RequestHandler extends Thread {
	protected Socket sock;
	protected PrintWriter out;
	protected BufferedReader in;

	ServerService su = new ServerService();

	public RequestHandler(Socket clientSocket){
		this.sock = clientSocket;

		try{
			out = new PrintWriter(clientSocket.getOutputStream(), true); // to send data from server process to client
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // to receive data from client side
		} catch(Exception e){
			System.out.println("Error: " + e); 
			return;
		}
	}

	public void run(){
		System.out.println("Handler started.");

		try {
			while(!sock.isInputShutdown()){
				String request = receiveMessage();

				if (!request.equals("")){
					processRequest(request);
				}
			}
		} catch(Exception exc){}
		System.out.println("Handler stopped.");
	}

	protected void processRequest(String request){
		// Searching function name so it can be called
		// request #funcName#arg1#arg2#...

		StringTokenizer st = new StringTokenizer(request,"#");
		String functionName = st.nextToken();
		String arg1 = st.nextToken();
		
		if (functionName.equals("setI")){
			int ni = Integer.parseInt(arg1);
			int staroi = su.setI(ni);
			sendMessage("" + staroi);
			
		} else if (functionName.equals("sum")){
			String arg2 = st.nextToken();

			int a = Integer.parseInt(arg1);
			int b = Integer.parseInt(arg2);

			int res = su.sum(a,b);
			sendMessage("" + res);
		}
		
	}

	protected void sendMessage(String message){ // send calculated value to client
		out.println(message);
	}

	protected String receiveMessage() { // receive value from client (hard coded or from user input)
		try{
			return in.readLine();
		} catch(IOException exc){
			System.out.println("Error: " + exc);
		}
		return "";
	}
	
} 







