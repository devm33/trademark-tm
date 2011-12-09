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

public class LoseScreen extends Composite {
	private boolean done = false;
	private Label lbl1;
	private Button btnNewGame;
	private Button btnQuitGame;
	private Canvas canvas;
	private Image image;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LoseScreen(Composite parent, int style) {
		super(parent, style);
		
		createContents();
	/*	
		//logic for new game button
		btnNewGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				choice = 1;
			}
		});
		*/
		//logic for quit game button
		btnQuitGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				done = true;
			}
		});
	}
	

	/**
	 * returns true if user clicks quit game
	 * @return
	 */
	public boolean getDone(){
		return done;
	}
	
	/**
	 * sets the label text to notify user of cause of loss
	 */
	public void setNotification(String a){
		lbl1.setText(a);
	}
	
	/**
	 * reset choice so user can play again
	 */
	/*
	public void resetDone(){
		done = false;
	}
	*/
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	/**
	 * creates controls for the composite
	 */
	private void createContents(){
		lbl1 = new Label(this, SWT.WRAP);
		lbl1.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lbl1.setBounds(10, 234, 430, 25);
		lbl1.setText("Sorry. You were not smart enough to survive the Oregon Trail.");
		
		btnQuitGame = new Button(this, SWT.NONE);
		btnQuitGame.setEnabled(true);
		btnQuitGame.setBounds(365, 265, 75, 25);
		btnQuitGame.setText("Quit");
		
		Label lblYouLose = new Label(this, SWT.NONE);
		lblYouLose.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD | SWT.ITALIC));
		lblYouLose.setBounds(130, 252, 165, 35);
		lblYouLose.setText("YOU LOSE!");
		
		canvas = new Canvas(this, SWT.NONE);
		canvas.setBounds(10, 10, 430, 216);
		image = new Image(getDisplay(),getClass().getResourceAsStream("images/lose.jpg"));
		
		canvas.addPaintListener(new PaintListener() {
	        public void paintControl(PaintEvent e) {
	         e.gc.drawImage(image,0,0);
	        }
	    });
	}
}
