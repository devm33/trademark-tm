package m5;

/**
 * Class for the Farmer character
 * 
 * @author Devraj Mehta
 */

public class Farmer extends Leader {

	/**
	 * Create a new Farmer with a given name. Money initially set to 400.
	 * 
	 * @param name the name of the Farmer.
	 */
	public Farmer(String name) {
		super(name);
		money = 400;
	}
}