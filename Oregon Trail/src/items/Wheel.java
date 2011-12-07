package items;

import game.World;

/**
 * class for wheel item
 * @author Jaron
 *
 */
public class Wheel extends Item {

	/**
	 * sets wheel's weight to 75 pounds
	 */
	public Wheel()
	{
		super(75, 0, "Wheel");
	}
	
	@Override
	public void use() 
	{
		if(getNumber() > 0){
			setNumber(getNumber()-1);
			World.getWagon().setIsWheelBroken(false);
		}
	}

	@Override
	public String getDescription() {
		return "A big, round wooden wagon wheel.";
	}

	@Override
	public String getResponse() {
		return "Big and round like my wife.";
	}

	@Override
	public String getBoughtResponse() {
		return "Spike attachments sold separately.";
	}

}
