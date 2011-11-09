package game;

/**
 * backend for Town GUI
 * @author Jaron
 * @author Stephen
 *
 */
public class Town {
	private Store townStore;
	private String townName;
	/**
	 * Constructs a town
	 * @param townName String name of the town
	 * @param townStore Store for the town
	 */
	public Town(String townName, Store townStore){
		this.townName = townName;
		this.townStore = townStore;
	}
	/**
	 * Constructor for a town that creates a store based on the town name
	 * @param townName
	 */
	public Town(String townName, int distance){
		this.townName = townName;
		this.townStore = new Store(townName+" General Store", distance);
	}
	/**
	 * Default constructor for the town and store, Independence
	 */
	public Town(){
		this.townName = "Independence";
		this.townStore = new Store();
	}
	/**
	 * Method to return the store
	 * @return townStore the store of this Town object
	 */
	public Store getStore(){
		return townStore;
	}
	/**
	 * Method to return the town Name
	 * @return townName the name of the Town object
	 */
	public String getTownName(){
		return townName;
	}

}
