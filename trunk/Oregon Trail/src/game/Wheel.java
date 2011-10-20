package game;

public class Wheel extends Item {

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
