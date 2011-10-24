package game;

import items.Ammo;
import items.Food;
import items.Item;
import items.Medicine;
import items.Water;

/**
 * Backend for Inventory GUI
 * @author Jaron
 *
 */
public class Inventory {
	private Item[]itemInventory;
	private int length = 6;
	
	
	public Inventory(){
		itemInventory = new Item[length];
		itemInventory[0] = new Ammo();
		itemInventory[1] = new Food();
		itemInventory[2] = new Medicine();
		itemInventory[3] = new Water();
	}
	
	public Inventory(Item item0,Item item1, Item item2){
		itemInventory = new Item[length];
		itemInventory[0] = item0;
		itemInventory[1] = item1;
		itemInventory[2] = item2;
	}
	
	public Inventory(Item item0,Item item1, Item item2, Item item3, Item item4){
		itemInventory = new Item[length];
		itemInventory[0] = item0;
		itemInventory[1] = item1;
		itemInventory[2] = item2;
		itemInventory[3] = item3;
		itemInventory[4] = item4;
	}
	public int getLength(){
		return length;
	}
	
	public Item getItem(int x){
		return itemInventory[x];
	}
	
	public void setItemNum(int item, int num){
		itemInventory[item].setNumber(num);
	}
	
	public Item[] getItemInventory(){
		return itemInventory;
	}
	
	public boolean contains(Item i) {
		// TODO Auto-generated method stub
		for (int x=0; x<length; x++){
			if (itemInventory[x].getName()==i.getName()){
				return true;
			}
			
		}
		return false;
	}

}
