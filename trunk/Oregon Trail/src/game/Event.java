package game;

import java.util.Random;
import javax.swing.*;
import exceptions.InsufficientFundsException;
import people.Leader;
import people.Person;

/**
 * This class houses the random events that happen anywhere/everywhere in game
 * @author Stephen Bentley
 *
 */
public class Event {
	Random rand;
	Wagon eventWagon;
	/**
	 * Constructor for events
	 * @param w the wagon that the events will affect
	 */
	public Event(Wagon w){
		this.eventWagon = w;
		this.rand = new Random();
	}
	/**
	 * The massive event call that should be run every turn to determine if random event happens, and if so, which one.
	 */
	public void eventCall(){
		int r = rand.nextInt(100);
		int l = rand.nextInt(10);
		int s = rand.nextInt(5);
		boolean b = rand.nextBoolean();
		
		int ep = rand.nextInt(5);//for use determining who gets sick/bitten
		
		if(eventWagon.getPace()> 10 && eventWagon.getRations()<=2 ){
			r+=20;
			if (eventWagon.getRations()<=1){
				r+=10;
			}
		}
		if(l>4 && r<15 && b){
			System.out.println("heal people");
		}
		if(r>=90 && l<7 && b){
			System.out.println("disease");
			if(s==0){
				//System.out.println("dysentery");
				this.diseaseMessage("dysentery");
			}
			if(s==1){
				//System.out.println("typhoid");
				this.diseaseMessage("typhoid");
			}
			if(s==2){
				//System.out.println("scarlet fever");
				this.diseaseMessage("scarlet fever");
			}
			if(s==3){
				//System.out.println("measels");
				this.diseaseMessage("measels");
			}
			if(s==4){
				//System.out.println("scurvy");
				this.diseaseMessage("scurvy");
			}
		}else if(r<74 && r>72 && b){
			//System.out.println("snakebite");
			this.snakebiteMessage();
		}else if(r==68 && l>7 && b){
			//System.out.println("theft");
			try {
				eventWagon.getLeader().setMoney(eventWagon.getLeader().getMoney()-((s+1)*10));
				this.theftMessage(((s+1)*10));
			} catch (InsufficientFundsException e) {
				System.out.println("avoided theft due to lack of valuables");
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}else if(r<60 && r>58 && l<3 && b){
			System.out.println("lightning strike");
			for (Person p: eventWagon.getPassengers()){
				p.die();
			}
			this.lightningMessage();
				//output to popup
				//eventWagon.setNotification("Your wagon was struck by a random lighting bolt...none survived.");
			eventWagon.setTotalDeath();	
		}
		if(r<6 && b){
			//System.out.println("treasure");
			int newCash = (s+1)*10;
			this.treasureMessage((s+1)*10);
			try {
				eventWagon.getLeader().addMoney(newCash);
			} catch (InsufficientFundsException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}
	/**
	 * for use with the event status messages
	 * @param str the message to display
	 */
	public void displayMessage(String str) {
		if(System.getProperty("os.name").equals("Mac OS X"))
			System.out.println(str);
		else
			JOptionPane.showMessageDialog(null,str);
	}
	/**
	 * this message is triggered by a lightning strike
	 */
	public void lightningMessage() {
		//JOptionPane.showMessageDialog(null, "Your wagon was struck by a random lighting bolt...none survived.");
		displayMessage("Your wagon was struck by a random lighting bolt...none survived.");
	}
	/**
	 * this message is triggered by the disease event
	 * @param s the disease name
	 */
	public void diseaseMessage(String s){
		//JOptionPane.showMessageDialog(null, "(Traveler/Leader) has caught "+s);
		displayMessage("(Traveler/Leader) has caught "+s);
	}
	/**
	 * this message is triggered by the snakebite event
	 */
	public void snakebiteMessage(){
		//JOptionPane.showMessageDialog(null, "(Traveler/Leader) was bitten by a snake and is sick from the venom.");
		displayMessage("(Traveler/Leader) was bitten by a snake and is sick from the venom.");
	}
	/**
	 * this message is triggered by the theft event
	 * @param d the int number of dollars stolen
	 */
	public void theftMessage(int d){
		//JOptionPane.showMessageDialog(null, "You were robbed of $"+d+".00");
		displayMessage("You were robbed of $"+d+".00");
	}
	/**
	 * this message is triggered by the finding of treasure
	 * @param d the int number of dollars found
	 */
	public void treasureMessage(int d){
		//JOptionPane.showMessageDialog(null, "You were robbed of $"+d+".00");
		displayMessage("You found $"+d+".00 on the ground.");
	}
}
