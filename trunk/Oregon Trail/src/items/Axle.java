package items;

import game.World;

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
		if(getNumber() > 0){
			setNumber(getNumber()-1);
			World.getWagon().setIsAxleBroken(false);
		}
	}

	@Override
	public String getDescription() {
		return "A long, heavy wooden wagon axle.";
	}

	@Override
	public String getResponse() {
		return "Long and heavy like my di...wagon axles.";
	}

	@Override
	public String getBoughtResponse() {
		return "You sure that's enough? You can never have too many heavy axles.";
	}
}
