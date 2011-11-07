package game;

import items.Item;
import items.Food;
import items.Water;
import game.Inventory;

import java.util.List;
import java.util.ArrayList;

import exceptions.ItemNotFoundException;
import exceptions.WeightCapacityExceededException;

import people.Leader;
import people.Person;
import people.Traveler;

/**
 * Wagon class.
 * 
 * @author Devraj Mehta
 */

public class Wagon {
	
	private int pace, rations, capacity, distance, townDistance;
	private int totalWeight;
	private Inventory inventory;
	private List<Traveler> members;
	private Leader leader;
	
	
	/**
	 * Better constructor for wagon -- blank.
	 */
	public Wagon() {
		capacity = 3500; //this should just stay here.
		distance = 0;
		totalWeight = 0;
		inventory = new Inventory();
	}
	
	
	/**
	 * Create a new Wagon.
	 * 
	 * @param pace
	 * @param rations
	 * @param capacity
	 * @param leader
	 */
	public Wagon(int pace, int rations, int capacity,
			Leader leader, List<Traveler> members) {
		this.pace = pace;
		this.rations = rations;
		this.capacity = capacity;
		this.leader = leader;
		this.members = members;
		distance = 0;
		totalWeight = 0;
		inventory = new Inventory();
	}
	
	/**
	 * set a new leader
	 * @param l the new leader object
	 */
	public void setLeader(Leader l){
		leader = l;
	}
	
	/**
	 * set party to a new list of members
	 * @param l the list of new party members
	 */
	public void setMembers(List<Traveler> l) {
		members = l;
	}

	/**
	 * returns the wagon's current pace
	 * @return the Wagon's current pace setting.
	 */
	public int getPace() {
		return pace;
	}
	
	/**
	 * @0 miles traveled a day - Stopped Pace
	 * @5 miles traveled a day - Leisurely Pace
	 * @10 miles traveled a day - Steady Pace
	 * @15 miles traveled a day - Grueling Pace
	 * 
	 * @param pace the Wagon's new pace setting.
	 */
	public void setPace(int pace) {
		this.pace = pace;
	}
	
	/**
	 * 
	 * @return the Wagon's current rations setting.
	 */
	public int getRations() {
		return rations;
	}
	
	/**
	 * 
	 * @param rations the Wagon's new rations setting.
	 */
	public void setRations(int rations) {
		this.rations = rations;
	}
	
	/**
	 * 
	 * @return the Wagon's capacity.
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * 
	 * @param capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * 
	 * @return the distance the Wagon has traveled
	 */
	public int getDistance()
	{
		return distance;
	}
	
	/**
	 * gets the current distance from previous town to next town
	 * @return the distance to next town from previous town
	 */
	public int getTownDistance(){
		return townDistance;
	}
	
	/**
	 * sets the current distance after previous town
	 * @param a the new distance after previous town
	 */
	public void setTownDistance(int a){
		townDistance = a;
	}
	
	/**
	 * 
	 * @return this Wagon's Leader.
	 */
	public Leader getLeader() {
		return leader;
	}
	
	/**
	 * Get a list of people in this wagon. Includes leader as first member.
	 * 
	 * @return List of Persons in this Wagon.
	 */
	public List<Person> getPassengers() {
		List<Person> l = new ArrayList<Person>();
		l.add(leader);
		l.addAll(members);
		return l;
	}
	
	/**
	 * 
	 * @return the Wagon's inventory in a list of Items.
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	/**
	 * Adds an Item to the Wagon's inventory if possible.
	 * 
	 * @param i the item to add
	 * @throws WeightCapacityExceededException
	 */
	public void addToInventory(Item i, int n) throws WeightCapacityExceededException {
		if((i.getWeight()*n) + totalWeight > capacity)
			throw new WeightCapacityExceededException();
		else{
			for (int x=0; x<inventory.getLength(); x++){
				if(inventory.getItemInventory()[x]!=null){
					if(i.equals(inventory.getItemInventory()[x])){
						totalWeight += (i.getWeight()*n);
						World.getWagon().getInventory().getItemInventory()[x].setNumber((inventory.getItemInventory()[x].getNumber()+n));
						//System.out.println("weight: "+totalWeight);
						//System.out.println("item: "+inventory.getItemInventory()[x].getName());
					}
				}
			}
		}
		
	}
	
	/**
	 * Adds a list of Items to the Wagon's inventory. 
	 * Adds none of the items if the total weight would 
	 * be higher than the wagon's capacity.
	 * 
	 * @param l list of items to add.
	 * @throws WeightCapacityExceededException
	 */
	public void addToInventory(List<Item> l) throws WeightCapacityExceededException {
		int weight = sumWeight(l);
		if(weight + totalWeight > capacity)
			throw new WeightCapacityExceededException();
		totalWeight += weight;
	//	inventory.addAll(l);//needs compatibility fix with Inventory.java
	}
	
	/**
	 * Removes an Item from the inventory.
	 * 
	 * @param i Item to remove.
	 * @throws ItemNotFoundException
	 */
	public void removeFromInventory(Item i) throws ItemNotFoundException {
		if(!inventory.contains(i))
			throw new ItemNotFoundException();
		totalWeight -= i.getWeight();
	//	inventory.remove(i);//needs compatibility fix with Inventory.java
	}
	
	/**
	 * Removes a list of Items from the Wagon's inventory. Removes no items if any item isn't found in the wagon's inventory.
	 * 
	 * @param l list of Items to remove from the inventory.
	 * @throws ItemNotFoundException
	 */
	public void removeFromInventory(List<Item> l) throws ItemNotFoundException {
		for(Item i : l)
			if(!inventory.contains(i))
				throw new ItemNotFoundException();
		totalWeight -= sumWeight(l);
	//	inventory.removeAll(l);//needs compatibility fix with Inventory.java
	}
	
	/**
	 * 
	 * @param l List of Items to sum weight.
	 * @return the total weight of the Items in the list.
	 */
	private int sumWeight(List<Item> l) {
		int weight = 0;
		for(Item i : l)
			weight += i.getWeight();
		return weight;
	}
	
	/**
	 * toString method for Wagon class.
	 * 
	 * @return string representing this wagon class.
	 */
	public String toString(){
		String str = "";
		str += leader;
		for(Traveler t : members)
			str += ", " + t;
		return str;
	}
	
	/**
	 * 
	 * @return the amount of money the leader of this wagon has.
	 */
	public String getCash(){
		String total = Integer.toString(leader.getMoney());
		return total;
	}
	
	/**
	 * 
	 * @return the current weight of the wagon.
	 */
	public int getTotalWeight(){
		return totalWeight;
	}
	
	/**
	 * Updates the distance, food amount, and water amount for the Wagon
	 * based on the pace, ration rate, and water rate respectively
	 * 
	 */
	public void takeStep()
	{
		Food f = inventory.getFood();
		Water w = inventory.getWater();
		
		leader.live(); //Live dammnit LIVE!
		for(Traveler t : members) {
			t.live();
		}
		
		//check if anyone's still alive
		boolean alive = false;
		for(Person p : this.getPassengers())
			if(p.getHealth() > 0)
				alive = true;
		if(!alive) {
			System.out.println("Everyone died. You lose.");
			System.exit(0);
		}
		
		distance += pace;
		townDistance += pace;
		f.use();
		w.use();
		
	}
}
