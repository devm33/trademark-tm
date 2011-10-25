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
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
/**
 * Screen to display the wagon contents and controls.
 * 
 * 
 * @author Devraj Mehta
 *
 */
public class WagonScreen extends Composite {
	private Text rationsDescript;
	private Text paceDescript;
	private Text food;
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
		
		btnTakeTurn = new Button(this, SWT.NONE);
		btnTakeTurn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(rations.get)
				
				
				
				
				wagon.takeStep();
			}
		});
		btnTakeTurn.setBounds(20, 37, 94, 28);
		btnTakeTurn.setText("Take Turn");
		
		rations = new Combo(this, SWT.NONE);
		rations.setItems(new String[] {"None", "bare-bones", "Meager", "Normal", "Wellfed"});
		rations.setBounds(68, 83, 94, 22);
		rations.setText("Normal");
		
		pace = new Combo(this, SWT.NONE);
		pace.setItems(new String[] {"Stopped", "Leisurely", "Steady", "Grueling"});
		pace.setBounds(68, 144, 94, 22);
		pace.setText("Steady");
		
		rationsDescript = new Text(this, SWT.BORDER);
		rationsDescript.setBounds(174, 83, 210, 42);
		
		paceDescript = new Text(this, SWT.BORDER);
		paceDescript.setBounds(174, 145, 210, 42);
		
		food = new Text(this, SWT.BORDER);
		food.setBounds(257, 10, 64, 19);
		
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
		
		Label lblWagon = new Label(this, SWT.NONE);
		lblWagon.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		lblWagon.setBounds(10, 5, 75, 28);
		lblWagon.setText("WAGON");
		
		Label lblFoodRemaing = new Label(this, SWT.NONE);
		lblFoodRemaing.setBounds(163, 16, 88, 13);
		lblFoodRemaing.setText("Food Remaing:");
		
		Label lblDistanceRemaing = new Label(this, SWT.NONE);
		lblDistanceRemaing.setBounds(163, 45, 100, 13);
		lblDistanceRemaing.setText("Distance Remaing:");
		
		Label lblRatiions = new Label(this, SWT.NONE);
		lblRatiions.setBounds(13, 91, 49, 13);
		lblRatiions.setText("Rations:");
		
		Label lblPace = new Label(this, SWT.NONE);
		lblPace.setBounds(20, 147, 49, 13);
		lblPace.setText("Pace:");
	}
	
	public void update() {
		
		rations.setBounds(68, 83, 94, 22);
		rations.setText("Normal");

		
		pace.setItems(new String[] {"Stopped", "Leisurely", "Steady", "Grueling"});
		pace.setBounds(68, 144, 94, 22);
		pace.setText("Steady");
		
		rationsDescript = new Text(this, SWT.BORDER);
		rationsDescript.setBounds(174, 83, 210, 42);
		
		paceDescript = new Text(this, SWT.BORDER);
		paceDescript.setBounds(174, 145, 210, 42);
	
		
		food = new Text(this, SWT.BORDER);
		food.setBounds(257, 10, 64, 19);
		
		
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
		if(r.equals("None"))
			return 0;
		else if(r.equals("Barebones"))
			return 1;
		else if(r.equals("Meager"))
			return 2;
		else if(r.equals("Normal"))
			return 3;
		else if(r.equals("Wellfed"))
			return 4;
		return -1;
	}
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
}
