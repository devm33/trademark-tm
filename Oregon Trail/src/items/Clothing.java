package items;

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

	@Override
	public String getDescription() {
		return "A handknit cotton outfit";
	}

	@Override
	public String getResponse() {
		return "Wow your oxen with the latest 1848 rag fashion.";
	}

	@Override
	public String getBoughtResponse() {
		return "That brown matches well with your brown.";
	}

}
