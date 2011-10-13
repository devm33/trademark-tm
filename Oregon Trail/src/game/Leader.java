package game;

/**
 * Abstract class to implement basic functions of all leader classes.
 * 
 * @author Devraj Mehta
 */
public abstract class Leader implements Person {
	
	protected int money, health, thirst, hunger;
	protected String name;
	
	/**
	 * Create a new Leader with a given name.
	 * 
	 * @param name the Leader's name
	 */
	public Leader(String name) {
		money = 0;
		health = 100;
		thirst = 0;
		hunger = 0;
		this.name = name;
	}
	
	/**
	 * Method to check Leader's money.
	 * 
	 * @return the amount of money the Leader has.
	 */
	public int getMoney() {
		return money;
	}
	
	/**
	 * Changes the Leader's money by a given amount. Make amount negative to withdrawal money from Leader.
	 * 
	 * @param change the amount to add to the Leader's money.
	 * @return the Leader's current funds.
	 * @throws InsufficentFundsException
	 */
	public int setMoney(int change) throws InsufficentFundsException {
		money += change;
		if(money < 0)
			throw new InsufficentFundsException();
		return money;
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
	public String getStatus() {
		//TODO
		return null;
	}
	
	public void hunt() {
		//TODO
	}
	
	public void sick() {
		//TODO
	}
	
	public void trade() {
		//TODO
	}

}
