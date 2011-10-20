package game;

import java.util.List;
import java.util.ArrayList;

/**
 * The World class holds the overarching organization for the Oregon Trail Game
 * 
 * 
 * @author Devraj Mehta
 *
 */
public class World {
	private Wagon theWagon; //the user's wagon class containing the leader, travellers, inventory, etc.
	//private Map the Map; //GUI class for displaying the main map of the game.
	//private List<Town> towns; //the list of towns in the game
	
	//this class should also contain the events and should run them periodically/randomly
	
	public static void main(String[] args) {
		World theWorld = new World();
	}
	
	public World() {
		//open the config window to start game
		//Config window = new Config();
		//theWagon = window.startGame();
				
		System.out.println(theWagon);
		
		//next first town
		
		//next map (loop?)
	}
	
	

}
