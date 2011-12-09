package items;

import game.World;

import java.util.Random;

/**
 * Class to implement the ammunition item.
 * 
 * 
 * @author Devraj Mehta
 *
 */

public class Ammo extends Item {
	
	/**
	 * keeps track of the number of rounds in this amount of ammo.
	 */
	private int rounds;
	/**
	 * in order to keep track of rounds this class needs to know when
	 * the number of ammunition is change. this var keeps track of what
	 * it thinks the amount is.
	 */
	private int amount;
	
	/**
	 * Create a new Ammo object. Weighs 3 lbs. Costs $2 for 10 rounds.
	 */
	public Ammo(){
		super(3, 0, "Ammo");
		rounds = 0;
		amount = 0;
	}
	
	
	@Override
	public void use() {
		Random huntingRand = new Random();
		int foodGained = huntingRand.nextInt(35)+1;
		if(amount != getNumber()) {
			rounds += 10*(getNumber() - amount);
			amount = getNumber();
		}
		if(rounds == 0)
			return; //do nothing
		if(rounds < 0) {
			rounds = 0; //can't have negative amounts
			return; //still do nothing
		}
		rounds--;
		int food = World.getWagon().getInventory().getFood().getNumber();
		World.getWagon().getInventory().getFood().setNumber(food+foodGained);
		if(rounds % 20 == 0)
			setNumber(getNumber()-1);
		World.getMainScreen().displayOnField("Went hunting and gained "+foodGained+" pounds of food.");
		World.getMainScreen().stepGame();
	}


	@Override
	public String getDescription() {
		return "A box of 10 hunting rifle rounds used for big game.";
	}


	@Override
	public String getResponse() {
		return "Don't blow your face off with these.";
	}


	@Override
	public String getBoughtResponse() {
		return "Happy hunting, shooter!";
	}
}
