package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LoseScreen extends Composite {
	private int choice = 0;
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
		
		//logic for new game button
		btnNewGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				choice = 1;
			}
		});
		
		//logic for quit game button
		btnQuitGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				choice = 2;
			}
		});
	}

	/**
	 * return user choice. 1 for NEW GAME, 2 for QUIT GAME
	 * @return
	 */
	public int getChoice(){
		return choice;
	}
	
	/**
	 * reset choice so user can play again
	 */
	public void resetChoice(){
		choice = 0;
	}
	
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
		
		btnNewGame = new Button(this, SWT.NONE);
		btnNewGame.setBounds(68, 247, 75, 25);
		btnNewGame.setText("Main Menu");
		
		btnQuitGame = new Button(this, SWT.NONE);
		btnQuitGame.setEnabled(true);
		btnQuitGame.setBounds(294, 247, 75, 25);
		btnQuitGame.setText("Quit");
	}
}
