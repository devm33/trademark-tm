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

/**
 * Screen which displays route Map
 * 
 * 
 * @author - David Byas-Smith
 */

public class MapScreen extends Composite {


	
	private Image image;
	
	private int townDistanceRemaining;
	private int gameDistanceRemaining;
	private final int totalGameDistance = 1909;
	
	
	public MapScreen(Composite parent, int style) {
		super(parent, style);
		
		Label lblDest = new Label(this, SWT.NONE);
		lblDest.setBounds(377, 170, 63, 13);
		lblDest.setText("Ft. Kearny");
		
		Label lbldepart = new Label(this, SWT.NONE);
		lbldepart.setBounds(10, 170, 82, 13);
		lbldepart.setText("Independence");
		
		
		//townDistanceRemaining = Map.distanceToTown();
		Label lblDistanceRemaining = new Label(this, SWT.NONE);
		lblDistanceRemaining.setBounds(132, 266, 97, 13);
		lblDistanceRemaining.setText("Distance Remaining:");
		
		ProgressBar townProgressBar = new ProgressBar(this, SWT.NONE);
		townProgressBar.setMaximum(10);
		townProgressBar.setBounds(10, 189, 430, 18);
		//townProgressBar.setMaximum();
		
		gameDistanceRemaining = totalGameDistance - World.getWagon().getDistance();
		Label lblGameDistanceRemaining = new Label(this, SWT.NONE);
		lblGameDistanceRemaining.setBounds(235, 266, 49, 13);
		lblGameDistanceRemaining.setText(Integer.toString(gameDistanceRemaining));
		
		ProgressBar gameProgressBar = new ProgressBar(this, SWT.NONE);
		gameProgressBar.setBounds(10, 242, 430, 18);
		gameProgressBar.setMaximum(totalGameDistance);
		
		
		Canvas canvas = new Canvas(this, SWT.NONE);
		image = new Image(getDisplay(),getClass().getResourceAsStream("images/map1.jpg"));
		canvas.setBounds(10, 10, 430, 154);
		
		canvas.addPaintListener(new PaintListener() {
	        public void paintControl(PaintEvent e) {
	         e.gc.drawImage(image,0,0);
	        }
	    });
		
		Button btnCloseMap = new Button(this, SWT.NONE);
		btnCloseMap.setBounds(371, 267, 68, 23);
		btnCloseMap.setText("Close Map");
		
		
		Label lblStart = new Label(this, SWT.NONE);
		lblStart.setBounds(10, 223, 49, 13);
		lblStart.setText("Start");
		
		Label lblOregon = new Label(this, SWT.NONE);
		lblOregon.setBounds(391, 223, 49, 13);
		lblOregon.setText("Oregon");
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("Distance Remaining:");
		label_1.setBounds(132, 213, 97, 13);
		
		Label lblTownDistanceRemaining = new Label(this, SWT.NONE);
		lblTownDistanceRemaining.setText("0000");
		lblTownDistanceRemaining.setBounds(235, 213, 49, 13);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
