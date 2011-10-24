package exceptions;

/**
 * Exception class for inventory errors.
 * 
 * 
 * @author Devraj Mehta
 *
 */

public class ItemNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	public ItemNotFoundException(){
		System.out.println("I don't think that item exists");
	}
}
