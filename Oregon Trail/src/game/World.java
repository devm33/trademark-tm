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
	
	private static Town currentTown; //the next town coming up or the one that we're currently in
	
	private MainScreen mainScreen; //the main GUI class
	
	private static Map theMap;
	
	//this class should also contain the events and should run them periodically/randomly
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		World thisWorld = new World();
	}
	
	public World() {
		//initialize first town and store as well as a wagon to be passed to 
		theWagon = new Wagon();
		theMap = new Map();
		//start off in a town
		currentTown = new Town();
		
		//initialize the main gui
		mainScreen = new MainScreen();
		
		//set the first store as the first store for the store screen
		mainScreen.setStore(currentTown.getStore());
		
		//start main game loop
		boolean game_running = true;
		while(game_running) {
			
			
			game_running = mainScreen.stepGame();
			theWagon.setTownDistance(theMap.distanceToTown(theWagon.getDistance()));
			
			//check if there is a town
			Town temp = theMap.getNextTown(theWagon.getDistance());
			if(temp != currentTown && temp != null && !mainScreen.inTown()) { 
				mainScreen.setStore(temp.getStore());
				currentTown = temp;
			}
		}
		
		//kill the gui now that we're done
		mainScreen.disposeDisplay();
	}
	
	/**
	 * returns the game wagon
	 * @return the game wagon
	 */
	public static Wagon getWagon(){
		return theWagon;
	}
	
	/**
	 * returns the current town
	 * @return the current town
	 */
	public static Town getTown(){
		return currentTown;
	}
	
	/**
	 * returns the game map
	 * @return the game map
	 */
	public static Map getMap(){
		return theMap;
	}
}
