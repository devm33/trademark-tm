package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class WinScreen extends Composite {
	private Label lbl1;
	private Button btnNewGame;
	private Button btnQuitGame;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public WinScreen(Composite parent, int style) {
		super(parent, style);
		
		createContents();
		
		//logic for New Game button
		btnNewGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});

		//logic for Quit Game button
		btnQuitGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	private void createContents(){
		lbl1 = new Label(this, SWT.NONE);
		lbl1.setFont(SWTResourceManager.getFont("Segoe UI", 40, SWT.NORMAL));
		lbl1.setBounds(43, 50, 290, 82);
		lbl1.setText("YOU WIN!");
		
		btnNewGame = new Button(this, SWT.NONE);
		btnNewGame.setBounds(71, 180, 75, 25);
		btnNewGame.setText("New Game");
		
		btnQuitGame = new Button(this, SWT.NONE);
		btnQuitGame.setBounds(258, 180, 75, 25);
		btnQuitGame.setText("Quit Game");
	}
}
