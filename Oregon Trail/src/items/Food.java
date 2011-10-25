package items;

import people.Person;
import exceptions.InsufficientFoodException;
import game.World;

public class Food extends Item {
	private int excess;
	//class needs adjustment for individual units of food.
	/**
	 * constructor for food
	 */
	public Food() {
		super(5, 0, "Food");
		
	}
	/**
	 * Method for using food with input ration used which automatically conpensates for dead party members
	 * @param used the number of lbs of food used (increments of 5lbs) 
	 */
	public void use(int used) {
		int partyLiving = 0;

		for (Person m:World.getWagon().getPassengers()){
			if (m.getHealth()>0){
				partyLiving++;
			}
		}
		
		if(partyLiving<5){
			this.excess += 5-partyLiving;
			if(this.excess>5){
				this.excess -=5;
				this.setNumber(this.getNumber()+1);
			}
		}
		
		try {
			if (this.getNumber()>=(used*partyLiving)) {
				if (this.getNumber() > 0) {
					this.setNumber(this.getNumber() - (used*partyLiving));
				}
			}
			else{
				throw new InsufficientFoodException();
			}
		} catch (InsufficientFoodException e) {
			//e.printStackTrace();
		}
	}
	/**
	 * Method for using food with ration rate drawn from the wagon class that compensates for dead party members
	 */
	@Override
	public void use() {
		int partyLiving = 0;
		//for loop to adjust number of used lbs based on people dead
		//increment excess based on number of lbs not eaten
		//if # of lbs unused reaches 5, available num of food units will increase by 1
		//if above triggered, set excess to 0 and begin again
		
		for (Person m:World.getWagon().getPassengers()){
			if (m.getHealth()>0){
				partyLiving++;
			}
		}
		
		if(partyLiving<5){
			this.excess += 5-partyLiving;
			if(this.excess>5){
				this.excess -=5;
				this.setNumber(this.getNumber()+1);
			}
		}
		
		int used = World.getWagon().getRations();
		try {
			if (this.getNumber()>=(used*partyLiving)) {
				if (this.getNumber() > 0) {
					this.setNumber(this.getNumber() - (used*partyLiving));
				}
			}
			else{
				throw new InsufficientFoodException();
			}
		} catch (InsufficientFoodException e) {
			//e.printStackTrace();
		}
		
	}

}
