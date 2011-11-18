package people;

import exceptions.InsufficientFundsException;

/**
 * Abstract class to implement basic functions of all leader classes.
 * 
 * @author Devraj Mehta
 */
public abstract class Leader implements Person {
	
	protected int money, health, thirst, hunger;
	protected String name;
	private boolean isSick;
	private String illnessName;
	private boolean isPoisoned;
	private String poisonType;
	
	/**
	 * Create a new Leader with a given name.
	 * 
	 * @param name the Leader's name
	 */
	public Leader(String name) {
		this(100,0,0,name);
	}
	
	
	/**
	 * Create a new leader
	 * @param health
	 * @param thirst
	 * @param hunger
	 * @param name
	 */
	public Leader(int health, int thirst, int hunger, String name) {
		this.health = health;
		this.thirst = thirst;
		this.hunger = hunger;
		this.name = name;
		money = 0;
		this.isPoisoned = false;
		this.isSick = false;
		this.illnessName = null;
		this.poisonType = null;
	}



	/**
	 * Method to check Leader's money.
	 * 
	 * @return the amount of money the Leader has.
	 */
	public int getMoney() {
		return money;
	}
	
	/**
	 * Changes the Leader's money by a given amount. Make amount negative to withdrawal money from Leader.
	 * 
	 * @param change the amount to add to the Leader's money.
	 * @return the Leader's current funds.
	 * @throws InsufficientFundsException
	 */
	public int addMoney(int change) throws InsufficientFundsException {
		if(money + change < 0)
			throw new InsufficientFundsException();
		else
			money += change;
		return money;
	}
	
	/**
	 * Change the money
	 * @param newmoney
	 * @return
	 * @throws InsufficientFundsException
	 */
	public int setMoney(int newmoney) throws InsufficientFundsException {
		if(newmoney < 0)
			throw new InsufficientFundsException();
		else
			money = newmoney;
		return money;
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
	public int getThirst() {
		return thirst;
	}

	@Override
	public int getHunger() {
		return hunger;
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
	public void die() {
		health = 0;
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
		thirst -= amount;
		if(thirst < 0)
			thirst = 0;
		//TODO update health
	}

	@Override
	public void live() {
		if(health <= 0)
			return; //I ain't living.
		if(isSick)
			this.addHealth(-20);
		if(isPoisoned && this.poisonType.equals("poison"))
			this.addHealth(-20);
		if(isPoisoned && this.poisonType.equals("venom"))
			this.addHealth(-25);
		if(health <= 0)
			die();
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
	public String toString(){
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
		if(isPoisoned){
			return "POISONED";
		}
		if(isSick){
			return "SICK";
		}
		if(health==0){
			return "DEAD";
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
	
	public void hunt() {
		//TODO
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
	public void setHealed() {
		this.isPoisoned = false;
		this.isSick = false;
		this.illnessName = null;
		this.poisonType = null;
		
	}
	
	public void trade() {
		//TODO
	}
	@Override
	public String getSickness() {
		return this.illnessName;
	}

}
