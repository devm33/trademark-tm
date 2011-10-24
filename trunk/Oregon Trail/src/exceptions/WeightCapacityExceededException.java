package exceptions;

/**
 * Exception class for wagon capacity errors.
 * 
 * 
 * @author Devraj Mehta
 *
 */

public class WeightCapacityExceededException extends Exception {

	private static final long serialVersionUID = 1L;
	public WeightCapacityExceededException(){
		System.out.println("You have too much junk in your wagon trunk");
	}
}
