package gui;

import game.World;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;

/**
 * The screen where the party travels outside of towns
 * and encounters events.
 * 
 * May initialize hunting or river crossing mini games.
 * @author Jaron
 *
 */
public class FieldScreen extends Composite {
	private boolean atRiver = false, atTown = false, hunting = false, 
			win = false, reachedRiver = false, reachedTown = false;
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
	
	private Image image;
	private Canvas canvas;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public FieldScreen(Composite parent, int style) {
		super(parent, style);
		
		createContents();
		
		//logic for Take Turn button
		btnTakeTurn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {

				World.getEvent().eventCall();
				World.getWagon().takeStep();
				update();
				
				//check if we're in oregon or has reached a river or town
				if(World.getWagon().getDistance() >= 1909) {
					System.out.println("Welcome to Oregon! You Win!");
					win = true;
				} else {
					handleRiverOrTown();
					if(World.getMap().distanceToRiver() <= World.getWagon().getPace() && 
						World.getMap().getNextRiver() != null){
						/*Checks distance to next river based upon current pace and if a next river exists*/
						reachedRiver = true;
						//atRiver = true;
					} else if(World.getMap().distanceToTown() <= World.getWagon().getPace()){
						/*Checks distance to next town based upon current pace*/
						reachedTown = true;
					}
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
				if(cur_rations != World.getWagon().getRations()) {
					World.getWagon().setRations(cur_rations);
					rationsDescript.setText(rationsDescript(World.getWagon().getRations()));
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
				if(cur_pace != World.getWagon().getPace()) {
					World.getWagon().setPace(cur_pace);
					paceDescript.setText(paceDescript(World.getWagon().getPace()));
				}
			}
		});
	}
	
	/**
	 * check if user won the game
	 * @return
	 */
	public boolean getWin(){
		return win;
	}
	
	/**
	 * reset win boolean so user can play again
	 * @param b
	 */
	public void resetWin(){
		win = false;
	}
	
	/**
	 * check if user has reached a town
	 * @return
	 */
	public boolean checkAtTown(){
		return atTown;
	}
	
	/**
	 * reset atTown boolean so user can re-enter town later
	 */
	public void resetAtTown(){
		atTown = false;
	}
	
	/**
	 * check if user has reached a river
	 * @return
	 */
	public boolean checkAtRiver(){
		return atRiver;
	}
	
	/**
	 * reset atRiver boolean so user can re-enter town later
	 */
	public void resetAtRiver(){
		atRiver = false;
	}
	
	/**
	 * logic for reaching a river or town
	 */
	private void handleRiverOrTown(){
		if(reachedRiver){
			atRiver = true;
			reachedRiver = false;
		} else if(reachedTown){
			atTown = true;
			reachedTown = false;
		} else {
			/*clears label to prevent notification spamming*/
			//lblNotify.setText("");
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
	
	/**
	 * updates labels and drop menus based on previous user selection
	 * in configuration screen
	 */
	public void update(){
		dropRations.setText(rations(World.getWagon().getRations()));
		
		dropPace.setText(pace(World.getWagon().getPace()));
		
		rationsDescript.setText(rationsDescript(World.getWagon().getRations()));
		
		paceDescript.setText(paceDescript(World.getWagon().getPace()));
		
		lblDistance.setText("" + World.getWagon().getDistance());
	}
	
	/**
	 * shows notification after a river decision
	 */
	public void showRiverResult(){
		lblNotify.setText(World.getWagon().getNotification());
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
		btnTakeTurn.setBounds(344, 207, 94, 28);
		
		dropRations = new Combo(this, SWT.NONE);
		dropRations.setItems(new String[] {"None", "Bare-Bones", "Meager", "Normal", "Wellfed"});
		dropRations.setBounds(65, 207, 94, 23);
		
		dropPace = new Combo(this, SWT.NONE);
		dropPace.setItems(new String[] {"Stopped", "Leisurely", "Steady", "Grueling"});
		dropPace.setBounds(65, 249, 94, 23);
		
		lbl1 = new Label(this, SWT.NONE);
		lbl1.setText("Rations:");
		lbl1.setBounds(10, 210, 49, 13);
		
		lbl2 = new Label(this, SWT.NONE);
		lbl2.setText("Pace:");
		lbl2.setBounds(10, 252, 49, 13);
		
		lbl3 = new Label(this, SWT.NONE);
		lbl3.setBounds(177, 118, 49, 15);
		lbl3.setText("Distance:");
		
		lblDistance = new Label(this, SWT.NONE);
		lblDistance.setText("0");
		lblDistance.setBounds(232,118,32,13);
		
		rationsDescript = new Label(this, SWT.WRAP);
		rationsDescript.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		rationsDescript.setBounds(163, 207, 175, 23);
		
		paceDescript = new Label(this, SWT.WRAP);
		paceDescript.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		paceDescript.setBounds(163, 249, 275, 26);
		
		lblNotify = new Label(this, SWT.BORDER | SWT.WRAP | SWT.SHADOW_NONE);
		lblNotify.setAlignment(SWT.CENTER);
		lblNotify.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblNotify.setBounds(8, 135, 430, 65);
		lblNotify.setText("The Journey Begins!");
		
		canvas = new Canvas(this, SWT.NONE);
		canvas.setBounds(8, 10, 430, 102);
		image = new Image(getDisplay(),getClass().getResourceAsStream("images/wagon_Map.jpg"));
		canvas.addPaintListener(new PaintListener() {
	        public void paintControl(PaintEvent e) {
	         e.gc.drawImage(image,0,0);
	        }
	    });
	}
	/**
	 * Displays a message on the notify label.
	 * @param str the string to display
	 */
	public void displayMessage(String str) {
		lblNotify.setText(str);
	}
}
