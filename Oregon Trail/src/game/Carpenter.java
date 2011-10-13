package game;

/**
 * Class for the Carpenter character
 * 
 * @author Devraj Mehta
 */

public class Carpenter extends Leader {

	/**
	 * Create a new Carpenter with a given name. Money initially set to 800.
	 * 
	 * @param name the name of the Carpenter.
	 */
	public Carpenter(String name) {
		super(name);
		money = 800;
	}
}