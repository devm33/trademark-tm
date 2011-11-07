package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class InnScreen extends Composite {
	private boolean done = false;
	private Label lbl1;
	private Button btnRest;
	private Button btnLeave;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InnScreen(Composite parent, int style) {
		super(parent, style);
		
		createContents();
		
		//logic for the Rest button (costs 5 dollars)
		btnRest.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.out.println("PLACEHOLDER RESPONSE");
			}
		});
		
		//logic for the Leave button
		btnLeave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				done = true;
			}
		});
	}

	/**
	 * checks if the user is leaving the inn
	 * @return
	 */
	public boolean isDone(){
		return done;
	}
	
	/**
	 * resets done boolean so user can enter inn again
	 */
	public void resetDone(){
		done = false;
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
		lbl1.setAlignment(SWT.CENTER);
		lbl1.setFont(SWTResourceManager.getFont("Segoe UI", 31, SWT.NORMAL));
		lbl1.setBounds(10, 70, 400, 55);
		lbl1.setText("Welcome to our inn!");
		
		btnRest = new Button(this, SWT.NONE);
		btnRest.setBounds(76, 195, 75, 25);
		btnRest.setText("Rest ($5)");
		
		btnLeave = new Button(this, SWT.NONE);
		btnLeave.setBounds(268, 195, 75, 25);
		btnLeave.setText("Leave");
	}
}
