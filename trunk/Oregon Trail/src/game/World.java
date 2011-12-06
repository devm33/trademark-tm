package game;

import items.Item;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import people.*;

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
	
	private static Town currentTown; //the next town coming up or the one that we're currently in
	
	private static MainScreen mainScreen; //the main GUI class
	
	private static Map theMap;
	
	private static Calendar calendar;
	
	private static int days;
	
	//this class should also contain the events and should run them periodically/randomly
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		World thisWorld = new World();
	}
	
	public World() {

		initializeGame();
		
		//start main game loop
		boolean game_running = true;
		while(game_running) {
			
			game_running = mainScreen.stepGame();
			theWagon.setTownDistance(theMap.distanceToTown()); //?
			
			
			//check if there is a town
			Town temp = theMap.getNextTown();
			if(temp != currentTown && temp != null && !mainScreen.inTown()) { 
				mainScreen.setTownAndStore(temp);
				currentTown = temp;
				theWagon.getInventory().getWater().setNumber(150);
			}
		}
		
		//kill the gui now that we're done
		mainScreen.quitGame();
	}
	
	public static void initializeGame() {
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
	}

	public static void restartGame(){
		//set initial date and days
		calendar = Calendar.getInstance();
		calendar.set(1848, 5, 1);
		days = 1;
		
		//initialize first town and store as well as a wagon to be passed to 
		theWagon.setCapacity(3500);
		theWagon.setDistance(0);
		theWagon.setTotalWeight(0);
		theWagon.resetInventory();
		wagonEvent = new Event(theWagon);
		theMap = new Map();
		//start off in a town
		currentTown = new Town();
		
		//initialize the main gui
		//mainScreen = new MainScreen();
		
		//set the first store as the first store for the store screen
		mainScreen.setTownAndStore(currentTown);	
	}
	
	/**
	 * Save the current game
	 */
	public static void saveGame() {
		int i = 0;
		String filename = "src/game/savedgames/savedgame_";
		File file = new File(filename + i);
		FileWriter fw = null;
		try {
			while(file.exists())
				file = new File(filename + ++i);
			//file.createNewFile();
			fw = new FileWriter(file);
			//leader name
			fw.write(theWagon.getLeader().getName() + "\n");
			//date
			Calendar c = Calendar.getInstance();
			fw.write(""+c.get(Calendar.MONTH)+"/"+c.get(Calendar.DATE)+"/"+c.get(Calendar.YEAR)+"\n");
			//	Wagon
			//distance
			fw.write(""+theWagon.getDistance()+"\n");
			//pace
			fw.write(""+theWagon.getPace()+"\n");
			//rations
			fw.write(""+theWagon.getRations()+"\n");
			//capacity
			fw.write(""+theWagon.getCapacity()+"\n");
			//total weight
			fw.write(""+theWagon.getTotalWeight()+"\n");
			//cash
			fw.write(""+theWagon.getCash()+"\n");
			//people in wagon
			for(Person t : theWagon.getPassengers()) {
				fw.write(""+t.getClass().getName()+"\n"+t.getName()+'\n'+t.getHealth()+'\n'+t.getHunger()+'\n'+t.getThirst()+"\n");
			}
			//inventory in wagon
			fw.write("inventory\n");
			for(Item item : theWagon.getInventory().getItemInventory())
				fw.write(""+item.getName()+"\n"+item.getNumber()+"\n");
			//current screen
			fw.write("screen\n");
			fw.write(mainScreen.getCurrentScreen() + "\n");
			fw.write("end\n");
			fw.close();
		} catch (IOException e) {
			System.out.println("problem creating file: " + filename + i);
			e.printStackTrace();
		}
	}
	
	/**
	 * Load a saved game
	 * @param game a string of the saved game
	 */
	public static boolean loadGame(String game) {
		try {
			String[] lines = game.split("\n");
			int i = 0;
			//wagon
			int distance = Integer.parseInt(lines[i++]);
			int pace = Integer.parseInt(lines[i++]);
			int rations = Integer.parseInt(lines[i++]);
			int capacity = Integer.parseInt(lines[i++]);
			int weight = Integer.parseInt(lines[i++]);
			int cash = Integer.parseInt(lines[i++]);
			//people
			String classname, name;
			int health, hunger, thirst;
			Leader leader = null;
			List<Traveler> members = new ArrayList<Traveler>();
			while(!lines[i].equals("inventory")) {
				classname = lines[i++];
				name = lines[i++];
				health = Integer.parseInt(lines[i++]);
				hunger = Integer.parseInt(lines[i++]);
				thirst = Integer.parseInt(lines[i++]);
				if(classname.equals("people.Banker"))
					leader = new Banker(health, hunger, thirst, name);
				else if(classname.equals("people.Farmer"))
					leader = new Farmer(health, hunger, thirst, name);
				else if(classname.equals("people.Carpenter"))
					leader = new Carpenter(health, hunger, thirst, name);
				else //people.Traveler
					members.add(new Traveler(health, hunger, thirst, name));	
			}
			i++; //inventory
			Inventory inventory = new Inventory();
			int number;
			while(!lines[i].equals("screen")) {
				name = lines[i++];
				number = Integer.parseInt(lines[i++]);
				inventory.getItemByName(name).setNumber(number);
			}
			i++; //screen
			mainScreen.setCurrentScreen(lines[i++]);
			//load it into the actual game play
			theWagon = new Wagon(pace, rations, capacity, weight, leader, members, distance, inventory,cash);
		} catch(Exception e) {
			System.out.println("Unable to load file: "+game);
			e.printStackTrace();
			return false;
		}
		return true;
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
	 * returns the current town
	 * @return the current town
	 */
	public static Town getCurrentTown(){
		return currentTown;
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
