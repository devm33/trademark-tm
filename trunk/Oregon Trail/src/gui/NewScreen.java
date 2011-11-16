package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class NewScreen extends Composite {
	private int choice = 0;
	private Label lbl1;
	private Button btnNew;
	private Button btnLoad;
	private Button btnQuit;
	
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
		lbl1 = new Label(this, SWT.NONE);
		lbl1.setFont(SWTResourceManager.getFont("Segoe UI", 27, SWT.BOLD));
		lbl1.setAlignment(SWT.CENTER);
		lbl1.setBounds(110, 41, 229, 63);
		lbl1.setText("Oregon Trail");
		
		btnNew = new Button(this, SWT.NONE);
		btnNew.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				choice = 1;
			}
		});
		btnNew.setBounds(131, 110, 194, 25);
		btnNew.setText("New Game");
		
		btnLoad = new Button(this, SWT.NONE);
		btnLoad.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				choice = 2;
			}
		});
		btnLoad.setBounds(131, 160, 194, 25);
		btnLoad.setText("Load Game");
		
		btnQuit = new Button(this, SWT.NONE);
		btnQuit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				choice = 3;
			}
		});
		btnQuit.setBounds(194, 208, 75, 25);
		btnQuit.setText("Quit Game");
	}
}
