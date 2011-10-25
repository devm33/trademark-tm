package items;

import exceptions.InsufficientFoodException;
import game.World;

public class Food extends Item {
	//class needs adjustment for individual units of food.
	/**
	 * constructor for food
	 */
	public Food() {
		super(5, 0, "Food");
		int excess;
	}
	/**
	 * Method for using food with input ration used
	 * @param used the number of lbs of food used (increments of 5lbs) 
	 */
	public void use(int used) {
		
		//for loop to adjust number of used lbs based on people dead
		//increment excess based on number of lbs not eaten
		//if # of lbs unused reaches 5, available num of food units will increase by 1
		//if above triggered, set excess to 0 and begin again
		
		try {
			if (this.getNumber()>=used) {
				if (this.getNumber() > 0) {
					this.setNumber(this.getNumber() - used);
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
	 * Method for using food with ration rate drawn from the wagon class
	 */
	@Override
	public void use() {
		
		//for loop to adjust number of used lbs based on people dead
		//increment excess based on number of lbs not eaten
		//if # of lbs unused reaches 5, available num of food units will increase by 1
		//if above triggered, set excess to 0 and begin again
		
		int used =World.getWagon().getRations();
		try {
			if (this.getNumber()>=used) {
				if (this.getNumber() > 0) {
					this.setNumber(this.getNumber() - used);
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
