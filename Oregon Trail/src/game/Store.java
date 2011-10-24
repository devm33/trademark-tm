package game;
/**
 * Backend for Store GUI
 * 
 * @author Stephen
 * @author Jaron
 *
 */
import exceptions.InsufficentFundsException;
import exceptions.WeightCapacityExceededException;
import gui.MainScreen;
import items.*;
import items.Item;

public class Store {
	private String name;
	
	public Store(){
		this.name = "default";
		
	}
	
	public void buy(Item i, int num, int price){
		int buyNum = num;
		int availCash = Integer.parseInt(MainScreen.getWagon().getCash());
		int total = buyNum*price;
		try{
			MainScreen.getWagon().getLeader().setMoney(availCash-total);
			MainScreen.getWagon().addToInventory(i, buyNum);
		}
		catch(InsufficentFundsException f){
			
		}
		catch(WeightCapacityExceededException w){
			
		}
	}

}
