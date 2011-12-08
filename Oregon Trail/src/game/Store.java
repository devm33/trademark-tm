package game;


import exceptions.InsufficientFundsException;
import exceptions.WeightCapacityExceededException;
import items.*;
import items.Item;
/**
 * Backend logic for Store GUI
 * 
 * @author Stephen Bentley
 * @author Jaron Gao
 *
 */
public class Store {
	private String name;
	private Inventory storeInventory;
	private int[] prices = new int[9];
	private int offset;
	/**
	 * constructor for the default store, Independence General Store
	 */
	public Store(){
		this.setName("Independence General Store");
		this.storeInventory = new Inventory();

		prices[0]=2;
		prices[1]=5;
		prices[2]=10;
		prices[3]=0;
		prices[4]=10;
		prices[5]=10;
		prices[6]=40;
		prices[7]=10;
		prices[8]=10;

	}
	/**
	 * constructor for a store
	 * @param name the name of the store
	 * @param distance the distance along the trail of the town containing this store
	 */
	public Store(String name, int distance){
		this.setName(name);
		this.offset = (distance/100);
		
		if(offset<=6){
			this.storeInventory = new Inventory();
		}
		if(6<offset&&offset<=11){
			this.storeInventory = new Inventory(new Ammo(),new Food(),new Medicine(),new Water(),new Axle(),new Oxen(),new Tongue());
		}
		if(11<offset&&offset<=15){
			this.storeInventory = new Inventory(new Ammo(),new Food(),new Axle(),new Wheel(),new Clothing());
		}
		if(15<offset){
			this.storeInventory = new Inventory(new Food(),new Oxen(),new Medicine());
		}
		

		prices[0]=(int) (2*(.5*offset));
		prices[1]=(int) (5*(.5*offset));
		prices[2]=(int) (10*(.5*offset));
		prices[3]=(int) (0*(.5*offset));
		prices[4]=(int) (10*(.5*offset));
		prices[5]=(int) (10*(.5*offset));
		prices[6]=(int) (40*(.5*offset));
		prices[7]=(int) (10*(.5*offset));
		prices[8]=(int) (10*(.5*offset));

	
	}
	/**
	 * the buy method for a given store
	 * @param i the item to buy
	 * @param num the number of that item to buy
	 * @param price the price of the item
	 */
	public void buy(Item i, int num, int price, int weight) throws InsufficientFundsException, WeightCapacityExceededException{
		int newWeight = World.getWagon().getTotalWeight() + (num*weight);
		if(newWeight < World.getWagon().getCapacity()){
			int buyNum = num;
			int availCash = Integer.parseInt(World.getWagon().getCash());
			int total = buyNum*price;
			try{
				World.getWagon().getLeader().setMoney((availCash-total));
				World.getWagon().addToInventory(i, buyNum);
			}
			catch(InsufficientFundsException f){
				throw new InsufficientFundsException();
			}
		} else {
			throw new WeightCapacityExceededException();
		}
	}
	/**
	 * the buy method for a given store
	 * @param i the item to buy
	 * @param num the number of that item to buy
	 */
	public void buy(Item i, int num) throws InsufficientFundsException, WeightCapacityExceededException{
		int newWeight = World.getWagon().getTotalWeight() + (num*i.getWeight());
		int price = storeInventory.getPrice(i,getPrices());
		
		
		if(newWeight < World.getWagon().getCapacity()){
			int buyNum = num;
			int availCash = Integer.parseInt(World.getWagon().getCash());
			int total = buyNum*price;
			try{
				World.getWagon().getLeader().setMoney((availCash-total));
				World.getWagon().addToInventory(i, buyNum);
			}
			catch(InsufficientFundsException f){
				throw new InsufficientFundsException();
			}
		} else {
			throw new WeightCapacityExceededException();
		}
	}
	/**
	 * setter for store name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getter for store name
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * retrieves the store inventory
	 * @return storeInventory
	 */
	public Inventory getInventory() {
		//TODO
		return storeInventory;
	}
	/**
	 * returns the prices after their calculations from the store constructor
	 * @return prices for this store
	 */
	public int[] getPrices(){
		return prices;
	}
	/**
	 * the distance offset used for item availability and prices
	 * @return the offset value
	 */
	public int getOffset(){
		return offset;
	}

}
