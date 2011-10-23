package game;

public class Water extends Item {

	//from super


	public Water() {
		super(7, 0, "Water");
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
