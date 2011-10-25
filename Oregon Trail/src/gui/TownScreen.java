package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * Town screen composite
 * @author Jaron
 *
 */
public class TownScreen extends Composite{
	//0 is default, 1 is Inn, 2 is Store, 3 is Leave Town
	public int choice = 0;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TownScreen(Composite parent, int style){
		super(parent,style);
		
		Label lblTown = new Label(this, SWT.NONE);
		lblTown.setText("I'm a Town");
		lblTown.setBounds(10, 10, 201, 13);
		
		
		Button btnInn = new Button(this, SWT.NONE);
		btnInn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		btnInn.setText("Inn");
		btnInn.setBounds(10, 240, 122, 23);
		
		
		Button btnStore = new Button(this, SWT.NONE);
		btnStore.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				choice = 2;
			}
		});
		btnStore.setText("Store");
		btnStore.setBounds(165, 240, 122, 23);
		
		Button btnLeaveTown = new Button(this, SWT.NONE);
		btnLeaveTown.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				choice = 3;
			}
		});
		btnLeaveTown.setText("Leave Town");
		btnLeaveTown.setBounds(323, 240, 109, 23);
		
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {


	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
