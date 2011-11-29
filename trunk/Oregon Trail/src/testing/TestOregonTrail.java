package testing;

import static org.junit.Assert.*;

import game.Map;
import game.Wagon;

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
	
	/*
	//Implement tests like so
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	*/

}
