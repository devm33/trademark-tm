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
	
//	private int townDistanceRemaining;
	private int gameDistanceRemaining;
	private final int totalGameDistance = 1909;
	
	/**
	 * creates the map screen
	 * @param parent
	 * @param style
	 */
	public MapScreen(Composite parent, int style) {
		super(parent, style);
		
		createContents();
	}

	/**
	 * check if user is finished with map screen
	 * @return
	 */
	public boolean isDone(){
		return done;
	}
	
	/**
	 * reset done variable so user can re-enter map screen
	 */
	public void resetDone(){
		done = false;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	/**
	 * updates map screen
	 */
	public void update(){
		//update town distance
		townProgressBar.setMaximum(World.getMap().totalDistanceToTown());
		townProgressBar.setSelection((World.getMap().totalDistanceToTown()-World.getMap().distanceToTown()));
		lblTownDistanceRemaining.setText(Integer.toString(World.getMap().distanceToTown()));
		//update towns
		lblDepart.setText(World.getMap().geLastTown().getTownName());
		lblDest.setText(World.getMap().getNextTown().getTownName());
		//update game distance
		gameProgressBar.setSelection(World.getWagon().getDistance());
		gameDistanceRemaining = totalGameDistance - World.getWagon().getDistance();
		lblGameDistanceRemaining.setText(Integer.toString(gameDistanceRemaining));
	}
	
	/**
	 * creates controls for composite
	 */
	private void createContents(){
		lblDest = new Label(this, SWT.WRAP);
		lblDest.setAlignment(SWT.RIGHT);
		lblDest.setBounds(268, 170, 172, 15);
		lblDest.setText("Next Town");
		
		lblDepart = new Label(this, SWT.WRAP);
		lblDepart.setBounds(10, 170, 130, 15);
		lblDepart.setText("Past Town");
		
		
		//townDistanceRemaining = Map.distanceToTown();
		lblDistanceRemaining = new Label(this, SWT.NONE);
		lblDistanceRemaining.setBounds(121, 266, 108, 15);
		lblDistanceRemaining.setText("Distance Remaining:");
		
		townProgressBar = new ProgressBar(this, SWT.NONE);
		townProgressBar.setMaximum(World.getMap().totalDistanceToTown());
		townProgressBar.setBounds(10, 189, 430, 18);
		
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
		lblTownDistanceRemaining.setText(Integer.toString(World.getMap().distanceToTown()));
		lblTownDistanceRemaining.setBounds(235, 213, 49, 13);
	}
}
