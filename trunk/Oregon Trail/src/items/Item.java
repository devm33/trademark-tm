package items;

/**
 * Abstract class for all Items.
 * 
 * 
 * @author Devraj Mehta
 *
 */

public abstract class Item implements Comparable<Item> {
	
	private String name;
	private int weight;
	private int number;
	
	/**
	 * Constructor to make a new Item with a given weight.
	 * Assumes a multiplicity of one.
	 * @param w the weight of the Item.
	 */
	public Item(int w) {
		this(w,1,"");
	}
	
	/**
	 * Constructor to make a copy of another Item.
	 * @param i the Item to copy
	 * @param num the number of Items to hold in this instance.
	 */
	public Item(Item i, int num) {
		weight = i.getWeight();
		number = num;
		name = i.toString();
	}
	
	/**
	 * Constructor to make a new Item with a given weight and number.
	 * @param w the weight
	 * @param num the number
	 */
	public Item(int w, int num) {
		this(w,num,"");
	}
	
	/**
	 * Constructor to make a new Item with a given weight, number, and name
	 * @param w the weight
	 * @param num the number
	 * @param n the name
	 */
	public Item(int w, int num, String n) {
		weight = w;
		number = num;
		name = n;
	}
	
	/**
	 * Constructor to copy an item.
	 * @param i the Item
	 */
	public Item(Item i) {
		this(i, 1);
	}
	
	/**
	 * Method for checking the weight of an item.
	 * 
	 * @return the weight of the item.
	 */
	public int getWeight() {
		return weight*number;
	}
	
	/**
	 * Method for checking the number of items represented by this class.
	 * 
	 * @return the multiplicity of this instance of the item.
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * Method for adding to the multiplicity of this item.
	 * @return the new number of items.
	 */
	public int addItem() {
		number++;
		return number;
	}
	
	/**
	 * Method for adding multiple items to this instance
	 * @param num the number of items to add
	 * @return the new number held in this class
	 */
	public int addItems(int num) {
		number += num;
		return number;
	}
	
	/**
	 * Method for changing the number of items
	 * 
	 * @param num the new number of items.
	 */
	public void setNumber(int num) {
		number = num;
	}
	
	/**
	 * Method to get the name of an item.
	 * @return the name of this item
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return a string representation of this instance of an item.
	 */
	public String toString() {
		return name + ": " + weight + " lbs x " + number;
	}
	
	/**
	 * Abstract method for using items.
	 */
	public abstract void use();
	
	/**
	 * Implementing the comparable library
	 */
	public boolean equals(Item i) {
		return name.equals(i.getName());
	}
}
