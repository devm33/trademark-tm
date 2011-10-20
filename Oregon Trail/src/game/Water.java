package game;

public class Water extends Item {

	//from super


	public Water(int num) {
		super(5, num, "Water");
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void use() {
		// TODO Auto-generated method stub
		this.setNumber(this.getNumber()-1);
	}

}
