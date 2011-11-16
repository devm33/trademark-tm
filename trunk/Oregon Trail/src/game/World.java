package game;

import java.util.Calendar;

import gui.MainScreen;

/**
 * The World class holds the overarching organization for the Oregon Trail Game
 * Should hold towns, stores, buildings, events, RIVERS
 * 
 * @author Devraj Mehta
 *
 */
public class World {
	
	private static Event wagonEvent; //the random events that affect the user and wagon
	
	private static Wagon theWagon; //the user's wagon class containing the leader, travellers, inventory, etc.
	
	private Town currentTown; //the next town coming up or the one that we're currently in
	
	private MainScreen mainScreen; //the main GUI class
	
	private static Map theMap;
	
	private static Calendar calendar;
	
	private static int days;
	
	//this class should also contain the events and should run them periodically/randomly
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		World thisWorld = new World();
	}
	
	public World() {
		//set initial date and days
		calendar = Calendar.getInstance();
		calendar.set(1848, 5, 1);
		days = 1;
		
		//initialize first town and store as well as a wagon to be passed to 
		theWagon = new Wagon();
		wagonEvent = new Event(theWagon);
		theMap = new Map();
		//start off in a town
		currentTown = new Town();
		
		//initialize the main gui
		mainScreen = new MainScreen();
		
		//set the first store as the first store for the store screen
		mainScreen.setTownAndStore(currentTown);
		
		//start main game loop
		boolean game_running = true;
		while(game_running) {
			
			
			game_running = mainScreen.stepGame();
			theWagon.setTownDistance(theMap.distanceToTown());
			
			//check if there is a town
			Town temp = theMap.getNextTown();
			if(temp != currentTown && temp != null && !mainScreen.inTown()) { 
				mainScreen.setTownAndStore(temp);
				currentTown = temp;
			}
		}
		
		//kill the gui now that we're done
		mainScreen.disposeDisplay();
	}
	
	/**
	 * increment days each time user moves
	 */
	public static void nextDay(){
		days++;
		calendar.add(Calendar.DATE, 1);
	}
	
	/**
	 * returns the game wagon
	 * @return the game wagon
	 */
	public static Wagon getWagon(){
		return theWagon;
	}
	
	public static Event getEvent(){
		return wagonEvent;
	}
	
	/**
	 * returns the game map
	 * @return the game map
	 */
	public static Map getMap(){
		return theMap;
	}
	
	/**
	 * returns number of days traveled
	 * @return
	 */
	public static int getDays(){
		return days;
	}

	public static String getDate(){
		String date = " "+calendar.get(Calendar.DATE)+", "+calendar.get(Calendar.YEAR);
		String month = "";
		switch(calendar.get(Calendar.MONTH)){
		case 1:
			month = "January";
			break;
		case 2:
			month = "February";
			break;
		case 3:
			month = "March";
			break;
		case 4: 
			month = "April";
			break;
		case 5: 
			month = "May";
			break;
		case 6: 
			month = "June";
			break;
		case 7:
			month = "July";
			break;
		case 8:
			month = "August";
			break;
		case 9:
			month = "September";
			break;
		case 10:
			month = "October";
			break;
		case 11:
			month = "November";
			break;
		case 12: 
			month = "December";
			break;
		default:
			month = "HURRR";
			break;
		}
		date = month + date;
		return date;
	}
}
