package game;

import java.awt.Component;
import java.util.Random;

import javax.swing.*;


import exceptions.InsufficientFundsException;

import people.Leader;
import people.Person;


public class Event {
	Random rand;
	Wagon eventWagon;
	
	public Event(Wagon w){
		this.eventWagon = w;
		this.rand = new Random();
	}
	
	public void eventCall(){
		int r = rand.nextInt(100);
		int s = rand.nextInt(5);
		
		if(eventWagon.getPace()> 10 && eventWagon.getRations()<=2 ){
			r+=20;
			if (eventWagon.getRations()<=1){
				r+=10;
			}
		}
		
		
		
		
		if(r>=90){
			System.out.println("disease");
			if(s==0){
				System.out.println("dysentery");
			}
			if(s==1){
				System.out.println("typhoid");
			}
			if(s==2){
				System.out.println("scarlet fever");
			}
			if(s==3){
				System.out.println("measels");
			}
			if(s==4){
				System.out.println("scurvy");
			}
		}else if(r<74&&r>72){
			System.out.println("snakebite");
		}else if(r<=69&&r>=66){
			System.out.println("theft");
			try {
				eventWagon.getLeader().setMoney(eventWagon.getLeader().getMoney()-50);
			} catch (InsufficientFundsException e) {
				System.out.println("avoided theft due to lack of valuables");
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}else if(r<60&&r>57){
			System.out.println("lightning strike");
			for (Person p: eventWagon.getPassengers()){
				p.die();
			}
			this.lightningMessage();
				//output to popup
				//eventWagon.setNotification("Your wagon was struck by a random lighting bolt...none survived.");
			eventWagon.setTotalDeath();
			
		}
		
		
		
		if(r<5){
			System.out.println("treasure");
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
}
