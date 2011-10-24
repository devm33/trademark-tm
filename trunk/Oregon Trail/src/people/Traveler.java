package people;

/**
 * Class for inactive members of the wagon party.
 * 
 * 
 * @author Devraj Mehta
 *
 */

public class Traveler implements Person {
	
	private int health, thirst, hunger;
	private String name;
	
	/**
	 * Create a new Traveler with a given name.
	 * 
	 * @param name the Traveler's name.
	 */
	public Traveler(String name) {
		health = 100;
		thirst = 0;
		hunger = 0;
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public int setHealth(int change) {
		health += change;
		if(health < 0)
			health = 0;
		if(health > 100)
			health = 100;
		return health;
	}

	@Override
	public void sick() {
		//TODO
	}

	@Override
	public void die() {
		health = 0;
	}

	@Override
	public void eatFood(int amount) {
		hunger -= amount;
		if(hunger < 0)
			hunger = 0;
		//TODO update health
	}

	@Override
	public void drinkWater(int amount) {
		thirst -= amount;
		if(thirst < 0)
			thirst = 0;
		//TODO update health
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public String getStatus() {
		//TODO
		return null;
	}
}