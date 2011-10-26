package items;

/**
 * class for wagon axle
 * @author Jaron
 *
 */
public class Axle extends Item{
	/**
	 * set weight and multiplicity of wagon axle
	 */
	public Axle(){
		super(125,0,"Axle");
	}

	@Override
	public void use(){
		if(getNumber() > 0)
			setNumber(getNumber()-1);
	}
}
