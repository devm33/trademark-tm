package items;
/**
 * oxen item
 * @author Jaron
 *
 */
public class Oxen extends Item{
	/**
	 * set weight and multiplicity of clothing
	 */
	public Oxen(){
		super(0,0,"Oxen");
	}
	
	@Override
	public void use(){
		//how does one use oxen?
	}

	@Override
	public String getDescription() {
		return "A pair of strong animals used to pull your wagon.";
	}

	@Override
	public String getResponse() {
		return "Freshly stolen from my neighbor's ranch.";
	}

	@Override
	public String getBoughtResponse() {
		return "Don't worry, I only stole the good ones.";
	}
}
