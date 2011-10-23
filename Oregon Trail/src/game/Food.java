package game;

public class Food extends Item {

	
	
	//from super
	public Food() {
		super(5, 0, "Food");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		if(this.getNumber()>0){
			this.setNumber(this.getNumber()-1);
		}
	}

}
