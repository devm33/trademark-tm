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
			int newCash;
			if(s==0){
				newCash = 10;
			}
			if(s==1){
				newCash = 20;
			}
			if(s==2){
				newCash = 30;
			}
			if(s==3){
				newCash = 40;
			}
			else{
				newCash = 50;
			}
			this.treasureMessage((s+1)*10);
			try {
				eventWagon.getLeader().addMoney(newCash);
			} catch (InsufficientFundsException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}
	public void lightningMessage(){
		JOptionPane.showMessageDialog(null, "Your wagon was struck by a random lighting bolt...none survived.");
	}
	public void diseaseMessage(String s){
		JOptionPane.showMessageDialog(null, "(Traveler/Leader) has caught "+s);
	}
	public void snakebiteMessage(){
		JOptionPane.showMessageDialog(null, "(Traveler/Leader) was bitten by a snake and is sick from the venom.");
	}
	public void theftMessage(int d){
		JOptionPane.showMessageDialog(null, "You were robbed of $"+d+".00");
	}
	public void treasureMessage(int d){
		JOptionPane.showMessageDialog(null, "You were robbed of $"+d+".00");
	}
}
