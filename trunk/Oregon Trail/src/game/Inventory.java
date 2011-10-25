package game;

import items.Ammo;
import items.Food;
import items.Item;
import items.Medicine;
import items.Water;

/**
 * Backend for Inventory GUI
 * @author Stephen
 * @author Jaron
 *
 */
public class Inventory {
	private Item[]itemInventory;
	private int length = 6;
	/**
	 * Constructor for empty game items in Inventory
	 */
	public Inventory(){
		itemInventory = new Item[length];
		itemInventory[0] = new Ammo();
		itemInventory[1] = new Food();
		itemInventory[2] = new Medicine();
		itemInventory[3] = new Water();
	}
	/**
	 * Constructor for 3 specific items in Inventory
	 * @param item0 an item to go in the Inventory
	 * @param item1 an item to go in the Inventory
	 * @param item2 an item to go in the Inventory
	 */
	public Inventory(Item item0,Item item1, Item item2){
		itemInventory = new Item[length];
		itemInventory[0] = item0;
		itemInventory[1] = item1;
		itemInventory[2] = item2;
	}
	/**
	 * Constructor for all items in Inventory
	 * @param item0 an item to go in the Inventory
	 * @param item1 an item to go in the Inventory
	 * @param item2 an item to go in the Inventory
	 * @param item3 an item to go in the Inventory
	 * @param item4 an item to go in the Inventory
	 * @param item5 an item to go in the Inventory
	 */
	public Inventory(Item item0,Item item1, Item item2, Item item3, Item item4, Item item5){
		itemInventory = new Item[length];
		itemInventory[0] = item0;
		itemInventory[1] = item1;
		itemInventory[2] = item2;
		itemInventory[3] = item3;
		itemInventory[4] = item4;
		itemInventory[5] = item5;
	}
	/**
	 * Method to check the length of Inventory
	 * @return length of Inventory in int
	 */
	public int getLength(){
		return length;
	}
	/**
	 * Method to return an Item
	 * @param x the specified position in Inventory
	 * @return itemInventory[x] the specified Item
	 */
	public Item getItem(int x){
		return itemInventory[x];
	}
	/**
	 * Sets the number of an item in inventory
	 * @param item the item to be adjusted
	 * @param num the item's position in Inventory
	 */
	public void setItemNum(int item, int num){
		itemInventory[item].setNumber(num);
	}
	/**
	 * Gets the itemInventory
	 * @return itemInventory the array of Items
	 */
	public Item[] getItemInventory(){
		return itemInventory;
	}
	/**
	 * Checks the contents of inventory for an item
	 * @param i the item to check for
	 * @return boolean
	 */
	public boolean contains(Item i) {
		// TODO Auto-generated method stub
		for (int x=0; x<length; x++){
			if (itemInventory[x].getName()==i.getName()){
				return true;
			}
			
		}
		return false;
	}
	/**
	 * Returns the Food object in the Inventory if it exists, if not returns null
	 * @return Food or null depending on the presence of a Food object in inventory
	 */
	public Food getFood(){
		for (int f=0; f<length; f++){
			if (itemInventory[f].equals(new Food())){
				return (Food) itemInventory[f];
			}
		}
		return null;
	}

}
