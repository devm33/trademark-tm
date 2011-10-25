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
		// TODO Auto-generated constructor stub
	}
	/**
	 * Method for using food with input ration used
	 * @param used the number of lbs of food used (increments of 5lbs) 
	 */
	public void use(int used) {
		// TODO Auto-generated method stub
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Method for using food with ration rate drawn from the wagon class
	 */
	@Override
	public void use() {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
