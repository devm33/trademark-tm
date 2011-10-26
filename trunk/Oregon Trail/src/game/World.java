package game;

import gui.MainScreen;

/**
 * The World class holds the overarching organization for the Oregon Trail Game
 * Should hold towns, stores, buildings, events, RIVERS
 * 
 * @author Devraj Mehta
 *
 */
public class World {
	
	private static Wagon theWagon; //the user's wagon class containing the leader, travellers, inventory, etc.
	private Store firstStore;
	private Town firstTown; //hard-ish code for m6
	
	private MainScreen mainScreen;
	
	//private Map the Map; //GUI class for displaying the main map of the game.
	//private List<Town> towns; //the list of towns in the game
	
	//private location of sorts?
	
	//this class should also contain the events and should run them periodically/randomly
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		World thisWorld = new World();
	}
	
	public World() {
		//initialize first town and store as well as a wagon to be passed to 
		theWagon = new Wagon();
		firstTown = new Town();
		firstStore = firstTown.getStore();
		
		//initialize the main gui
		mainScreen = new MainScreen();
		
		//set the first store as the first store for the store screen
		mainScreen.setStore(firstStore);
		
		//start main game loop
		boolean game_running = true;
		while(game_running) {
			
			
			game_running = mainScreen.stepGame();
			
			
		}
		
		//kill the gui now that we're done
		mainScreen.disposeDisplay();
	}
	
	public static Wagon getWagon(){
		return theWagon;
	}

}
