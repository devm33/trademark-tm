package game;

import items.Ammo;
import items.Food;
import items.Item;
import items.Medicine;
import items.Water;
import items.Clothing;
import items.Wheel;
import items.Axle;
import items.Oxen;
import items.Tongue;

/**
 * Backend for Inventory GUI
 * @author Stephen Bentley
 * @author Jaron
 *
 */
public class Inventory {
	private Item[]itemInventory;
	private int length = 9;
	/**
	 * Default Constructor for empty game items in Inventory
	 */
	public Inventory(){
		length = 9;
		itemInventory = new Item[length];
		itemInventory[0] = new Ammo();
		itemInventory[1] = new Food();
		itemInventory[2] = new Medicine();
		itemInventory[3] = new Water();
		itemInventory[4] = new Axle();
		itemInventory[5] = new Clothing();
		itemInventory[6] = new Oxen();
		itemInventory[7] = new Tongue();
		itemInventory[8] = new Wheel();
	}
	/**
	 * Constructor for all items in Inventory
	 * @param item0 an item to go in the Inventory
	 * @param item1 an item to go in the Inventory
	 * @param item2 an item to go in the Inventory
	 * @param item3 an item to go in the Inventory
	 * @param item4 an item to go in the Inventory
	 * @param item5 an item to go in the Inventory
	 * @param item6 an item to go in the Inventory
	 * @param item7 an item to go in the Inventory
	 * @param item8 an item to go in the Inventory
	 */
	public Inventory(Item item0,Item item1, Item item2, Item item3, Item item4, Item item5, Item item6, Item item7, Item item8){
		length = 9;
		itemInventory = new Item[length];
		itemInventory[0] = item0;
		itemInventory[1] = item1;
		itemInventory[2] = item2;
		itemInventory[3] = item3;
		itemInventory[4] = item4;
		itemInventory[5] = item5;
		itemInventory[6] = item6;
		itemInventory[7] = item7;
		itemInventory[8] = item8;
	}
	/**
	 * Constructor for 7 items in Inventory
	 * @param item0 an item to go in the Inventory
	 * @param item1 an item to go in the Inventory
	 * @param item2 an item to go in the Inventory
	 * @param item3 an item to go in the Inventory
	 * @param item4 an item to go in the Inventory
	 * @param item5 an item to go in the Inventory
	 * @param item6 an item to go in the Inventory

	 */
	public Inventory(Item item0,Item item1, Item item2, Item item3, Item item4, Item item5, Item item6){
		length = 7;
		itemInventory = new Item[length];
		itemInventory[0] = item0;
		itemInventory[1] = item1;
		itemInventory[2] = item2;
		itemInventory[3] = item3;
		itemInventory[4] = item4;
		itemInventory[5] = item5;
		itemInventory[6] = item6;

	}
	/**
	 * Constructor for 5 items in Inventory
	 * @param item0 an item to go in the Inventory
	 * @param item1 an item to go in the Inventory
	 * @param item2 an item to go in the Inventory
	 * @param item3 an item to go in the Inventory
	 * @param item4 an item to go in the Inventory

	 */
	public Inventory(Item item0,Item item1, Item item2, Item item3, Item item4){
		length = 5;
		itemInventory = new Item[length];
		itemInventory[0] = item0;
		itemInventory[1] = item1;
		itemInventory[2] = item2;
		itemInventory[3] = item3;
		itemInventory[4] = item4;
	}
	/**
	 * Constructor for 3 specific items in Inventory
	 * @param item0 an item to go in the Inventory
	 * @param item1 an item to go in the Inventory
	 * @param item2 an item to go in the Inventory
	 */
	public Inventory(Item item0,Item item1, Item item2){
		length  = 3;
		itemInventory = new Item[length];
		itemInventory[0] = item0;
		itemInventory[1] = item1;
		itemInventory[2] = item2;
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
	 * returns true if inventory contains 0 quantity of items
	 * @return
	 */
	public boolean isBlank(){
		boolean blank = true;
		Item[] inventory = getItemInventory();
		for(int i = 0; i<getLength(); i++){
			if(inventory[i].getNumber() != 0){
				blank = false;
				break;
			}
		}
		return blank;
	}
	
	/**
	 * Checks the contents of inventory for an item
	 * @param i the item to check for
	 * @return boolean
	 */
	public boolean contains(Item i) {
		// TODO Auto-generated method stub
		for (int x=0; x<length; x++){
			if (itemInventory[x].getName().equals(i.getName())){
				return true;
			}
			
		}
		return false;
	}
	/**
	 * Finds an item based on its name and returns it.
	 * @param name the name of the item to get
	 * @return the item or null if not found
	 */
	public Item getItemByName(String name) {
		for(int x = 0; x < length; x++)
			if(itemInventory[x].getName().equals(name))
				return itemInventory[x];
		return null;
	}
	
	/**
	 * Returns the Food object in the Inventory if it exists, if not returns null
	 * @return Food or null depending on the presence of a Food object in inventory
	 */
	public Food getFood(){
		if(itemInventory[1].equals(new Food()))
			return (Food) itemInventory[1];
		
		for (int f=0; f<length; f++){
			if (itemInventory[f].equals(new Food())){
				return (Food) itemInventory[f];
			}
		}
		return null;
	}
	
	/**
	 * Returns the Water object in Inventory if it exists, if not, returns null
	 * @return Water or null depending on presence of a Water object in inventory
	 */
	public Water getWater(){
		if(itemInventory[3].equals(new Water()))
			return (Water) itemInventory[3];
		
		for (int f=0; f<length; f++){
			if (itemInventory[f].equals(new Water())){
				return (Water) itemInventory[f];
			}
		}
		return null;
	}
	
	/**
	 * Returns the Ammo object in the Inventory if it exists, if not returns null
	 * @return Ammo or null depending on the presence of a Ammo object in inventory
	 */
	public Ammo getAmmo(){
		if(itemInventory[0].equals(new Ammo()))
			return (Ammo) itemInventory[0];
		
		for (int f=0; f<length; f++){
			if (itemInventory[f].equals(new Ammo())){
				return (Ammo) itemInventory[f];
			}
		}
		return null;
	}
	
	/**
	 * Returns the Clothing object in the Inventory if it exists, if not returns null
	 * @return Clothing or null depending on the presence of a Clothing object in inventory
	 */
	public Clothing getClothing(){
		if(itemInventory[5].equals(new Clothing()))
			return (Clothing) itemInventory[5];
		
		for (int f=0; f<length; f++){
			if (itemInventory[f].equals(new Clothing())){
				return (Clothing) itemInventory[f];
			}
		}
		return null;
	}
	
	/**
	 * Returns the Medicine object in the Inventory if it exists, if not returns null
	 * @return Medicine or null depending on the presence of a Medicine object in inventory
	 */
	public Medicine getMedicine(){
		if(itemInventory[2].equals(new Medicine()))
			return (Medicine) itemInventory[2];
		
		for (int f=0; f<length; f++){
			if (itemInventory[f].equals(new Medicine())){
				return (Medicine) itemInventory[f];
			}
		}
		return null;
	}
	
	/**
	 * Returns the Wheel object in the Inventory if it exists, if not returns null
	 * @return Wheel or null depending on the presence of a Wheel object in inventory
	 */
	public Wheel getWheel(){
		if(itemInventory[8].equals(new Wheel()))
			return (Wheel) itemInventory[8];
		
		for (int f=0; f<length; f++){
			if (itemInventory[f].equals(new Wheel())){
				return (Wheel) itemInventory[f];
			}
		}
		return null;
	}
	
	/**
	 * Returns the Axle object in the Inventory if it exists, if not returns null
	 * @return Axle or null depending on the presence of a Axle object in inventory
	 */
	public Axle getAxle(){
		if(itemInventory[4].equals(new Axle()))
			return (Axle) itemInventory[4];
		
		for (int f=0; f<length; f++){
			if (itemInventory[f].equals(new Axle())){
				return (Axle) itemInventory[f];
			}
		}
		return null;
	}
	
	/**
	 * Returns the Oxen object in the Inventory if it exists, if not returns null
	 * @return Oxen or null depending on the presence of a Oxen object in inventory
	 */
	public Oxen getOxen(){
		if(itemInventory[6].equals(new Oxen()))
			return (Oxen) itemInventory[6];
		
		for (int f=0; f<length; f++){
			if (itemInventory[f].equals(new Oxen())){
				return (Oxen) itemInventory[f];
			}
		}
		return null;
	}
	
	/**
	 * Returns the Tongue object in the Inventory if it exists, if not returns null
	 * @return Tongue or null depending on the presence of a Tongue object in inventory
	 */
	public Tongue getTongue(){
		if(itemInventory[7].equals(new Tongue()))
			return (Tongue) itemInventory[7];
		
		for (int f=0; f<length; f++){
			if (itemInventory[f].equals(new Tongue())){
				return (Tongue) itemInventory[f];
			}
		}
		return null;
	}
	
	/**
	 * Returns the price of an item from the input price array
	 * @param i the Item to be priced
	 * @param priceIn a 1D int array of prices
	 * @return price the int price value
	 */
	
	public int getPrice(Item i, int[] priceIn) {
		int price=666666;//returned when a critical error occurs
		int[] prices = priceIn;
		for (int z=0;z<9;z++){
			if(i.getName().equals("Ammo")){
				price = prices[0];
			}
			if(i.getName().equals("Food")){
				price = prices[1];
			}
			if(i.getName().equals("Medicine")){
				price = prices[2];
			}
			if(i.getName().equals("Water")){
				price = prices[3];
			}
			if(i.getName().equals("Axle")){
				price = prices[4];
			}
			if(i.getName().equals("Clothing")){
				price = prices[5];
			}
			if(i.getName().equals("Oxen")){
				price = prices[6];
			}
			if(i.getName().equals("Tongue")){
				price = prices[7];
			}
			if(i.getName().equals("Wheel")){
				price = prices[8];
			}
		}
		return price;
		
	}
	
	public String toString() {
		String ret = "Inventory:\n";
		for(Item i : itemInventory)
			ret += i.toString() + "\n";
		return ret;
	}

}
