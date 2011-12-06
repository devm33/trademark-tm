package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LoseScreen extends Composite {
	private boolean done = false;
	private Label lbl1;
	private Button btnNewGame;
	private Button btnQuitGame;
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
		lbl1 = new Label(this, SWT.NONE);
		lbl1.setFont(SWTResourceManager.getFont("Segoe UI", 29, SWT.NORMAL));
		lbl1.setBounds(68, 72, 220, 46);
		lbl1.setText("You lose!");
		
		btnQuitGame = new Button(this, SWT.NONE);
		btnQuitGame.setEnabled(true);
		btnQuitGame.setBounds(294, 247, 75, 25);
		btnQuitGame.setText("Quit");
	}
}
