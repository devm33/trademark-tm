package gui;

import java.util.List;

import game.Wagon;
import game.World;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
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
	private boolean done = false;
	private Label food;
	private Label distance;
	private Label leader;
	private Label traveler0;
	private Label traveler1;
	private Label traveler2;
	private Label traveler3;
	private Label lblWagon;
	private Label lblFoodRemaining;
	private Label lblDistanceRemaining;
	private Button btnDone;
	
	private static Wagon wagon;

	/**
	 * creats the wagon status composite
	 * @param arg0
	 * @param arg1
	 */
	public WagonScreen(Composite arg0, int arg1) {
		super(arg0, arg1);
		
		wagon = World.getWagon();
		
		createContents();
		
		//Logic when user clicks the done button
		btnDone.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				done = true;
			}
		});
	}
	
	public void update() {
		
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
	 * checks if user is leaving the wagon status screen
	 * @return
	 */
	public boolean isDone(){
		return done;
	}
	
	/**
	 * reset done boolean so user can re-enter wagon status screen
	 */
	public void resetDone(){
		done = false;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	/**
	 * create composite controls
	 */
	private void createContents(){
		
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
		
		lblFoodRemaining = new Label(this, SWT.NONE);
		lblFoodRemaining.setBounds(143, 16, 108, 16);
		lblFoodRemaining.setText("Food Remaining:");
		
		lblDistanceRemaining = new Label(this, SWT.NONE);
		lblDistanceRemaining.setBounds(143, 45, 120, 13);
		lblDistanceRemaining.setText("Distance Traveled:");
		
		btnDone = new Button(this, SWT.NONE);
		btnDone.setBounds(344, 11, 75, 25);
		btnDone.setText("Return");
	}
}
