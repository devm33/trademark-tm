package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Button;

public class MapScreen extends Composite {

	/**
	 * Screen which displays route Map
	 * 
	 * 
	 * @author - David Byas-Smith
	 */
	public MapScreen(Composite parent, int style) {
		super(parent, style);
		
		Label lblDest = new Label(this, SWT.NONE);
		lblDest.setBounds(377, 127, 63, 13);
		lblDest.setText("Ft. Kearny");
		
		Label lblIndependence = new Label(this, SWT.NONE);
		lblIndependence.setBounds(10, 127, 82, 13);
		lblIndependence.setText("Independence");
		
		ProgressBar progressBar = new ProgressBar(this, SWT.NONE);
		progressBar.setBounds(10, 146, 430, 18);
		
		Label lblDistanceRemaining = new Label(this, SWT.NONE);
		lblDistanceRemaining.setBounds(156, 170, 97, 13);
		lblDistanceRemaining.setText("Distance Remaining:");
		
		Label label = new Label(this, SWT.NONE);
		label.setBounds(259, 170, 49, 13);
		label.setText("0000");
		
		Canvas canvas = new Canvas(this, SWT.NONE);
		canvas.setBounds(10, 10, 430, 108);
		
		Canvas canvas_1 = new Canvas(this, SWT.NONE);
		canvas_1.setBounds(10, 189, 430, 71);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setBounds(185, 267, 68, 23);
		btnNewButton.setText("Close Map");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
