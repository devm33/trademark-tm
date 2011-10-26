package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import javax.swing.ImageIcon;
import org.eclipse.swt.widgets.Canvas;

/**
 * Town screen composite
 * @author Jaron
 *
 */
public class TownScreen extends Composite{
	//0 is default, 1 is Inn, 2 is Store, 3 is Leave Town
	public int choice = 0;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TownScreen(Composite parent, int style){
		super(parent,style);
		
		
		Button btnInn = new Button(this, SWT.NONE);
		btnInn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		btnInn.setText("Inn");
		btnInn.setBounds(10, 240, 122, 23);
		
		
		Button btnStore = new Button(this, SWT.NONE);
		btnStore.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				choice = 2;
			}
		});
		btnStore.setText("Store");
		btnStore.setBounds(165, 240, 122, 23);
		
		Button btnLeaveTown = new Button(this, SWT.NONE);
		btnLeaveTown.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				choice = 3;
			}
		});
		btnLeaveTown.setText("Leave Town");
		btnLeaveTown.setBounds(323, 240, 109, 23);
		
		
		Canvas canvas = new Canvas(this, SWT.NONE);
		
		final Image image = new Image(getDisplay(),getClass().getResourceAsStream("images/town.jpg"));
		canvas.setBounds(0, 0, 450, 234);
		
		canvas.addPaintListener(new PaintListener() {
	        public void paintControl(PaintEvent e) {
	         e.gc.drawImage(image,0,0);
	        }
	    });
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {


	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
