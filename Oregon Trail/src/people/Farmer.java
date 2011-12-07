package people;

/**
 * Class for the Farmer character
 * 
 * @author Devraj Mehta
 */

public class Farmer extends Leader {
	String type = "farm";
	/**
	 * Create a new Farmer with a given name. Money initially set to 400.
	 * 
	 * @param name the name of the Farmer.
	 */
	public Farmer(String name) {
		super(name);
		money = 400;
	}
	
	public Farmer(int health, int thirst, int hunger, String name) {
		super(health, thirst, hunger, name);
		money = 400;
	}
	@Override
	public String getType(){
		return type;
	}
}