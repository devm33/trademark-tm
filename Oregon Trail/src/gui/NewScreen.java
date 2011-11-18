package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;

public class NewScreen extends Composite {
	private int choice = 0;
	private Button btnNew;
	private Button btnLoad;
	private Button btnQuit;
	private Canvas canvas;
	private Image image;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public NewScreen(Composite parent, int style) {
		super(parent, style);
		
		createContents();

	}
	
	/**
	 * gets the value of user decision. 1 = newgame, 2 = loadgame, 3 = quitgame
	 * @return the user decision
	 */
	public int getChoice(){
		return choice;
	}
	
	/**
	 * resets choice variable so user can return to screen
	 */
	public void resetChoice(){
		choice = 0;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	private void createContents(){
		
		btnNew = new Button(this, SWT.NONE);
		btnNew.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				choice = 1;
			}
		});
		btnNew.setBounds(10, 260, 158, 25);
		btnNew.setText("New Game");
		
		btnLoad = new Button(this, SWT.NONE);
		btnLoad.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				choice = 2;
			}
		});
		btnLoad.setBounds(174, 260, 158, 25);
		btnLoad.setText("Load Game");
		
		btnQuit = new Button(this, SWT.NONE);
		btnQuit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				choice = 3;
			}
		});
		btnQuit.setBounds(338, 260, 102, 25);
		btnQuit.setText("Quit Game");
		
		canvas = new Canvas(this, SWT.NONE);
		canvas.setBounds(10, 10, 430, 244);
		image = new Image(getDisplay(),getClass().getResourceAsStream("images/title.jpg"));
		canvas.addPaintListener(new PaintListener() {
	        public void paintControl(PaintEvent e) {
	         e.gc.drawImage(image,0,0);
	        }
	    });
	}
}
