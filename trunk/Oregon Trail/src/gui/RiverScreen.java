package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class RiverScreen extends Composite {
	public boolean done = false;
	private Button btnQuit;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RiverScreen(Composite parent, int style) {
		super(parent, style);
		
		createContents();
		
		//logic for the Quit Crossing button
		btnQuit.addSelectionListener(new SelectionAdapter() {
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
		btnQuit = new Button(this, SWT.NONE);
		btnQuit.setBounds(162, 265, 84, 25);
		btnQuit.setText("Quit Crossing");
	}
}
