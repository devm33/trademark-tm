package game;

/**
 * Backend for river.
 * @author Jaron
 *
 */
public class River {
	private String name;
	
	/**
	 * create a river with a name
	 * @param name name of river
	 */
	public River(String name){
		setName(name);
	}
	
	/**
	 * set river's name
	 * @param name new name for river
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * get river's name
	 * @return name of river
	 */
	public String getName() {
		return name;
	}
	
}
