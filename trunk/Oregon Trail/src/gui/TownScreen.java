package gui;

import game.Town;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * Town screen composite
 * @author Jaron
 *
 */
public class TownScreen extends Composite{
	//0 is default, 1 is Inn, 2 is Store, 3 is Leave Town
	private int choice = 0;
	private Button btnInn;
	private Button btnStore;
	private Button btnLeaveTown;
	private Canvas canvas;
	private Image image;
	private Label lblWelcome;
	private Label lblTownName;
	private Town currentTown;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TownScreen(Composite parent, int style){
		super(parent,style);
		
		createContents();
		
		//Logic when user clicks the Inn button
		btnInn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				choice = 1;
			}
		});
		
		//Logic when user clicks the Store button
		btnStore.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				choice = 2;
			}
		});
		
		//logic when user clicks the Leave Town button
		btnLeaveTown.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				choice = 3;
			}
		});
	}
	
	/**
	 * checks which path the user picked (inn/store/leave)
	 * @return
	 */
	public int getChoice(){
		return choice;
	}

	/**
	 * reset choice so user can re-enter town
	 */
	public void resetChoice(){
		choice = 0;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void setTown(Town t) {
		currentTown = t;
		createContents();
	}
	
	/**
	 * Create contents of the window.
	 */
	private void createContents() {
		btnInn = new Button(this, SWT.NONE);
		btnInn.setText("Inn");
		btnInn.setBounds(10, 240, 122, 23);
		
		btnStore = new Button(this, SWT.NONE);
		btnStore.setText("Store");
		btnStore.setBounds(165, 240, 122, 23);
		
		btnLeaveTown = new Button(this, SWT.NONE);
		btnLeaveTown.setText("Leave Town");
		btnLeaveTown.setBounds(323, 240, 109, 23);
		
		canvas = new Canvas(this, SWT.NONE);
		
		image = new Image(getDisplay(),getClass().getResourceAsStream("images/town.jpg"));
		canvas.setBounds(0, 0, 450, 234);
		
		canvas.addPaintListener(new PaintListener() {
	        public void paintControl(PaintEvent e) {
	         e.gc.drawImage(image,0,0);
	        }
	    });
		
		lblWelcome = new Label(this, SWT.NONE);
		lblWelcome.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.BOLD | SWT.ITALIC));
		lblWelcome.setBounds(10, 269, 122, 24);
		lblWelcome.setText("Welcome to");

		lblTownName = new Label(this, SWT.NONE);
		lblTownName.setText("Independence");
		lblTownName.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.BOLD | SWT.ITALIC));
		lblTownName.setBounds(131, 269, 301, 24);
		
		if(currentTown != null)
			lblTownName.setText(currentTown.getTownName());
		
		if(currentTown == null || currentTown.getStore() == null)
			btnStore.setEnabled(false);
	}
}
