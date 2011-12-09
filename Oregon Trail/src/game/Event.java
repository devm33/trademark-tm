package game;

import java.util.Random;
import exceptions.InsufficientFundsException;
import people.Person;

/**
 * This class houses the random events that happen anywhere/everywhere in game
 * @author Stephen Bentley
 *
 */
public class Event {
	private Random rand;
	/**
	 * Constructor for events
	 */
	public Event(Wagon w){
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
		boolean farmLeader = World.getWagon().getLeader().getType().equals("farm");
		boolean carpLeader = World.getWagon().getLeader().getType().equals("carp");
		boolean bankLeader = World.getWagon().getLeader().getType().equals("bank");
		boolean assist = (World.getWagon().getInventory().getFood().getNumber()<50 && Integer.parseInt(World.getWagon().getCash())<100);
		displayMessage("Nothing interesting happened.");
		int ep = rand.nextInt(5);//for use determining who gets sick/bitten
		
		//updates message
		World.getMainScreen().stepGame();
		
		if(World.getWagon().getPace()> 10 && World.getWagon().getRations()<=3 && !farmLeader){
			r+=5;
			l+=2;
			if (World.getWagon().getRations()<=2){
				r+=10;
				l+=2;
				if (World.getWagon().getRations()<=1){
					r+=20;
					l+=1;
				}
			}
			
		}
		if((l>4 && r<15 && b)||(farmLeader && b && l<7)){
			if(!World.getWagon().getPassengers().get(ep).getStatus().equals("DEAD")||World.getWagon().getPassengers().get(ep).getHealth()<=0){
				//do nothing if person is already dead.
			}
			else{
				//System.out.println("heal people");
				for (Person h: World.getWagon().getPassengers()){
					if (h.getSickness() != null){
						this.healMessage(h.getName());
					}
					if (h.getPoisonType() != null){
						this.healMessage(h.getName());
					}
					h.setHealed();
					h.addHealth(10);
				}
			}
		}else if(r>=85 && l>2 && b){
			//System.out.println("disease");
			
			if(World.getWagon().getPassengers().get(ep).getHealth()<=0||World.getWagon().getPassengers().get(ep).getStatus().equals("SICK")||World.getWagon().getPassengers().get(ep).getStatus().equals("DEAD")||World.getWagon().getPassengers().get(ep).getStatus().equals("POISONED")){
					//do nothing if person is already sick, dead, or poisoned.
			}
			else{
				if(s==0){
					//System.out.println("dysentery");
					World.getWagon().getPassengers().get(ep).setSickness("disease", "dysentery");
					this.diseaseMessage("dysentery", World.getWagon().getPassengers().get(ep).getName());
				}
				if(s==1){
					//System.out.println("typhoid");
					World.getWagon().getPassengers().get(ep).setSickness("disease", "typhoid");
					this.diseaseMessage("typhoid", World.getWagon().getPassengers().get(ep).getName());
				}
				if(s==2){
					//System.out.println("scarlet fever");
					World.getWagon().getPassengers().get(ep).setSickness("disease", "scarlet fever");
					this.diseaseMessage("scarlet fever", World.getWagon().getPassengers().get(ep).getName());
				}
				if(s==3){
					//System.out.println("measels");
					World.getWagon().getPassengers().get(ep).setSickness("disease", "measels");
					this.diseaseMessage("measels", World.getWagon().getPassengers().get(ep).getName());
				}
				if(s==4){
					//System.out.println("scurvy");
					World.getWagon().getPassengers().get(ep).setSickness("disease", "scurvy");
					this.diseaseMessage("scurvy", World.getWagon().getPassengers().get(ep).getName());
				}
			}
		}else if(r==73 && l>4 && b){
			//System.out.println("snakebite");
			if(World.getWagon().getPassengers().get(ep).getStatus().equals("SICK")||World.getWagon().getPassengers().get(ep).getStatus().equals("DEAD")||World.getWagon().getPassengers().get(ep).getStatus().equals("POISONED")){
				//do nothing if person is already sick, dead, or poisoned.
			}
			else{
				World.getWagon().getPassengers().get(ep).setSickness("poison", "venom");
				this.snakebiteMessage(World.getWagon().getPassengers().get(ep).getName());
			}
		}else if(r==72 && l>4 && b){
			//System.out.println("poison");
			if(World.getWagon().getPassengers().get(ep).getStatus().equals("SICK")||World.getWagon().getPassengers().get(ep).getStatus().equals("DEAD")||World.getWagon().getPassengers().get(ep).getStatus().equals("POISONED")){
				//do nothing if person is already sick, dead, or poisoned.
			}
			else{
				this.poisonMessage(World.getWagon().getPassengers().get(ep).getName());
				World.getWagon().getPassengers().get(ep).setSickness("poison", "poison");
			}
		}else if((r==68 && l>7 && b)||(bankLeader && r==68 && b)){
			//System.out.println("theft");
			try {
				World.getWagon().getLeader().setMoney(World.getWagon().getLeader().getMoney()-((s+1)*10));
				this.theftMessage(((s+1)*10));
			} catch (InsufficientFundsException e) {
				System.out.println("avoided theft due to lack of valuables");
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}else if(r<60 && r>59 && l>7 && b){
			//System.out.println("lightning strike");
			this.lightningMessage();
			
			for (Person p: World.getWagon().getPassengers()){
				p.die();
			}
				//output to popup
				//World.getWagon().setNotification("Your wagon was struck by a random lighting bolt...none survived.");
			//World.getWagon().setTotalDeath();	
				//call to be changed if lose conditions are added to losescreen
		}else if(r<40 &&r>39 && !carpLeader && World.getWagon().getPace()>10){
			if(s<2){
				World.getWagon().setIsWheelBroken(true);
				this.brokenWheelMessage();
			}else if(s==2 || s==3){
				World.getWagon().setIsAxleBroken(true);
				this.brokenAxleMessage();
			}else if(s==4){
				World.getWagon().setIsTongueBroken(true);
				this.brokenTongueMessage();
			}
		}else if(r<30 && r>6 && farmLeader && World.getWagon().getInventory().getFood().getNumber()<100){
			//farmer found food
			World.getWagon().getInventory().getFood().setNumber(World.getWagon().getInventory().getFood().getNumber()+50);
			this.farmFoodMessage();
		}else if(r<30 && r>25 && !farmLeader && World.getWagon().getPace()>10 && b){
			//overworked oxen
			World.getWagon().getInventory().getOxen().setNumber(World.getWagon().getInventory().getOxen().getNumber()-1);
			this.deadOxenMessage();
		}else if(((r<5 && b)|| (assist && b)) && !bankLeader){
			//System.out.println("treasure");
			int newCash = (s+1)*10;
			
			try {
				World.getWagon().getLeader().addMoney(newCash);
				this.treasureMessage((s+1)*10);
			} catch (InsufficientFundsException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}

	/**
	 * for use with the event status messages
	 * @param str the message to pass to the fieldscreen display
	 */
	public void displayMessage(String str) {
		World.getMainScreen().displayOnField(str);
		//System.out.println("this should be on field screen: "+str);
		/*
		if(System.getProperty("os.name").equals("Mac OS X"))
			System.out.println(str);
		else
			JOptionPane.showMessageDialog(null,str); //the lack of a parent component is what causes problems here (on mac at least)
		*/
	}
	
	/**
	 * Displays cause of loss on lose screen.
	 * @param str
	 */
	public void displayLoss(String str){
		World.getMainScreen().displayOnLose(str);
	}
	
	
	/**
	 * this message is triggered by a lightning strike
	 */
	public void lightningMessage() {
		//JOptionPane.showMessageDialog(null, "Your wagon was struck by a random lighting bolt...none survived.");
		displayLoss("Your wagon was struck by a random lighting bolt...none survived.");
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
	/**
	 * this message is triggered by the farmer finding food
	 */
	public void farmFoodMessage(){
		displayMessage(World.getWagon().getLeader().getName()+" found some food along the trail.");
	}
	/**
	 * this message is triggered by an ox dying of exhaustion
	 */
	public void deadOxenMessage(){
		displayMessage("An ox has died from exhaustion!");
	}
	/**
	 * this message is triggered by a broken wheel event
	 */
	public void brokenWheelMessage(){
		displayMessage("A wheel has broken!");
	}
	/**
	 * this message is triggered by a broken axle
	 */
	public void brokenAxleMessage(){
		displayMessage("An axle has broken!");
	}
	/**
	 * this message is triggered by a broken oxen tongue
	 */
	public void brokenTongueMessage(){
		displayMessage("An oxen Tongue has broken!");
	}
}
