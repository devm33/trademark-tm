package testing;

import static org.junit.Assert.*;

import game.Map;
import game.Store;
import game.Wagon;
import game.World;


import items.Ammo;
import items.Axle;
import items.Clothing;
import items.Food;
import items.Medicine;
import items.Oxen;
import items.Tongue;
import items.Water;
import items.Wheel;

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
	Store R0Store;
	Store R1Store;
	Store R2Store;
	Store R3Store;
	
	
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
		R0Store = new Store("testing 0", 500);
		R1Store = new Store("testing 1", 800);
		R2Store = new Store("testing 2", 1300);
		R3Store = new Store("testing 3", 1700);
	}

	/**
	 * This tests the wagon's initialization
	 * 
	 * @author Dev
	 */
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
	 * This tests that the store's available inventory is properly initialized based on distance from start.
	 * 
	 * @author Stephen Bentley
	 */
	@Test
	public void testStoreInitialization(){
		//region 0 with everything available
		assertTrue("Store0 Inventory missing Food", R0Store.getInventory().contains(new Food()));
		assertTrue("Store0 Inventory missing Ammo", R0Store.getInventory().contains(new Ammo()));
		assertTrue("Store0 Inventory missing Water", R0Store.getInventory().contains(new Water()));
		assertTrue("Store0 Inventory missing Medicine", R0Store.getInventory().contains(new Medicine()));
		assertTrue("Store0 Inventory missing Axle", R0Store.getInventory().contains(new Axle()));
		assertTrue("Store0 Inventory missing Clothing", R0Store.getInventory().contains(new Clothing()));
		assertTrue("Store0 Inventory missing Oxen", R0Store.getInventory().contains(new Oxen()));
		assertTrue("Store0 Inventory missing Tongue", R0Store.getInventory().contains(new Tongue()));
		assertTrue("Store0 Inventory missing Wheel", R0Store.getInventory().contains(new Wheel()));
		//region 1 with most things available
		assertTrue("Store1 Inventory missing Food", R1Store.getInventory().contains(new Food()));
		assertTrue("Store1 Inventory missing Ammo", R1Store.getInventory().contains(new Ammo()));
		assertTrue("Store1 Inventory missing Water", R1Store.getInventory().contains(new Water()));
		assertTrue("Store1 Inventory missing Medicine", R1Store.getInventory().contains(new Medicine()));
		assertTrue("Store1 Inventory missing Axle", R1Store.getInventory().contains(new Axle()));
		assertFalse("Store1 Inventory not missing Clothing", R1Store.getInventory().contains(new Clothing()));
		assertTrue("Store1 Inventory missing Oxen", R1Store.getInventory().contains(new Oxen()));
		assertTrue("Store1 Inventory missing Tongue", R1Store.getInventory().contains(new Tongue()));
		assertFalse("Store1 Inventory not missing Wheel", R1Store.getInventory().contains(new Wheel()));
		//region 2 with less available
		assertTrue("Store2 Inventory missing Food", R2Store.getInventory().contains(new Food()));
		assertTrue("Store2 Inventory missing Ammo", R2Store.getInventory().contains(new Ammo()));
		assertFalse("Store2 Inventory not missing Water", R2Store.getInventory().contains(new Water()));
		assertFalse("Store2 Inventory not missing Medicine", R2Store.getInventory().contains(new Medicine()));
		assertTrue("Store2 Inventory missing Axle", R2Store.getInventory().contains(new Axle()));
		assertTrue("Store2 Inventory missing Clothing", R2Store.getInventory().contains(new Clothing()));
		assertFalse("Store2 Inventory not missing Oxen", R2Store.getInventory().contains(new Oxen()));
		assertFalse("Store2 Inventory not missing Tongue", R2Store.getInventory().contains(new Tongue()));
		assertTrue("Store2 Inventory missing Wheel", R2Store.getInventory().contains(new Wheel()));
		//region 3 with almost nothing available
		assertTrue("Store3 Inventory missing Food", R3Store.getInventory().contains(new Food()));
		assertFalse("Store3 Inventory not missing Ammo", R3Store.getInventory().contains(new Ammo()));
		assertFalse("Store3 Inventory not missing Water", R3Store.getInventory().contains(new Water()));
		assertTrue("Store3 Inventory missing Medicine", R3Store.getInventory().contains(new Medicine()));
		assertFalse("Store3 Inventory not missing Axle", R3Store.getInventory().contains(new Axle()));
		assertFalse("Store3 Inventory not missing Clothing", R3Store.getInventory().contains(new Clothing()));
		assertTrue("Store3 Inventory missing Oxen", R3Store.getInventory().contains(new Oxen()));
		assertFalse("Store3 Inventory not missing Tongue", R3Store.getInventory().contains(new Tongue()));
		assertFalse("Store3 Inventory not missing Wheel", R3Store.getInventory().contains(new Wheel()));
		
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
