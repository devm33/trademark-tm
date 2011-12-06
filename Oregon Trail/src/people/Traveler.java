package people;

import game.World;

/**
 * Class for inactive members of the wagon party.
 * 
 * 
 * @author Devraj Mehta
 *
 */

public class Traveler implements Person {
	
	private int health, thirst, hunger;
	private String name;
	private boolean isSick;
	private boolean isPoisoned;
	private String illnessName;
	private String poisonType;
	
	/**
	 * Create a new Traveler with a given name.
	 * 
	 * @param name the Traveler's name.
	 */
	public Traveler(String name) {
		this(100,0,0,name);
	}
	
	
	/**
	 * Create a new traveler
	 * @param health
	 * @param thirst
	 * @param hunger
	 * @param name
	 */
	public Traveler(int health, int thirst, int hunger, String name) {
		this.health = health;
		this.thirst = thirst;
		this.hunger = hunger;
		this.name = name;
		this.isPoisoned = false;
		this.isSick = false;
		this.illnessName = null;
		this.poisonType = null;
	}



	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public int addHealth(int change) {
		health += change;
		if(health < 0)
			health = 0;
		if(health > 100)
			health = 100;
		return health;
	}

	@Override
	public void setSickness(String type, String name) {
		if(type.equals("disease")){
			this.isSick = true;
			this.illnessName = name;
		}
		if(type.equals("poison")){
			this.isPoisoned = true;
			this.poisonType = name;
		}
	}

	@Override
	public void die() {
		health = 0;
	}
	
	@Override
	public int getThirst() {
		return thirst;
	}

	@Override
	public int getHunger() {
		return hunger;
	}
	
	@Override
	public void eatFood(int amount) {
		hunger -= amount;
		if(hunger < 0)
			hunger = 0;
		//TODO update health
	}

	@Override
	public void drinkWater(int amount) {
		//called with eating food
		int availWater = World.getWagon().getInventory().getWater().getNumber();
		if (availWater>0) {
			thirst -= amount*30;
			if (thirst < 0)
				thirst = 0;
			World.getWagon().getInventory().getWater().setNumber(availWater-1);
			//TODO update health
		}
		else{
			thirst += (amount);
		}
	}

	@Override
	public void live() {
		if(isSick)
			this.addHealth(-20);
		if(isPoisoned && this.poisonType.equals("poison"))
			this.addHealth(-20);
		if(isPoisoned && this.poisonType.equals("venom"))
			this.addHealth(-30);
		if(health <= 0){
			die();
			return;
		} //I ain't living.
		thirst += 30;
		if(thirst >= 100) {
			thirst = 100;
			die();
		}
		hunger += 15;
		if(hunger >= 100) {
			hunger = 100;
			health -= 15;
			if(health < 0)
				die();
		}
	}
	
	@Override
	public String toString() {
		if(isPoisoned){
			return name+": health= "+health+", hunger= "+hunger+", thirst= "+thirst+", status= poisoned";
		}
		if(isSick){
			return name+": health= "+health+", hunger= "+hunger+", thirst= "+thirst+", status= "+illnessName;
		}
		else return name+": health= "+health+", hunger= "+hunger+", thirst= "+thirst;
	}

	@Override
	public String getStatus() {
		//TODO
		if(health<=0){
			return "DEAD";
		}
		if(isPoisoned){
			return "POISONED";
		}
		if(isSick){
			return "SICK";
		}
		if(thirst>=50 && hunger>=50){
			return "THIRSTY"+" "+"HUNGRY";
		}
		if(thirst>=50){
			return "THIRSTY";
		}
		if(hunger>=50){
			return "HUNGRY";
		}
		else{
			return "HEALTHY";
		}
	}


	@Override
	public String getSickness() {
		return this.illnessName;
	}
	
	@Override
	public String getPoisonType(){
		return this.poisonType;
	}


	@Override
	public void setHealed() {
		this.isPoisoned = false;
		this.isSick = false;
		this.illnessName = null;
		this.poisonType = null;
		
	}
}
