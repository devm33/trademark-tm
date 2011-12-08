package people;

/**
 * Class for the Banker character
 * 
 * @author Devraj Mehta
 */

public class Banker extends Leader {
	String type = "bank";
	/**
	 * Create a new Banker with a given name. Money initially set to 1600.
	 * 
	 * @param name the name of the Banker.
	 */
	public Banker(String name) {
		super(name);
		money = 1600;
	}
	/**
	 * constructor for ues with saving/loading
	 * @param health of banker
	 * @param thirst of banker
	 * @param hunger of banker
	 * @param name of banker
	 */
	public Banker(int health, int thirst, int hunger, String name) {
		super(health, thirst, hunger, name);
		money = 1600;
	}
	@Override
	public String getType(){
		return type;
	}
}
