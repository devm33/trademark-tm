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
	/**
	 * constructor for the default store, Independence General Store
	 */
	public Store(){
		this.name = "Independence General Store";
	}
	/**
	 * constructor for a store
	 * @param name the name of the store
	 */
	public Store(String name){
		this.name = name;
		
	}
	/**
	 * the buy method for a given store
	 * @param i the item to buy
	 * @param num the number of that item to buy
	 * @param price the price of the item
	 */
	public void buy(Item i, int num, int price) throws InsufficientFundsException, WeightCapacityExceededException{
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
		catch(WeightCapacityExceededException w){
			throw new WeightCapacityExceededException();
		}
	}

}
