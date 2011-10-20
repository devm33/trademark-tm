package game;
/**
 * Class to implement medicine items.
 * 
 * 
 * @author Devraj Mehta
 *
 */


public class Medicine extends Item {
	
	/**
	 * Keeps track of the number of uses of this medicine.
	 */
	private int uses;
	
	/**
	 * Creates a new medicine item with 0 inventory.
	 */
	public Medicine() {
		super(1,0,"Medicine");
	}
	
	@Override
	public void setNumber(int num) {
		super.setNumber(num);
		uses = num * 50; //50 uses in 1lb of medicine.
	}
	
	@Override
	public void use() {
		uses--;
		if(uses % 50 == 0)
			super.setNumber(getNumber()-1);
	}
	
}
