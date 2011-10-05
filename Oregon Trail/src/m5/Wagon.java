package m5;

import java.util.List;
import java.util.ArrayList;

/**
 * Wagon class.
 * 
 * @author Devraj Mehta
 * 
 */

public class Wagon {
	
	private int pace, rations, capacity;
	private int totalWeight;
	private List<Item> inventory;
	private List<Traveler> members;
	private Leader leader;
	
	
	/**
	 * Create a new Wagon.
	 * 
	 * @param pace
	 * @param rations
	 * @param capacity
	 * @param members
	 * @param leader
	 */
	public Wagon(int pace, int rations, int capacity, List<Traveler> members,
			Leader leader) {
		this.pace = pace;
		this.rations = rations;
		this.capacity = capacity;
		this.members = members;
		this.leader = leader;
		totalWeight = 0;
		inventory = new ArrayList<Item>();
	}

	/**
	 * 
	 * @return the Wagon's current pace setting.
	 */
	public int getPace() {
		return pace;
	}
	
	/**
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
	public List<Item> getInventory() {
		return inventory;
	}
	
	/**
	 * Adds an Item to the Wagon's inventory if possible.
	 * 
	 * @param i the item to add
	 * @throws WeightCapacityExceededException
	 */
	public void addToInventory(Item i) throws WeightCapacityExceededException {
		if(i.getWeight() + totalWeight > capacity)
			throw new WeightCapacityExceededException();
		inventory.add(i);
		totalWeight += i.getWeight();
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
		inventory.addAll(l);
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
		inventory.remove(i);
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
		inventory.removeAll(l);
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
	
	
}
