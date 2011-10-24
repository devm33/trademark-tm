package exceptions;

/**
 * Class to throw when there is an accounting error.
 * 
 * @author Devraj Mehta
 *
 */

public class InsufficentFundsException extends Exception {

	private static final long serialVersionUID = 1L;
	public InsufficentFundsException(){
		System.out.println("YOU OUTTA MONEY, FOOL");
	}
}
