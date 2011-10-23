package game;
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
		if(getNumber() > 0)
			setNumber(getNumber()-1);
	}

}
