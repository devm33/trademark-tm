package items;

import people.Person;
import game.World;

/**
 * Class to implement medicine items.
 * 
 * 
 * @author Devraj Mehta
 *
 */


public class Medicine extends Item {
	
	/**
	 * Keeps track of the number of uses of this medicine.
	 */
	private int uses;
	/**
	 * in order to keep track of uses this class needs to know when
	 * the number of items is change. this variable keeps track of what
	 * it thinks the amount is.
	 */
	private int amount;
	/**
	 * the number of uses per item.
	 */
	private final static int num_uses = 50;
	
	/**
	 * Creates a new medicine item with 0 inventory.
	 */
	public Medicine() {
		super(1,0,"Medicine");
	}
	
	@Override
	public void use() {
		if(amount != getNumber()) {
			uses += num_uses*(getNumber() - amount);
			amount = getNumber();
		}
		if(uses == 0)
			return;
		if(uses < 0) {
			uses = 0;
			return;
		}
		
		//fully heals all the people in the wagon per use
		for(Person p : World.getWagon().getPassengers()) {
			if (!p.getStatus().equals("DEAD")) {
				p.setHealed();
				p.addHealth(10);
			}
		}
		World.getMainScreen().displayOnField("Medicine Used to make everything better.");
		uses--;
		if(uses % num_uses == 0)
			super.setNumber(getNumber()-1);
		return;
	}

	@Override
	public String getDescription() {
		return "A large container of medicine.";
	}

	@Override
	public String getResponse() {
		return "It's great for headaches and heroin addicts.";
	}

	@Override
	public String getBoughtResponse() {
		return "Don't tell the doctor where you got that...";
	}
	
}
