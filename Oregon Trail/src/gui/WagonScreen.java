package gui;

import java.util.List;

import game.Wagon;
import game.World;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import people.Person;
/**
 * Screen to display the wagon contents and controls.
 * 
 * 
 * @author Devraj Mehta
 *
 */
public class WagonScreen extends Composite {
	private Label rationsDescript;
	private Label paceDescript;
	private Label food;
	private Label distance;
	private Label leader;
	private Label traveler0;
	private Label traveler1;
	private Label traveler2;
	private Label traveler3;
	private Button btnTakeTurn;
	private Combo rations;
	private Combo pace;
	private Label lblWagon;
	
	private static Wagon wagon;

	public WagonScreen(Composite arg0, int arg1) {
		super(arg0, arg1);
		
		wagon = World.getWagon();
		
		btnTakeTurn = new Button(this, SWT.NONE);
		btnTakeTurn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {	
				wagon.takeStep();
				update();
			}
		});
		btnTakeTurn.setBounds(20, 37, 94, 28);
		btnTakeTurn.setText("Take Turn");
		
		rations = new Combo(this, SWT.NONE);
		rations.setItems(new String[] {"None", "Bare-Bones", "Meager", "Normal", "Wellfed"});
		rations.setBounds(68, 83, 94, 22);
		rations.setText("Normal");
		rations.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int cur_rations = rations(rations.getText());
				if(cur_rations < 0) {
					System.out.println("wtf happened? \n crashing from confusion.");
					System.exit(1);
				}
				if(cur_rations != wagon.getRations()) {
					wagon.setRations(cur_rations);
					rationsDescript.setText(rationsDescript(wagon.getRations()));
				}
			}
		});
		
		pace = new Combo(this, SWT.NONE);
		pace.setItems(new String[] {"Stopped", "Leisurely", "Steady", "Grueling"});
		pace.setBounds(68, 131, 94, 22);
		pace.setText("Steady");
		pace.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int cur_pace = pace(pace.getText());
				if(cur_pace < 0) {
					System.out.println("wtf happened? \n crashing from confusion.");
					System.exit(1);
				}
				if(cur_pace != wagon.getPace()) {
					wagon.setPace(cur_pace);
					paceDescript.setText(paceDescript(wagon.getPace()));
				}
			}
		});
		
		rationsDescript = new Label(this, SWT.NONE);
		rationsDescript.setBounds(174, 83, 266, 42);
		
		paceDescript = new Label(this, SWT.NONE);
		paceDescript.setBounds(174, 132, 266, 42);
		
		food = new Label(this, SWT.NONE);
		food.setBounds(247, 16, 75, 16);
		
		distance = new Label(this, SWT.NONE);
		distance.setBounds(278, 45, 120, 16);
		distance.setText("0");
		
		leader = new Label(this, SWT.NONE);
		leader.setBounds(0, 180, 450, 19);
		
		traveler0 = new Label(this, SWT.NONE);
		traveler0.setBounds(0, 205, 450, 19);
		
		traveler1 = new Label(this, SWT.NONE);
		traveler1.setBounds(0, 230, 450, 19);
		
		traveler2 = new Label(this, SWT.NONE);
		traveler2.setBounds(0, 255, 450, 19);
		
		traveler3 = new Label(this, SWT.NONE);
		traveler3.setBounds(0, 281, 450, 19);
		
		lblWagon = new Label(this, SWT.NONE);
		lblWagon.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		lblWagon.setBounds(10, 5, 75, 28);
		lblWagon.setText("Wagon"); //This is myWagon. There are many like it, but this one is mine.
		
		Label lblFoodRemaining = new Label(this, SWT.NONE);
		lblFoodRemaining.setBounds(143, 16, 108, 13);
		lblFoodRemaining.setText("Food Remaining:");
		
		Label lblDistanceRemaining = new Label(this, SWT.NONE);
		lblDistanceRemaining.setBounds(143, 45, 120, 13);
		lblDistanceRemaining.setText("Distance Remaining:");
		
		Label lblRatiions = new Label(this, SWT.NONE);
		lblRatiions.setBounds(13, 91, 49, 13);
		lblRatiions.setText("Rations:");
		
		Label lblPace = new Label(this, SWT.NONE);
		lblPace.setBounds(20, 134, 49, 13);
		lblPace.setText("Pace:");
		
	}
	
	public void update() {
		
		rations.setText(rations(wagon.getRations()));
		
		pace.setText(pace(wagon.getPace()));
		
		rationsDescript.setText(rationsDescript(wagon.getRations()));
		
		paceDescript.setText(paceDescript(wagon.getPace()));
		
		food.setText("" + wagon.getInventory().getFood().getNumber());
		
		distance.setText("" + wagon.getDistance());
		
		List<Person> pass = wagon.getPassengers();
		Label[] labels = {leader, traveler0, traveler1, traveler2, traveler3};
		int i = 0;
		for(Person p : pass) {
			labels[i].setText(p.toString());
			i++;
		}
	}
	/**
	 * Returns a string naming the given rations setting
	 * @param r the rations setting
	 * @return the string naming the rations setting or null if r not valid
	 */
	private String rations(int r) {
		switch(r) {
		case 0:
			return "None";
		case 1:
			return "Bare-Bones";
		case 2:
			return "Meager";
		case 3:
			return "Normal";
		case 4:
			return "Wellfed";
		}
		return null;
	}
	/**
	 * Returns the integer associated with the given string
	 * @param r the string representing rations
	 * @return the integer for rations or -1 if r not valid
	 */
	private int rations(String r) {
		if(r.equals("None"))
			return 0;
		else if(r.equals("Bare-Bones"))
			return 1;
		else if(r.equals("Meager"))
			return 2;
		else if(r.equals("Normal"))
			return 3;
		else if(r.equals("Wellfed"))
			return 4;
		return -1;
	}
	/**
	 * Returns a description of the given rations setting
	 * @param r the rations setting
	 * @return the string description or null if r not valid
	 */
	private String rationsDescript(int r) {
		switch(r) {
		case 0:
			return "You gonna die.";
		case 1:
			return "1 pound of food per person per day.";
		case 2:
			return "2 pounds of food per person per day.";
		case 3:
			return "3 pounds of food per person per day.";
		case 4:
			return "4 pounds of food per person per day.";
		}
		return null;
	}
	/**
	 * Returns the string associated with the integer for pace
	 * @param p the integer for pace
	 * @return the string pace or null if p not valid
	 */
	private String pace(int p) {
		switch(p) {
		case 0:
			return "Stopped";
		case 5:
			return "Leisurely";
		case 10:
			return "Steady";
		case 15:
			return "Grueling";
		}
		return null;
	}
	/**
	 * Returns the integer associated with the string pace
	 * @param p the pace in string form
	 * @return integer with the pace or -1 if not valid
	 */
	private int pace(String p) {
		if(p.equals("Stopped"))
			return 0;
		else if(p.equals("Leisurely"))
			return 5;
		else if(p.equals("Steady"))
			return 10;
		else if(p.equals("Grueling"))
			return 15;
		return -1;
	}
	/**
	 * Returns a string describing the current pace selection.
	 * @param p the pace to describe.
	 * @return description or null if invalid pace.
	 */
	private String paceDescript(int p) {
		switch(p) {
		case 0:
			return "0 miles pers day restful.\n Recover from exhaustion faster.";
		case 5:
			return "5 miles per day slow and restful.\n Helps recover from exhaustion.";
		case 10:
			return "10 miles per day basic pace.\n Normal fatigue.";
		case 15:
			return "15 miles per day hard pace.\n Oxen and people rapidly become tired, then exhausted.";
		}
		return null;
	}
}
