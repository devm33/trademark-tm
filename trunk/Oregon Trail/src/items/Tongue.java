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

	@Override
	public String getDescription() {
		return "A wooden wagon tongue.";
	}

	@Override
	public String getResponse() {
		return "It's the latest and greatest in oxen-containing technology.";
	}

	@Override
	public String getBoughtResponse() {
		return "Your Ox will thank you for forcing them to wear that.";
	}
}