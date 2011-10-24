package game;

/**
 * backend for Town GUI
 * @author Jaron
 *
 */
public class Town {
	private Store townStore;
	private String townName;
	
	public Town(String townName, Store townStore){
		this.townName = townName;
		this.townStore = townStore;
	}
	
	public Town(){
		this.townName = "Indpendence";
		this.townStore = new Store();
	}
	
	public Store getTownStore(){
		return townStore;
	}
	public String getTownName(){
		return townName;
	}

}
