package game;

/**
 * backend for Town GUI
 * @author Jaron Gao
 * @author Stephen Bentley
 *
 */
public class Town {
	private Store townStore;
	private String townName;
	private int townImage;
	/**
	 * Constructs a town
	 * @param townName String name of the town
	 * @param townStore Store for the town
	 */
	public Town(String townName, Store townStore, int townImage){
		this.townName = townName;
		this.townStore = townStore;
		this.townImage = townImage;
	}
	/**
	 * Constructor for a town that creates a store based on the town name
	 * @param townName name of the town
	 * @param distance the distance of the town along the trail
	 */
	public Town(String townName, int distance, int townImage){
		this.townName = townName;
		this.townStore = new Store(townName+" General Store", distance);
		this.townImage = townImage;
	}
	/**
	 * Default constructor for the town and store, Independence
	 */
	public Town(){
		this.townName = "Independence";
		this.townStore = new Store();
		this.townImage = 1;
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
	/**
	 * Method to return town Image number
	 * @return townImage the number corresponding to the Town image
	 */
	public int getTownImage(){
		return townImage;
	}
}
