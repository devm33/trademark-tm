package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * River crossing choice/minigame
 * @author Jaron
 *
 */
public class RiverScreen extends Composite {
	private boolean done = false;
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
	
	/**
	 * check if user is finished with river screen
	 * @return
	 */
	public boolean isDone(){
		return done;
	}
	
	/**
	 * reset done variable so user can re-enter river screen
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
		btnQuit = new Button(this, SWT.NONE);
		btnQuit.setBounds(162, 265, 84, 25);
		btnQuit.setText("Quit Crossing");
	}
}
