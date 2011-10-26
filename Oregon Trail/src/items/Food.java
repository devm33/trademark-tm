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
	public void use(int ration) {
		int partyLiving = 0;

		for (Person m:World.getWagon().getPassengers()){
			if (m.getHealth()>0){
				partyLiving++;
			}
			else{
			}
		}
		
		int foodUsed = ration*partyLiving;
		
		try {
			if (this.getNumber()>=(int)(Math.ceil((this.getNumber()-foodUsed)/5.0))) {
				//this.setNumber(this.getNumber() - (ration*partyLiving));
				setNumber(getNumber()-(int)(Math.ceil((this.getNumber()-foodUsed)/5.0)));
				int unitUsed = (int)(Math.ceil((this.getNumber()-foodUsed)/5.0));
				this.excess = (5*unitUsed)-(partyLiving*ration);
				while (this.excess>5){
					this.excess -=5;
					setNumber(getNumber()+1);
				}
			}
			else {
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
		int ration = World.getWagon().getRations();
		int partyLiving = 0;

		for (Person m:World.getWagon().getPassengers()){
			if (m.getHealth()>0){
				partyLiving++;
			}
			else{
			}
		}
		
		int foodUsed = ration*partyLiving;
		
		try {
			if (this.getNumber()>=(int)(Math.ceil((this.getNumber()-foodUsed)/5.0))) {
				//this.setNumber(this.getNumber() - (ration*partyLiving));
				setNumber(getNumber()-(int)(Math.ceil((this.getNumber()-foodUsed)/5.0)));
				int unitUsed = (int)(Math.ceil((this.getNumber()-foodUsed)/5.0));
				this.excess = (5*unitUsed)-(partyLiving*ration);
				while (this.excess>5){
					this.excess -=5;
					setNumber(getNumber()+1);
				}
			}
			else {
				throw new InsufficientFoodException();
			}
		} catch (InsufficientFoodException e) {
			//e.printStackTrace();
		}
		
	}

}
