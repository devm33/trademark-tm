package testing;

import static org.junit.Assert.*;

import game.Map;
import game.Wagon;
import game.World;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import people.*;

/**
 * JUnit Testing Code
 * 
 *  //Everyone Needs to submit one test
 * @author Devaj Mehta, 
 * 
 */
public class TestOregonTrail {
	
	/**
	 * objects used in testing
	 */
	private static Wagon wagon;
	private static List<Traveler> members;
	private static Leader leader;
	private static Map map;
	
	
	/**
	 * sets up objects for testing
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		leader = new Banker("Leader Person");
		members = new ArrayList<Traveler>();
		members.add(new Traveler("Member One"));
		members.add(new Traveler("Member Two"));
		members.add(new Traveler("Member Three"));
		members.add(new Traveler("Member Four"));
		wagon = new Wagon();
		wagon.setLeader(leader);
		wagon.setMembers(members);
		map = new Map();
	}

	//test the values of the wagon at start
	@Test
	public void testInitialWagon() {
		List<Person> testPassengers = new ArrayList<Person>();
		testPassengers.add(leader);
		testPassengers.addAll(members);
		assertTrue("Passengers incorrectly initialized.", testPassengers.equals(wagon.getPassengers()));
		assertTrue("Wagon did not start at zero distance.", wagon.getDistance() == 0);
		assertTrue("Wagon does not have leader's money.", Integer.parseInt(wagon.getCash()) == leader.getMoney());
		assertFalse("People start dead.", wagon.getTotalDeath());
	}
	
	
	/**
	 * This test checks that each passenger's values were initialized correctly.
	 * 
	 * @author Robert Heck
	 */
	@Test
	public void testPassengerStatus()
	{
		List<Person> testPassengers = new ArrayList<Person>();
		testPassengers.add(leader);
		testPassengers.addAll(members);
		assertTrue("Leader has incorrect money.", leader.getMoney() == 1600);
		for(int x=0; x<testPassengers.size(); x++)
		{
			assertTrue("Members have incorrect health.", testPassengers.get(x).getHealth() == 100);
			assertTrue("Members have incorrect hunger.", testPassengers.get(x).getHunger() == 0);
			assertTrue("Members have incorrect thirst.", testPassengers.get(x).getThirst() == 0);
		}
	}
	
	/**
	 * Testing for correct restart of game
	 * located in World.restartGame()
	 * @author Jaron Gao
	 */
	@Test
	public void testRestartGame()
	{
		World.initializeGame();
		World.restartGame();
		assertTrue("Game restarts to Day 1", World.getDays() == 1);
		assertTrue("Game restarts to Distance 0", World.getWagon().getDistance() == 0);
		assertTrue("Game restarts to Weight 0", World.getWagon().getTotalWeight() == 0);
		assertTrue("Game restarts to blank Inventory", World.getWagon().getInventory().isBlank() == true);
		assertTrue("Game restarts to Independence", World.getCurrentTown().getTownName().equals("Independence"));
	}
	
	/**
	 * This test checks traveller status and health when poisoned and sick. It also tests passenger death.
	 * 
	 *  @author David Byas-Smith
	 */
	@Test
	public void testTravellerSickness()
	{
		Traveler testPassenger = new Traveler("Test");
		testPassenger.setSickness("poison", "bleach");
		assertTrue("Passenger is not poisoned.", testPassenger.getStatus().equals("POISONED"));
		testPassenger.setHealed();
		assertTrue("Passenger is still poisoned.", testPassenger.getStatus().equals("HEALTHY"));
		testPassenger.setSickness("disease", "chicken pox");
		assertTrue("Passenger is not sick.", testPassenger.getStatus().equals("SICK"));
		int testPassHealth = testPassenger.getHealth();
		testPassenger.live();
		assertTrue("Passenger is not dying.", testPassenger.getHealth()==(testPassHealth-15));
		testPassenger.setHealed();
		assertTrue("Passenger is still sick.", testPassenger.getStatus().equals("HEALTHY"));
		testPassenger.die();
		assertTrue("Passender is still alive.", testPassenger.getStatus().equals("DEAD"));
		assertTrue("Passender is still alive.", testPassenger.getHealth()==0);
	}
	
	/*
	//Implement tests like so
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	*/

}
