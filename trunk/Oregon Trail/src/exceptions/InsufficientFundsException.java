package exceptions;

/**
 * Class to throw when there is an accounting error.
 * 
 * @author Devraj Mehta
 *
 */

public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = 1L;
	public InsufficientFundsException(){
		System.out.println("YOU OUTTA MONEY, FOOL");
	}
}
