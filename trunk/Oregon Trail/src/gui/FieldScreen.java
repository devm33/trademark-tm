package gui;

import game.Wagon;
import game.World;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * The screen where the party travels outside of towns
 * and encounters events.
 * 
 * May initialize hunting or river crossing mini games.
 * @author Jaron
 *
 */
public class FieldScreen extends Composite {
	private Combo dropRations;
	private Combo dropPace;
	private Label rationsDescript;
	private Label paceDescript;
	private Button btnTakeTurn;
	private Label lbl1;
	private Label lbl2;
	private Label lbl3;
	private Label lblDistance;
	private Label lblNotify;
	int passedTowns = 0;
	
	private static Wagon wagon;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public FieldScreen(Composite parent, int style) {
		super(parent, style);
		
		wagon = World.getWagon();
		
		createContents();
		
		//logic for Take Turn button
		btnTakeTurn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				wagon.takeStep();
				update();
				System.out.println(wagon.getTownDistance());
				//check if we're in oregon
				if(World.getWagon().getDistance() >= 1909) {
					System.out.println("Welcome to Oregon! You Win!");
					System.exit(0);
				}
				
				//Checks if the party has reached a river or town
				if(World.getMap().distanceToRiver(World.getWagon().getDistance()) <= World.getWagon().getPace() && 
					World.getMap().getNextRiver(World.getWagon().getDistance()) != null){
					/*Checks distance to next river based upon current pace and if a next river exists*/
						String name = World.getMap().getNextRiver(World.getWagon().getDistance()).getName();
						System.out.println("You've reached " + name);
						lblNotify.setText("You've reached " + name);
				} else if(World.getMap().distanceToTown(World.getWagon().getDistance()) <= World.getWagon().getPace()){
					/*Checks distance to next town based upon current pace*/
					System.out.println("You've reached " + World.getTown().getTownName());
					lblNotify.setText("You've reached " + World.getTown().getTownName());
				} else {
					/*clears label to prevent notification spamming*/
					lblNotify.setText("");
				}
			}
		});
		
		//logic for ration drop down menu
		dropRations.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int cur_rations = rations(dropRations.getText());
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
		
		//Logic when user selects a new item from the pace drop down
		dropPace.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int cur_pace = pace(dropPace.getText());
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
	
	/**
	 * updates labels and drop menus based on previous user selection
	 * in configuration screen
	 */
	public void update(){
		dropRations.setText(rations(wagon.getRations()));
		
		dropPace.setText(pace(wagon.getPace()));
		
		rationsDescript.setText(rationsDescript(wagon.getRations()));
		
		paceDescript.setText(paceDescript(wagon.getPace()));
		
		lblDistance.setText("" + wagon.getDistance());
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	/**
	 * creates the controls for the composite
	 */
	private void createContents(){
		btnTakeTurn = new Button(this, SWT.NONE);
		btnTakeTurn.setText("Take Turn");
		btnTakeTurn.setBounds(346, 241, 94, 28);
		
		dropRations = new Combo(this, SWT.NONE);
		dropRations.setItems(new String[] {"None", "Bare-Bones", "Meager", "Normal", "Wellfed"});
		dropRations.setBounds(65, 246, 94, 23);
		
		dropPace = new Combo(this, SWT.NONE);
		dropPace.setItems(new String[] {"Stopped", "Leisurely", "Steady", "Grueling"});
		dropPace.setBounds(65, 209, 94, 23);
		
		lbl1 = new Label(this, SWT.NONE);
		lbl1.setText("Rations:");
		lbl1.setBounds(10, 249, 49, 13);
		
		lbl2 = new Label(this, SWT.NONE);
		lbl2.setText("Pace:");
		lbl2.setBounds(10, 212, 49, 13);
		
		lbl3 = new Label(this, SWT.NONE);
		lbl3.setBounds(182, 10, 49, 15);
		lbl3.setText("Distance:");
		
		lblDistance = new Label(this, SWT.NONE);
		lblDistance.setText("0");
		lblDistance.setBounds(235,10,32,13);
		
		rationsDescript = new Label(this, SWT.WRAP);
		rationsDescript.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		rationsDescript.setBounds(165, 248, 175, 23);
		
		paceDescript = new Label(this, SWT.WRAP);
		paceDescript.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		paceDescript.setBounds(165, 209, 273, 26);
		
		lblNotify = new Label(this, SWT.WRAP);
		lblNotify.setAlignment(SWT.CENTER);
		lblNotify.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblNotify.setBounds(10, 31, 430, 35);
		lblNotify.setText("The Journey Begins!");
	}
}
