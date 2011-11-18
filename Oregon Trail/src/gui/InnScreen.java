package gui;

import game.World;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;

import people.Person;

public class InnScreen extends Composite {
	private boolean done = false;
	private Button btnRest;
	private Button btnLeave;
	private Image image;
	private Canvas canvas;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InnScreen(Composite parent, int style) {
		super(parent, style);
		
		createContents();
		
		//logic for the Rest button (costs 5 dollars)
		btnRest.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for(Person p : World.getWagon().getMembers()){
					p.setHealed();
				}
				
				World.nextDay();
				for(Person p : World.getWagon().getPassengers()) {
					if(p.getHealth() > 0) {
						p.addHealth(100);
						p.eatFood(100);
						p.drinkWater(100);
					}
				}
			}
		});
		
		//logic for the Leave button
		btnLeave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				done = true;
			}
		});
	}

	/**
	 * checks if the user is leaving the inn
	 * @return
	 */
	public boolean isDone(){
		return done;
	}
	
	/**
	 * resets done boolean so user can enter inn again
	 */
	public void resetDone(){
		done = false;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	/**
	 * creates controls for the composite
	 */
	private void createContents(){
		
		btnRest = new Button(this, SWT.NONE);
		btnRest.setBounds(75, 265, 75, 25);
		btnRest.setText("Rest ($5)");
		
		btnLeave = new Button(this, SWT.NONE);
		btnLeave.setBounds(273, 265, 75, 25);
		btnLeave.setText("Leave");
		
		canvas = new Canvas(this, SWT.NONE);
		canvas.setBounds(10, 10, 430, 222);
		image = new Image(getDisplay(),getClass().getResourceAsStream("images/inn.jpg"));
		
		canvas.addPaintListener(new PaintListener() {
	        public void paintControl(PaintEvent e) {
	         e.gc.drawImage(image,0,0);
	        }
	    });
		
		Label lblWelcomeToThe = new Label(this, SWT.NONE);
		lblWelcomeToThe.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		lblWelcomeToThe.setBounds(10, 238, 430, 21);
		lblWelcomeToThe.setText("Welcome to the Inn. You can rest up for a small fee!");
	}
}
