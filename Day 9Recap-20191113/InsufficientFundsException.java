package recap;

public class InsufficientFundsException extends Exception {
	public InsufficientFundsException() {
		// TODO Auto-generated constructor stub
	}
	
	InsufficientFundsException(String message){
		super(message);
	}
	//Sets Custom message and also contain info about actual system generated message...
	InsufficientFundsException(String message, Exception innerException){
		super(message, innerException);
	}
}
