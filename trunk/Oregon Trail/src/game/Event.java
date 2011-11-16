package game;

import java.util.Random;

import exceptions.InsufficientFundsException;

import people.Leader;


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
			r+=30;
			if (eventWagon.getRations()<=1){
				r+=30;
			}
		}
		
		
		
		
		if(r>=74){
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
		}else if(r<74&&r>70){
			System.out.println("snakebite");
		}else if(r<=69&&r>=60){
			System.out.println("theft");
			try {
				eventWagon.getLeader().setMoney(eventWagon.getLeader().getMoney()-50);
			} catch (InsufficientFundsException e) {
				System.out.println("avoided theft due to lack of valuables");
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}else if(r<60&&r>55){
			System.out.println("lightning strike");
			
		}
		
		
		
		if(r<25){
			System.out.println("treasure");
		}
		
		
		
	}
}
