package m5;

/**
 * Person Interface
 * 
 * @author Devraj Mehta
 * @version 1.0
 */
public interface Person {
	
	/**
	 * Method to check each person's health.
	 * 
	 * @return integer representing person's current health; 100 for in perfect health, 0 for dead.
	 */
	public int getHealth();
	
	/**
	 * Changes person's health by a given amount.
	 * 
	 * @param change the amount the person's health is to change.
	 * @return integer representing person's current health.
	 */
	public int setHealth(int change);
	
	/**
	 * Given an amount of food the person eats it and updates its health.
	 * 
	 * @param amount the amount of food to eat.
	 */
	
	
	/**
	 * Kill this person: sets the health to zero and the status to DEAD.
	 */
	public void die();
	
	public void eatFood(int amount);
	
	/**
	 * Given an amount of water the person drinks it and updates its health.
	 * 
	 * @param amount the amount of water to drink.
	 */
	public void drinkWater(int amount);
	
	/**
	 * Method to check on the person's current condition. Valid statuses include: HEALTHY, SICK, HUNGRY, THIRSTY, and DEAD.
	 * 
	 * @return a string representing the person's current status.
	 */
	public String getStatus();
	
}
