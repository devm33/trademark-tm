package game;

/**
 * Backend for river.
 * @author Jaron
 *
 */
public class River {
	private String name;
	private int depth;
	private int ferryCost;
	
	/**
	 * create a river with a name
	 * @param name name of river
	 */
	public River(String name){
		setName(name);
		ferryCost = 25;
		depth = 4;
	}
	
	/**
	 * create a river with a name, a depth, and a cost
	 * @param name name of the river
	 * @param depth depth of the river
	 * @param cost cost of ferry to cross river
	 */
	public River(String name, int depth, int cost)
	{
		setName(name);
		setDepth(depth);
		setCost(cost);
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
	
	/**
	 * set river depth
	 * @param d depth of the river
	 */
	public void setDepth(int d)
	{
		depth = d;
	}
	
	/**
	 * get river depth
	 * @return depth of river
	 */
	public int getDepth()
	{
		return depth;
	}
	
	/**
	 * set cost of a ferry to cross river
	 * @param c cost of the ferry
	 */
	public void setCost(int c)
	{
		ferryCost = c;
	}
	
	/**
	 * get cost of a ferry to cross river
	 * @return the cost of the ferry
	 */
	public int getCost()
	{
		return ferryCost;
	}
	
}
