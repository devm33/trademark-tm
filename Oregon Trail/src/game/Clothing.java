package game;

public class Clothing extends Item {

	
	public Clothing()
	{
		super(2, 4, "Clothing");
	}
	
	@Override
	public void use() 
	{
		if(getNumber() > 0)
			setNumber(getNumber()-1);
	}

}
