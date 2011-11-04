package game;
import java.io.*;
import java.util.*;
/**
 * Class representing the map; keeps track of where everything is.
 * 
 * @author Devraj Mehta
 *
 * NOTE: only keeps track of towns right now
 */

public class Map {
	private List<TownPair> towns;
	
	private List<RiverPair> rivers;
	
	/**
	 * Create the map object.
	 */
	public Map() {
		Scanner scan = null;
		//Read in the river and town lists
		rivers = new ArrayList<RiverPair>();
		towns = new ArrayList<TownPair>();
		try {
			scan = new Scanner(new File("src/game/locations.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		String line = "";
		int index;
		int sum_distance = 0;
		while(scan.hasNext()) {
			line = scan.nextLine();
			index = line.indexOf(',');
			String townOrRiver = line.substring(0, index);
			line = line.substring(index+1);
			index = line.indexOf(',');
			String name = line.substring(0, index);
			line = line.substring(index+1);
			index = line.indexOf(',');
			int distance = Integer.parseInt(line.substring(0, index));
			sum_distance += distance;
			line = line.substring(index+1);
			if(townOrRiver.equals("T")) {
				Town temp;
				if(line.equals("Yes"))
					temp = new Town(name);
				else
					temp = new Town(name, null);
				towns.add(new TownPair(temp,sum_distance));
			}
			else
				rivers.add(new RiverPair(new River(name), sum_distance));
		}
	}
	
	/**
	 * Return the next town to this distance
	 * @param distance
	 * @return the next closest town
	 */
	public Town getNextTown(int distance) {
		for(TownPair t : towns) {
			if(t.distance > distance)
				return t.town;
		}
		return null;
	}
	
	/**
	 * Return the distance to the next town.
	 * @param distance the distance already traveled
	 * @return the distance to the next town
	 */
	public int distanceToTown(int distance) {
		for(TownPair t : towns) {
			if(t.distance > distance){
				return t.distance - distance;
			}
		}
		return 0;
	}
	
	/**
	 * Return the next river to this distance
	 * @param distance
	 * @return the next closest river
	 */
	public River getNextRiver(int distance) {
		for(RiverPair t : rivers) {
			if(t.distance > distance)
				return t.river;
		}
		return null;
	}
	
	/**
	 * Return the distance to the next river.
	 * @param distance the distance already traveled
	 * @return the distance to the next town
	 */
	public int distanceToRiver(int distance) {
		for(RiverPair t : rivers) {
			if(t.distance > distance)
				return t.distance - distance;
		}
		return 0;
	}
	
	/**
	 * Class for holding a pairing of a town and a distance.
	 */
	private class TownPair {
		public int distance;
		public Town town;
		/**
		 * Create a new TownPair
		 * @param town
		 * @param distance
		 */
		public TownPair(Town town, int distance) {
			this.distance = distance;
			this.town = town;
		}
	}
	
	/**
	 * Class for holding a pairing of a river and a distance.
	 */
	private class RiverPair {
		public int distance;
		public River river;
		/**
		 * Create a new RiverPair
		 * @param river
		 * @param distance
		 */
		public RiverPair(River river, int distance) {
			this.distance = distance;
			this.river = river;
		}
	}
	
}
