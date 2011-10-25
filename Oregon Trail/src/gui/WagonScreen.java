package gui;

import game.Wagon;
import game.World;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
/**
 * Screen to display the wagon contents and controls.
 * 
 * 
 * @author Devraj Mehta
 *
 */
public class WagonScreen extends Composite {
	private Text txtThisIsYour;
	private Text txtPace;
	private Text txtRations;
	private Text rationsDescript;
	private Text paceDescript;
	private Text txtFoodRemaining;
	private Text food;
	private Text txtDistanceRemaining;
	private Text distance;
	private Text leader;
	private Text traveler0;
	private Text traveler1;
	private Text traveler2;
	private Text traveler3;
	private Button btnTakeTurn;
	private Combo rations;
	private Combo pace;
	
	
	
	private Wagon wagon;

	public WagonScreen(Composite arg0, int arg1) {
		super(arg0, arg1);
		
		wagon = World.getWagon();
		
		txtThisIsYour = new Text(this, SWT.BORDER);
		txtThisIsYour.setText("This is your wagon. There are many like it...");
		txtThisIsYour.setBounds(0, 0, 115, 42);
		
		btnTakeTurn = new Button(this, SWT.NONE);
		btnTakeTurn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(rations.get)
				
				
				
				
				wagon.takeStep();
			}
		});
		btnTakeTurn.setBounds(0, 49, 94, 28);
		btnTakeTurn.setText("Take Turn");
		
		rations = new Combo(this, SWT.NONE);
		rations.setItems(new String[] {"None", "bare-bones", "Meager", "Normal", "Wellfed"});
		rations.setBounds(68, 83, 94, 22);
		rations.setText("Normal");
		
		txtPace = new Text(this, SWT.BORDER);
		txtPace.setText("Pace:");
		txtPace.setBounds(0, 145, 64, 19);
		
		txtRations = new Text(this, SWT.BORDER);
		txtRations.setText("Rations:");
		txtRations.setBounds(0, 84, 64, 19);
		
		pace = new Combo(this, SWT.NONE);
		pace.setItems(new String[] {"Stopped", "Leisurely", "Steady", "Grueling"});
		pace.setBounds(68, 144, 94, 22);
		pace.setText("Steady");
		
		rationsDescript = new Text(this, SWT.BORDER);
		rationsDescript.setBounds(174, 83, 210, 42);
		
		paceDescript = new Text(this, SWT.BORDER);
		paceDescript.setBounds(174, 145, 210, 42);
		
		txtFoodRemaining = new Text(this, SWT.BORDER);
		txtFoodRemaining.setText("Food remaining:");
		txtFoodRemaining.setBounds(157, 10, 94, 19);
		
		food = new Text(this, SWT.BORDER);
		food.setBounds(257, 10, 64, 19);
		
		txtDistanceRemaining = new Text(this, SWT.BORDER);
		txtDistanceRemaining.setText("Distance remaining:");
		txtDistanceRemaining.setBounds(157, 42, 115, 19);
		
		distance = new Text(this, SWT.BORDER);
		distance.setBounds(278, 42, 64, 19);
		
		leader = new Text(this, SWT.BORDER);
		leader.setBounds(0, 211, 200, 19);
		
		traveler0 = new Text(this, SWT.BORDER);
		traveler0.setBounds(0, 241, 200, 19);
		
		traveler1 = new Text(this, SWT.BORDER);
		traveler1.setBounds(0, 271, 200, 19);
		
		traveler2 = new Text(this, SWT.BORDER);
		traveler2.setBounds(219, 224, 200, 19);
		
		traveler3 = new Text(this, SWT.BORDER);
		traveler3.setBounds(219, 257, 200, 19);
	}
	
	public void update() {
		
		rations.setBounds(68, 83, 94, 22);
		rations.setText("Normal");
		
		txtPace = new Text(this, SWT.BORDER);
		txtPace.setText("Pace:");
		txtPace.setBounds(0, 145, 64, 19);
		
		txtRations = new Text(this, SWT.BORDER);
		txtRations.setText("Rations:");
		txtRations.setBounds(0, 84, 64, 19);
		
		pace.setItems(new String[] {"Stopped", "Leisurely", "Steady", "Grueling"});
		pace.setBounds(68, 144, 94, 22);
		pace.setText("Steady");
		
		rationsDescript = new Text(this, SWT.BORDER);
		rationsDescript.setBounds(174, 83, 210, 42);
		
		paceDescript = new Text(this, SWT.BORDER);
		paceDescript.setBounds(174, 145, 210, 42);
		
		txtFoodRemaining = new Text(this, SWT.BORDER);
		txtFoodRemaining.setText("Food remaining:");
		txtFoodRemaining.setBounds(157, 10, 94, 19);
		
		food = new Text(this, SWT.BORDER);
		food.setBounds(257, 10, 64, 19);
		
		txtDistanceRemaining = new Text(this, SWT.BORDER);
		txtDistanceRemaining.setText("Distance remaining:");
		txtDistanceRemaining.setBounds(157, 42, 115, 19);
		
		distance = new Text(this, SWT.BORDER);
		distance.setBounds(278, 42, 64, 19);
		
		leader = new Text(this, SWT.BORDER);
		leader.setBounds(0, 211, 200, 19);
		
		traveler0 = new Text(this, SWT.BORDER);
		traveler0.setBounds(0, 241, 200, 19);
		
		traveler1 = new Text(this, SWT.BORDER);
		traveler1.setBounds(0, 271, 200, 19);
		
		traveler2 = new Text(this, SWT.BORDER);
		traveler2.setBounds(219, 224, 200, 19);
		
		traveler3 = new Text(this, SWT.BORDER);
		traveler3.setBounds(219, 257, 200, 19);
	}
	
	
	private String rations(int r) {
		switch(r) {
		case 0:
			return "None";
		case 1:
			return "Barebones";
		case 2:
			return "Meager";
		case 3:
			return "Normal";
		case 4:
			return "Wellfed";
		}
		return null;
	}
	private int rations(String r) {
		switch(r) {
		case 0:
			return "None";
		case 1:
			return "Barebones";
		case 2:
			return "Meager";
		case 3:
			return "Normal";
		case "Wellfed":
			return 4;
		}
		reutnr -1;
	}
	private String pace(int p) {
		switch(r) {
		case 0:
			return "None";
		case 1:
			return "Barebones";
		case 2:
			return "Meager";
		case 3:
			return "Normal";
		case 4:
			return "Wellfed";
		}
		return null;
	}
	private int pace(String p) {
		switch(r) {
		case 0:
			return "None";
		case 1:
			return "Barebones";
		case 2:
			return "Meager";
		case 3:
			return "Normal";
		case 4:
			return "Wellfed";
		}
		return -1;
	}
}
