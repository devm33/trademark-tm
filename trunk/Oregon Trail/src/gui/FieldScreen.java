package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class FieldScreen extends Composite {
	private Combo dropRations;
	private Combo dropPace;
	private Button btnTakeTurn;
	private Label lbl1;
	private Label lbl2;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public FieldScreen(Composite parent, int style) {
		super(parent, style);
		
		createContents();
		
		//logic for Take Turn button
		btnTakeTurn.addSelectionListener(new SelectionAdapter() {
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
		btnTakeTurn = new Button(this, SWT.NONE);
		btnTakeTurn.setText("Take Turn");
		btnTakeTurn.setBounds(299, 241, 94, 28);
		
		dropRations = new Combo(this, SWT.NONE);
		dropRations.setItems(new String[] {"None", "Bare-Bones", "Meager", "Normal", "Wellfed"});
		dropRations.setBounds(65, 197, 94, 23);
		dropRations.setText("Normal");
		
		dropPace = new Combo(this, SWT.NONE);
		dropPace.setItems(new String[] {"Stopped", "Leisurely", "Steady", "Grueling"});
		dropPace.setBounds(65, 245, 94, 23);
		dropPace.setText("Steady");
		
		lbl1 = new Label(this, SWT.NONE);
		lbl1.setText("Rations:");
		lbl1.setBounds(10, 205, 49, 13);
		
		lbl2 = new Label(this, SWT.NONE);
		lbl2.setText("Pace:");
		lbl2.setBounds(17, 248, 49, 13);
	}
}
