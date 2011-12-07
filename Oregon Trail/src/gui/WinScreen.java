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

/**
 * Screen that shows when the user wins the game
 * @author Jaron
 *
 */
public class WinScreen extends Composite {
	private boolean done = false;
	private Label lbl1;
	private Button btnNewGame;
	private Button btnQuitGame;
	private Canvas canvas;
	private Canvas canvas_1;
	private Image image;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public WinScreen(Composite parent, int style) {
		super(parent, style);
		
		createContents();
		/*
		//logic for New Game button
		btnNewGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				choice = 1;
			}
		});
		*/

		//logic for Quit Game button
		btnQuitGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				done = true;
			}
		});
	}

	/**
	 * return if user clicks quit game button
	 * @return
	 */
	public boolean getDone(){
		return done;
	}
	
	/**
	 * reset choice so user can play again
	 */
	public void resetDone(){
		done = false;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	/**
	 * creates controls for composite
	 */
	private void createContents(){
		lbl1 = new Label(this, SWT.NONE);
		lbl1.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		lbl1.setBounds(10, 240, 430, 25);
		lbl1.setText("Congratulations! You have reached Oregon!! You Win!!");
		
		btnQuitGame = new Button(this, SWT.NONE);
		btnQuitGame.setBounds(181, 265, 75, 25);
		btnQuitGame.setText("Quit");
		
		canvas = new Canvas(this, SWT.NONE);
		canvas.setBounds(10, 10, 430, 216);
		image = new Image(getDisplay(),getClass().getResourceAsStream("images/oregon.jpg"));
		
		canvas.addPaintListener(new PaintListener() {
	        public void paintControl(PaintEvent e) {
	         e.gc.drawImage(image,0,0);
	        }
	    });
	}
}
