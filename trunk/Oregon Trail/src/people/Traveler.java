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
		this(100,0,0,name);
	}
	
	
	/**
	 * Create a new traveler
	 * @param health
	 * @param thirst
	 * @param hunger
	 * @param name
	 */
	public Traveler(int health, int thirst, int hunger, String name) {
		this.health = health;
		this.thirst = thirst;
		this.hunger = hunger;
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
	public int addHealth(int change) {
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
	public int getThirst() {
		return thirst;
	}

	@Override
	public int getHunger() {
		return hunger;
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
	public void live() {
		if(health <= 0)
			return; //I ain't living.
		thirst += 30;
		if(thirst >= 100) {
			thirst = 100;
			die();
		}
		hunger += 15;
		if(hunger >= 100) {
			hunger = 100;
			health -= 15;
			if(health < 0)
				die();
		}
	}
	
	@Override
	public String toString() {
		return name+": health = "+health+", hunger = "+hunger+", thirst = "+thirst;
	}

	@Override
	public String getStatus() {
		//TODO
		return null;
	}
}
