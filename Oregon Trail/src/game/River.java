package game;

import exceptions.InsufficientFundsException;
import items.Food;
import people.Leader;
import java.lang.Math;
import game.World;

/**
 * Backend for river.
 * @author Jaron
 *
 */
public class River {
	private String name;
	private int depth;
	private int ferryCost;
	
	/**
	 * create a river with a name
	 * @param name name of river
	 */
	public River(String name){
		setName(name);
		ferryCost = 25;
		depth = 4;
	}
	
	/**
	 * create a river with a name, a depth, and a cost
	 * @param name name of the river
	 * @param depth depth of the river
	 * @param cost cost of ferry to cross river
	 */
	public River(String name, int depth, int cost)
	{
		setName(name);
		setDepth(depth);
		setCost(cost);
	}
	
	/**
	 * set river's name
	 * @param name new name for river
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * get river's name
	 * @return name of river
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * set river depth
	 * @param d depth of the river
	 */
	public void setDepth(int d)
	{
		depth = d;
	}
	
	/**
	 * get river depth
	 * @return depth of river
	 */
	public int getDepth()
	{
		return depth;
	}
	
	/**
	 * set cost of a ferry to cross river
	 * @param c cost of the ferry
	 */
	public void setCost(int c)
	{
		ferryCost = c;
	}
	
	/**
	 * get cost of a ferry to cross river
	 * @return the cost of the ferry
	 */
	public int getCost()
	{
		return ferryCost;
	}
	
	/**
	 *  Runs the situation where the user chooses to take a ferry
	 */
	public void takeFerry() throws InsufficientFundsException
	{
		//fishing and water refill
		World.getWagon().getInventory().getWater().setNumber(200);
		World.getWagon().getInventory().getFood().setNumber(World.getWagon().getInventory().getWater().getNumber()+10);

		Leader leader = World.getWagon().getLeader();
		if(leader.getMoney() < getCost())
			throw new InsufficientFundsException();
		else
		{
			leader.setMoney(leader.getMoney()-getCost());
			World.getWagon().setNotification("You spent some money to cross safely.");
			System.out.println("FERRY RESULT");
		}
	}
	
	/**
	 * Runs the situation where the user chooses to ford the river
	 */
	public void ford()
	{		
		//fishing and water refill
		World.getWagon().getInventory().getWater().setNumber(200);
		World.getWagon().getInventory().getFood().setNumber(World.getWagon().getInventory().getWater().getNumber()+10);


		int fordChance = (int)(Math.random()*10 + 1);
		
		if(getDepth() >= 3 || fordChance > 7){
			Food f = World.getWagon().getInventory().getFood();
			if(f.getNumber() > 50)
				f.setNumber(f.getNumber()-20);
			else
				f.setNumber(f.getNumber()-1);
			World.getWagon().setNotification("You forded across the river but lost some food");
			//System.out.println("BAD FORD RESULT");
		}
		else{
			World.getWagon().setNotification("You successfully ford across the river.");
			//System.out.println("GOOD FORD RESULT");
		}
	}
	
	/**
	 * Runs the situation where the user chooses to caulk the wagon
	 */
	public void caulk()
	{		
		//fishing and water refill
		World.getWagon().getInventory().getWater().setNumber(200);
		World.getWagon().getInventory().getFood().setNumber(World.getWagon().getInventory().getWater().getNumber()+10);

		int caulkChance = (int)(Math.random()*10 + 1);
		
		if(caulkChance > 4){
			Food f = World.getWagon().getInventory().getFood();
			if(f.getNumber() > 50)
				f.setNumber(f.getNumber()-20);
			else
				f.setNumber(f.getNumber()-1);
			World.getWagon().setNotification("You crossed the river but lost some food");
			//System.out.println("BAD CAULK RESULT");
		}
		else{
			World.getWagon().setNotification("You successfully crossed the river by caulking the wagon");
			//System.out.println("GOOD CAULK RESULT");
		}
	}
	
	/**
	 * @return String representation of this river
	 */
	public String toString() {
		return name;
	}
}
