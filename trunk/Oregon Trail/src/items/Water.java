package items;

public class Water extends Item {

	//from super


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

}
