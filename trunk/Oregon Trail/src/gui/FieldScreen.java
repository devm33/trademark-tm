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

public class FieldScreen extends Composite {
	private Combo dropRations;
	private Combo dropPace;
	private Label rationsDescript;
	private Label paceDescript;
	private Button btnTakeTurn;
	private Label lbl1;
	private Label lbl2;
	
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
				System.out.println(World.getMap().distanceToTown(wagon.getTownDistance()));
			}
		});
		
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
	
	public void update(){
		switch(wagon.getRations()){
		case 1:
			dropRations.setText("Bare-Bones");
			rationsDescript.setText("1 pound of food per person per day.");
			break;
		case 2:
			dropRations.setText("Meager");
			rationsDescript.setText("2 pounds of food per person per day.");
			break;
		case 3: 
			dropRations.setText("Normal");
			rationsDescript.setText("3 pounds of food per person per day.");
			break;
		case 4: 
			dropRations.setText("Wellfed");
			rationsDescript.setText("4 pounds of food per person per day.");
			break;
		}
		
		switch(wagon.getPace()){
		case 5:
			dropPace.setText("Leisurely");
			paceDescript.setText("5 miles per day slow and restful.\n Helps recover from exhaustion.");
			break;
		case 10:
			dropPace.setText("Steady");
			paceDescript.setText("10 miles per day basic pace.\n Normal fatigue.");
			break;
		case 15:
			dropPace.setText("Grueling");
			paceDescript.setText("15 miles per day hard pace.\n Oxen and people rapidly become tired, then exhausted.");
			break;
		}
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	private void createContents(){
		btnTakeTurn = new Button(this, SWT.NONE);
		btnTakeTurn.setText("Take Turn");
		btnTakeTurn.setBounds(299, 241, 94, 28);
		
		dropRations = new Combo(this, SWT.NONE);
		dropRations.setItems(new String[] {"None", "Bare-Bones", "Meager", "Normal", "Wellfed"});
		dropRations.setBounds(65, 197, 94, 23);
		
		dropPace = new Combo(this, SWT.NONE);
		dropPace.setItems(new String[] {"Stopped", "Leisurely", "Steady", "Grueling"});
		dropPace.setBounds(65, 245, 94, 23);
		
		lbl1 = new Label(this, SWT.NONE);
		lbl1.setText("Rations:");
		lbl1.setBounds(10, 205, 49, 13);
		
		lbl2 = new Label(this, SWT.NONE);
		lbl2.setText("Pace:");
		lbl2.setBounds(17, 248, 49, 13);
		
		rationsDescript = new Label(this, SWT.NONE);
		rationsDescript.setBounds(174, 142, 266, 42);
		
		paceDescript = new Label(this, SWT.NONE);
		paceDescript.setBounds(147, 83, 266, 42);
	}
}
