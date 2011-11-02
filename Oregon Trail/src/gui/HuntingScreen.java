package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class HuntingScreen extends Composite {
	public boolean done = false;
	private Button btnReturn;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public HuntingScreen(Composite parent, int style) {
		super(parent, style);
		
		createContents();
		
		//logic for the Return From Hunt button
		btnReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				done = true;
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	private void createContents(){
		btnReturn = new Button(this, SWT.NONE);
		btnReturn.setBounds(164, 265, 104, 25);
		btnReturn.setText("Return from hunt");
	}
}
