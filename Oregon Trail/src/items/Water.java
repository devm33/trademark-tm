package items;

/**
 * class for water item
 * @author Jaron
 *
 */
public class Water extends Item {

	//from super

	/**
	 * creates water with 6 lbs
	 */
	public Water() {
		super(6, 0, "Water");
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void use() {
		// TODO Auto-generated method stub
		if(this.getNumber()>0){
			this.setNumber(this.getNumber()-1);
		}
		
	}


	@Override
	public String getDescription() {
		return "A bucket of drinkable water.";
	}


	@Override
	public String getResponse() {
		return "Clean....maybe not. Drinkable....yes.";
	}


	@Override
	public String getBoughtResponse() {
		return "A bucket per day keeps death away.";
	}

}
