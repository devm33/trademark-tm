package game;
/**
 * Class to implement the ammunition item.
 * 
 * 
 * @author Devraj Mehta
 *
 */

public class Ammo extends Item {
	
	/**
	 * keeps track of the number of rounds in this ammount of ammo.
	 */
	private int rounds;
	
	/**
	 * Create a new Ammo object. Weighs 3 lbs. Costs $2 for 20 rounds.
	 */
	public Ammo(){
		super(3, 0, "Ammunition");
	}
	
	@Override
	public void use() {
		rounds--;
		if(rounds % 20 == 0)
			setNumber(getNumber()-1);
	}
}
