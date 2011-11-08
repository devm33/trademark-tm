package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;

/**
 * River crossing choice/minigame
 * @author Jaron
 *
 */
public class RiverScreen extends Composite {
	private boolean done = false;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RiverScreen(Composite parent, int style) {
		super(parent, style);
		
		createContents();
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
		
		Canvas canvas = new Canvas(this, SWT.NONE);
		canvas.setBounds(271, 10, 169, 255);
		
		Label lblWeNeedTo = new Label(this, SWT.NONE);
		lblWeNeedTo.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		lblWeNeedTo.setBounds(24, 40, 231, 25);
		lblWeNeedTo.setText("we need to cross the river!");
		
		Label lblWhatWeGonna = new Label(this, SWT.NONE);
		lblWhatWeGonna.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		lblWhatWeGonna.setBounds(24, 64, 222, 25);
		lblWhatWeGonna.setText("What we gonna do?!?");
		
		ComboViewer comboViewer = new ComboViewer(this, SWT.NONE);
		Combo combo = comboViewer.getCombo();
		combo.setItems(new String[] {"Take Ferry", "Ford", "Caulk"});
		combo.setBounds(80, 133, 92, 21);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(24, 168, 49, 13);
		lblNewLabel.setText("New Label");
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		btnNewButton.setBounds(80, 231, 78, 34);
		btnNewButton.setText("CROSS");
	}
}
