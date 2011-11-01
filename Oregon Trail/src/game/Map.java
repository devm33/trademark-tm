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
	
	/**
	 * Create the map object.
	 */
	public Map() {
		towns = new ArrayList<TownPair>();
		Scanner scan = null;
		
		//Read in the town list
		try {
			scan = new Scanner(new File("src/game/townlist.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		String line = "";
		int index;
		while(scan.hasNext()) {
			line = scan.nextLine();
			index = line.indexOf(',');
			String name = line.substring(0, index);
			line = line.substring(index+1);
			index = line.indexOf(',');
			int distance = Integer.parseInt(line.substring(0, index));
			line = line.substring(index+1);
			Town temp;
			if(line.equals("Yes"))
				temp = new Town(name);
			else
				temp = new Town(name, null);
			towns.add(new TownPair(temp,distance));
		}
	}
	
	/**
	 * Return the next town to this distance
	 * @param distance
	 * @return the next closest town
	 */
	public Town getNextTown(int distance) {
		int sum = 0;
		for(TownPair t : towns) {
			sum += t.distance;
			if(sum > distance)
				return t.town;
		}
		return null;
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
	
}
