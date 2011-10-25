package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Combo;
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

	public WagonScreen(Composite arg0, int arg1) {
		super(arg0, arg1);
		
		txtThisIsYour = new Text(this, SWT.BORDER);
		txtThisIsYour.setText("This is your wagon");
		txtThisIsYour.setBounds(0, 0, 115, 42);
		
		Button btnTakeTurn = new Button(this, SWT.NONE);
		btnTakeTurn.setBounds(0, 49, 94, 28);
		btnTakeTurn.setText("Take Turn");
		
		Combo rations = new Combo(this, SWT.NONE);
		rations.setItems(new String[] {"None", "bare-bones", "Meager", "Normal", "Wellfed"});
		rations.setBounds(68, 83, 94, 22);
		rations.setText("Normal");
		
		txtPace = new Text(this, SWT.BORDER);
		txtPace.setText("Pace:");
		txtPace.setBounds(0, 145, 64, 19);
		
		txtRations = new Text(this, SWT.BORDER);
		txtRations.setText("Rations:");
		txtRations.setBounds(0, 84, 64, 19);
		
		Combo pace = new Combo(this, SWT.NONE);
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
		// TODO Auto-generated constructor stub
	}
}
