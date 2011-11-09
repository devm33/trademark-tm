package items;

import people.Person;
import exceptions.InsufficientFoodException;
import game.World;

public class Food extends Item {
	private int excess;
	//class needs adjustment for individual units of food.
	//write getter for excess
	//mention update to GUI display of food left for wagon
	/**
	 * constructor for food
	 */
	public Food() {
		super(5, 0, "Food");
		
	}
	/**
	 * Method for using food with input ration used which automatically compensates for dead party members
	 * @param ration int used the number of lbs of food used (increments of 5lbs) 
	 */
	public void use(int ration) {
		
		if (World.getWagon().getRations()!=0) {
			int partyLiving = 0;
			for (Person m : World.getWagon().getPassengers()) {
				if (m.getHealth() > 0) {
					partyLiving++;
				} else {
				}
			}
			int foodUsed = ration * partyLiving;
			int unitUsed = (int) Math.ceil(foodUsed/5.0);
			try {
				if (this.getNumber() >= unitUsed) {
					setNumber(getNumber() - unitUsed);
					this.excess = (5 * unitUsed) - (partyLiving * ration);
					while (this.excess > 5) {
						this.excess -= 5;
						setNumber(getNumber() + 1);
					}
					for(Person p : World.getWagon().getPassengers()){
						p.eatFood(ration*5);
						p.drinkWater(30);
					}
				} else {
					throw new InsufficientFoodException();
				}
				
			} catch (InsufficientFoodException e) {
				//e.printStackTrace();
				for(Person p : World.getWagon().getPassengers()){
					p.addHealth(-15);
					p.drinkWater(60);
				}
			}
		}
		else{
			for(Person p : World.getWagon().getPassengers()){
				p.addHealth(-15);
				p.drinkWater(60);
			}
		}
	}
	/**
	 * Method for using food with ration rate drawn from the wagon class that compensates for dead party members
	 */
	@Override
	public void use() {
		
		if (World.getWagon().getRations()!=0) {
			int ration = World.getWagon().getRations();
			int partyLiving = 0;
			for (Person m : World.getWagon().getPassengers()) {
				if (m.getHealth() > 0) {
					partyLiving++;
				} else {
				}
			}
			int foodUsed = ration * partyLiving;
			int unitUsed = (int) Math.ceil(foodUsed/5.0);
			try {
				if (this.getNumber() >= unitUsed) {
					setNumber(getNumber() - unitUsed);
					this.excess = (5 * unitUsed) - (partyLiving * ration);
					while (this.excess > 5) {
						this.excess -= 5;
						setNumber(getNumber() + 1);
					}
					for(Person p : World.getWagon().getPassengers()){
						if (ration>2){
							for(Person r : World.getWagon().getPassengers()){
								r.addHealth(5);
							}
							if(ration>3){
								for(Person q : World.getWagon().getPassengers()){
									q.addHealth(5);
								}
							}
						}
						p.eatFood(ration*5);
						p.drinkWater(30);
					}
				} else {
					throw new InsufficientFoodException();
				}
				
			} catch (InsufficientFoodException e) {
				//e.printStackTrace();
				for(Person p : World.getWagon().getPassengers()){
					p.addHealth(-15);
					p.drinkWater(60);
				}
			}
		}
		else{
			for(Person p : World.getWagon().getPassengers()){
				p.addHealth(-15);
				p.drinkWater(60);
			}
		}
		
	}
	@Override
	public String getDescription() {
		return "A durable canned foodstuff.";
	}
	@Override
	public String getResponse() {
		return "Better than my wife's poor excuse for cooking.";
	}
	@Override
	public String getBoughtResponse() {
		return "It won't expire for another hour or two.";
	}

}
