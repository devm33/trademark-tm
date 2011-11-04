package gui;

import game.World;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * Screen which displays route Map
 * 
 * 
 * @author - David Byas-Smith
 */

public class MapScreen extends Composite {
	private boolean done = false;
	
	private Canvas canvas;
	private Image image;
	private Button btnCloseMap;
	private Label lblDest;
	private Label lblDepart;
	private Label lblDistanceRemaining;
	private Label lblGameDistanceRemaining;
	private Label lblStart;
	private Label lblOregon;
	private Label label_1;
	private Label lblTownDistanceRemaining;
	private ProgressBar townProgressBar;
	private ProgressBar gameProgressBar;
	
	private int townDistanceRemaining;
	private int gameDistanceRemaining;
	private final int totalGameDistance = 1909;
	
	
	public MapScreen(Composite parent, int style) {
		super(parent, style);
		
		createContents();
	}

	public boolean isDone(){
		return done;
	}
	
	public void resetDone(){
		done = false;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	public void update(){
		gameProgressBar.setSelection(World.getWagon().getDistance());
		townProgressBar.setSelection(World.getWagon().getTownDistance());
		lblTownDistanceRemaining.setText(Integer.toString(World.getMap().distanceToRiver(World.getWagon().getTownDistance())));
	}
	
	private void createContents(){
		lblDest = new Label(this, SWT.NONE);
		lblDest.setBounds(377, 170, 63, 15);
		lblDest.setText("Next Town");
		
		lblDepart = new Label(this, SWT.NONE);
		lblDepart.setBounds(10, 170, 82, 15);
		lblDepart.setText("Past Town");
		
		
		//townDistanceRemaining = Map.distanceToTown();
		lblDistanceRemaining = new Label(this, SWT.NONE);
		lblDistanceRemaining.setBounds(121, 266, 108, 15);
		lblDistanceRemaining.setText("Distance Remaining:");
		
		townProgressBar = new ProgressBar(this, SWT.NONE);
		townProgressBar.setMaximum(World.getMap().distanceToTown(World.getWagon().getTownDistance()));
		townProgressBar.setBounds(10, 189, 430, 18);
		//townProgressBar.setMaximum();
		
		gameDistanceRemaining = totalGameDistance - World.getWagon().getDistance();
		lblGameDistanceRemaining = new Label(this, SWT.NONE);
		lblGameDistanceRemaining.setBounds(235, 266, 49, 13);
		lblGameDistanceRemaining.setText(Integer.toString(gameDistanceRemaining));
		
		gameProgressBar = new ProgressBar(this, SWT.NONE);
		gameProgressBar.setBounds(10, 242, 430, 18);
		gameProgressBar.setMaximum(totalGameDistance);

		
		canvas = new Canvas(this, SWT.NONE);
		image = new Image(getDisplay(),getClass().getResourceAsStream("images/map1.jpg"));
		canvas.setBounds(10, 10, 430, 154);
		
		canvas.addPaintListener(new PaintListener() {
	        public void paintControl(PaintEvent e) {
	         e.gc.drawImage(image,0,0);
	        }
	    });
		
		btnCloseMap = new Button(this, SWT.NONE);
		btnCloseMap.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				done = true;
			}
		});
		btnCloseMap.setBounds(371, 267, 68, 23);
		btnCloseMap.setText("Close Map");
		
		
		lblStart = new Label(this, SWT.NONE);
		lblStart.setBounds(10, 223, 49, 15);
		lblStart.setText("Start");
		
		lblOregon = new Label(this, SWT.NONE);
		lblOregon.setBounds(391, 223, 49, 15);
		lblOregon.setText("Oregon");
		
		label_1 = new Label(this, SWT.NONE);
		label_1.setText("Distance Remaining:");
		label_1.setBounds(121, 213, 108, 15);
		
		lblTownDistanceRemaining = new Label(this, SWT.NONE);
		lblTownDistanceRemaining.setText("0000");
		lblTownDistanceRemaining.setBounds(235, 213, 49, 13);
	}
}
