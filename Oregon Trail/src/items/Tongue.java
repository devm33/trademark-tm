package items;

/**
 * class for wagon tongue item
 * @author Jaron
 *
 */
public class Tongue extends Item{
	/**
	 * set weight and multiplicity of wagon tongue
	 */
	public Tongue(){
		super(100,0,"Tongue");
	}

	@Override
	public void use(){
		if(getNumber() > 0)
			setNumber(getNumber()-1);
	}
}
