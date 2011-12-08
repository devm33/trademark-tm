package people;

/**
 * Class for the Carpenter character
 * 
 * @author Devraj Mehta
 */

public class Carpenter extends Leader {
	String type = "carp";
	/**
	 * Create a new Carpenter with a given name. Money initially set to 800.
	 * 
	 * @param name the name of the Carpenter.
	 */
	public Carpenter(String name) {
		super(name);
		money = 800;
	}
	/**
	 * constructor for use with saving/loading
	 * @param health of carpenter
	 * @param thirst of carpenter
	 * @param hunger of carpenter
	 * @param name of carpenter
	 */
	public Carpenter(int health, int thirst, int hunger, String name) {
		super(health, thirst, hunger, name);
		money = 800;
	}
	@Override
	public String getType(){
		return type;
	}
}