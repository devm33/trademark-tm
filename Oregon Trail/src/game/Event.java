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
	private Random rand;
	private Wagon eventWagon;
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
			l+=2;
			if (eventWagon.getRations()<=1){
				r+=10;
				l+=2;
			}
		}
		if(l>4 && r<15 && b){
			if(!eventWagon.getPassengers().get(ep).getStatus().equals("DEAD")){
				//do nothing if person is already dead.
			}
			else{
				//System.out.println("heal people");
				for (Person h: eventWagon.getPassengers()){
					if (h.getSickness() != null){
						this.healMessage(h.getName());
					}
					h.setHealed();
				}
			}
		}
		if(r>=90 && l>3 && b){
			//System.out.println("disease");
			
			if(eventWagon.getPassengers().get(ep).getStatus().equals("SICK")||eventWagon.getPassengers().get(ep).getStatus().equals("DEAD")||eventWagon.getPassengers().get(ep).getStatus().equals("POISONED")){
					//do nothing if person is already sick, dead, or poisoned.
			}
			else{
				if(s==0){
					//System.out.println("dysentery");
					eventWagon.getPassengers().get(ep).setSickness("disease", "dysentery");
					this.diseaseMessage("dysentery", eventWagon.getPassengers().get(ep).getName());
				}
				if(s==1){
					//System.out.println("typhoid");
					eventWagon.getPassengers().get(ep).setSickness("disease", "typhoid");
					this.diseaseMessage("typhoid", eventWagon.getPassengers().get(ep).getName());
				}
				if(s==2){
					//System.out.println("scarlet fever");
					eventWagon.getPassengers().get(ep).setSickness("disease", "scarlet fever");
					this.diseaseMessage("scarlet fever", eventWagon.getPassengers().get(ep).getName());
				}
				if(s==3){
					//System.out.println("measels");
					eventWagon.getPassengers().get(ep).setSickness("disease", "measels");
					this.diseaseMessage("measels", eventWagon.getPassengers().get(ep).getName());
				}
				if(s==4){
					//System.out.println("scurvy");
					eventWagon.getPassengers().get(ep).setSickness("disease", "scurvy");
					this.diseaseMessage("scurvy", eventWagon.getPassengers().get(ep).getName());
				}
			}
		}else if(r==73 && l>4 && b){
			//System.out.println("snakebite");
			if(eventWagon.getPassengers().get(ep).getStatus().equals("SICK")||eventWagon.getPassengers().get(ep).getStatus().equals("DEAD")||eventWagon.getPassengers().get(ep).getStatus().equals("POISONED")){
				//do nothing if person is already sick, dead, or poisoned.
			}
			else{
				eventWagon.getPassengers().get(ep).setSickness("poison", "venom");
				this.snakebiteMessage(eventWagon.getPassengers().get(ep).getName());
			}
		}else if(r==72 && l>4 && b){
			//System.out.println("poison");
			if(eventWagon.getPassengers().get(ep).getStatus().equals("SICK")||eventWagon.getPassengers().get(ep).getStatus().equals("DEAD")||eventWagon.getPassengers().get(ep).getStatus().equals("POISONED")){
				//do nothing if person is already sick, dead, or poisoned.
			}
			else{
				this.poisonMessage(eventWagon.getPassengers().get(ep).getName());
				eventWagon.getPassengers().get(ep).setSickness("poison", "poison");
			}
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
			
			try {
				eventWagon.getLeader().addMoney(newCash);
				this.treasureMessage((s+1)*10);
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
			JOptionPane.showMessageDialog(null,str); //the lack of a parent component is what causes problems here (on mac at least)
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
	 * @param n the name of the person afflicted
	 */
	public void diseaseMessage(String s, String n){
		//JOptionPane.showMessageDialog(null, "(Traveler/Leader) has caught "+s);
		displayMessage(n+" has caught "+s);
	}
	/**
	 * this message is triggered by the snakebite event
	 * @param n the name of the person bitten
	 */
	public void snakebiteMessage(String n){
		//JOptionPane.showMessageDialog(null, "(Traveler/Leader) was bitten by a snake and is sick from the venom.");
		displayMessage(n+" was bitten by a snake and is sick from the venom.");
	}
	/**
	 * this message is triggered by the poisoned event
	 * @param n the name of the person poisoned
	 */
	public void poisonMessage(String n){
		//JOptionPane.showMessageDialog(null, "(Traveler/Leader) was bitten by a snake and is sick from the venom.");
		displayMessage(n+" was poisoned, possibly from your cooking.");
	}
	/**
	 * this message is triggered by healing
	 * @param n the name of the person healed
	 */
	public void healMessage(String n){
		//JOptionPane.showMessageDialog(null, "(Traveler/Leader) was bitten by a snake and is sick from the venom.");
		displayMessage(n+" has healed miraculously.");
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
