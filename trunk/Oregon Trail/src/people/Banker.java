package people;

/**
 * Class for the Banker character
 * 
 * @author Devraj Mehta
 */

public class Banker extends Leader {

	/**
	 * Create a new Banker with a given name. Money initially set to 1600.
	 * 
	 * @param name the name of the Banker.
	 */
	public Banker(String name) {
		super(name);
		money = 1600;
	}
}
