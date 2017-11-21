package clientServerExample;

// Test client side
public class Test {
	public static void main(String args[]){
		
		Service client = new Service("localhost",6001); // client created

		System.out.println(client.setI(10)); // call setI
		System.out.println(client.setI(15)); // new setI call which will be used later
		System.out.println(client.sum(5,60)); // call sum function

		System.out.println("Kraj testa...");
		client.closeSocket();
		
	}
}





