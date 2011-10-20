package game;

public class Food extends Item {

	
	
	//from super
	public Food(int w) {
		super(w);
		// TODO Auto-generated constructor stub
	}

	public Food(Item i, int num) {
		super(i, num);
		// TODO Auto-generated constructor stub
	}

	public Food(int w, int num) {
		super(w, num);
		// TODO Auto-generated constructor stub
	}

	public Food(int w, int num, String n) {
		super(w, num, n);
		// TODO Auto-generated constructor stub
	}

	public Food(Item i) {
		super(i);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		this.setNumber(this.getNumber()-1);
	}

}
