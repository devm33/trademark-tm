package game;

/**
 * Abstract class for all Items.
 * 
 * 
 * @author Devraj Mehta
 *
 */

public abstract class Item {
	
	private int weight;
	
	/**
	 * Constructor to make a new Item with a given weight.
	 * 
	 * @param w the weight of the Item.
	 */
	public Item(int w) {
		weight = w;
	}
	
	/**
	 * Method for checking the weight of an item.
	 * 
	 * @return the weight of the item.
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Abstract method for using items.
	 */
	public abstract void use();

}
