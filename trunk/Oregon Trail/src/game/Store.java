package game;
/**
 * Backend for Store GUI
 * 
 * @author Stephen
 * @author Jaron
 *
 */
import exceptions.InsufficientFundsException;
import exceptions.WeightCapacityExceededException;
import items.Item;

public class Store {
	private String name;
	private Inventory storeInventory;
	private int[][] prices;
	private int offset;
	/**
	 * constructor for the default store, Independence General Store
	 */
	public Store(){
		this.setName("Independence General Store");
		this.storeInventory = new Inventory();
		for (int y=0;y<9;y++){
			prices[y][0] = y;
		}
		prices[0][1] =2;
		prices[1][1] =5;
		prices[2][1] =10;
		prices[3][1] =0;
		prices[4][1] =10;
		prices[5][1] =10;
		prices[6][1] =40;
		prices[7][1] =10;
		prices[8][1] =10;

	}
	/**
	 * constructor for a store
	 * @param name the name of the store
	 */
	public Store(String name){
		this.setName(name);
		this.storeInventory = new Inventory();
		for (int y=0;y<9;y++){
			prices[y][0] = y;
		}
		prices[0][1] =(int) (2*(.5*offset));
		prices[1][1] =(int) (5*(.5*offset));
		prices[2][1] =10;
		prices[3][1] =0;
		prices[4][1] =10;
		prices[5][1] =10;
		prices[6][1] =40;
		prices[7][1] =10;
		prices[8][1] =10;

	
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
	 * @param price the price of the item
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
	
	public void setInventory(int sum_distance) {
		this.offset = (sum_distance/100);
		
	}
	
	public Inventory getInventory() {
		//TODO
		return storeInventory;
	}
	public int[][] getPrices(){
		return prices;
	}

}
