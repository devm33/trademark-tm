package game;

/**
 * class for clothing item
 * @author Jaron
 *
 */
public class Clothing extends Item {

	/**
	 * set weight and multiplicity of clothing
	 */
	public Clothing()
	{
		super(2, 0, "Clothing");
	}
	
	@Override
	public void use() 
	{
		if(getNumber() > 0)
			setNumber(getNumber()-1);
	}

}
