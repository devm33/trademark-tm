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
 * Screen to display the World.getWagon() contents and controls.
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

	/**
	 * creates the wagon status composite
	 * @param arg0
	 * @param arg1
	 */
	public WagonScreen(Composite arg0, int arg1) {
		super(arg0, arg1);
		
		
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
		
		food.setText("" + World.getWagon().getInventory().getFood().getNumber());
		
		distance.setText("" + World.getWagon().getDistance());
		
		List<Person> pass = World.getWagon().getPassengers();
		Label[] labels = {leader, traveler0, traveler1, traveler2, traveler3};
		int i = 0;
		for(Person p : pass) {
			labels[i].setText(p.toString());
			i++;
		}
	}
	
	/**
	 * checks if user is leaving the World.getWagon() status screen
	 * @return
	 */
	public boolean isDone(){
		return done;
	}
	
	/**
	 * reset done boolean so user can re-enter World.getWagon() status screen
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
		food.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		food.setBounds(174, 44, 88, 24);
		
		distance = new Label(this, SWT.NONE);
		distance.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		distance.setBounds(174, 74, 88, 26);
		distance.setText("0");
		
		leader = new Label(this, SWT.NONE);
		leader.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		leader.setBounds(2, 117, 440, 24);
		
		traveler0 = new Label(this, SWT.NONE);
		traveler0.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		traveler0.setBounds(2, 154, 440, 24);
		
		traveler1 = new Label(this, SWT.NONE);
		traveler1.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		traveler1.setBounds(2, 193, 440, 24);
		
		traveler2 = new Label(this, SWT.NONE);
		traveler2.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		traveler2.setBounds(2, 232, 440, 24);
		
		traveler3 = new Label(this, SWT.NONE);
		traveler3.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		traveler3.setBounds(2, 271, 440, 24);
		
		lblWagon = new Label(this, SWT.NONE);
		lblWagon.setAlignment(SWT.CENTER);
		lblWagon.setFont(SWTResourceManager.getFont("Tahoma", 16, SWT.NORMAL));
		lblWagon.setBounds(10, 10, 430, 28);
		lblWagon.setText("Wagon Status"); //This is myWagon. There are many like it, but this one is mine.
		
		lblFoodRemaining = new Label(this, SWT.NONE);
		lblFoodRemaining.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblFoodRemaining.setBounds(43, 44, 117, 24);
		lblFoodRemaining.setText("Food Remaining:");
		
		lblDistanceRemaining = new Label(this, SWT.NONE);
		lblDistanceRemaining.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblDistanceRemaining.setBounds(43, 74, 125, 26);
		lblDistanceRemaining.setText("Distance Traveled:");
		
		btnDone = new Button(this, SWT.NONE);
		btnDone.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnDone.setBounds(268, 44, 98, 56);
		btnDone.setText("Return");
	}
}
