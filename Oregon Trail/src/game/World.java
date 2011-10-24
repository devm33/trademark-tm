package game;

import gui.MainScreen;

/**
 * The World class holds the overarching organization for the Oregon Trail Game
 * 
 * 
 * @author Devraj Mehta
 *
 */
public class World {
	
	private Wagon theWagon; //the user's wagon class containing the leader, travellers, inventory, etc.
	private Store firstStore;
	private Town firstTown; //hard-ish code for m6
	
	private MainScreen mainScreen;
	
	//private Map the Map; //GUI class for displaying the main map of the game.
	//private List<Town> towns; //the list of towns in the game
	
	//this class should also contain the events and should run them periodically/randomly
	
	public static void main(String[] args) {
		World thisWorld = new World();
	}
	
	public World() {
		//initialize first town and store as well as a wagon to be passed to 
		theWagon = new Wagon();
		firstTown = new Town();
		firstStore = firstTown.getStore();
		
		//starting the main game loop
		mainScreen = new MainScreen();
		mainScreen.startGame();
		
		
		
		//next first town
		
		//next map (loop?)
	}
	
	public Wagon getWagon() {
		return theWagon;
	}
	

}
