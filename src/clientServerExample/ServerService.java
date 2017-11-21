package clientServerExample;

// ServerService.java
public class ServerService {
	protected int i = 0;

	public int setI(int ni){  // function to set "i" value
		int tmp = i; 
		i = ni;
		return tmp; 
	}

	public int sum(int a, int b){ // function to calculate some two numbers (a+b) + "i" value
		return a+b+i; 
	}
	
} // End ServerService.java 





