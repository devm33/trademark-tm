package game;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Town {

	protected Shell shlOregonTrail;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Town window = new Town();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlOregonTrail.open();
		shlOregonTrail.layout();
		while (!shlOregonTrail.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlOregonTrail = new Shell();
		shlOregonTrail.setSize(450, 300);
		shlOregonTrail.setText("Oregon Trail");
		
		Button btnInn = new Button(shlOregonTrail, SWT.NONE);
		btnInn.setBounds(10, 240, 122, 23);
		btnInn.setText("Inn ");
		
		Button btnStore = formToolkit.createButton(shlOregonTrail, "Store", SWT.NONE);
		btnStore.setBounds(174, 240, 122, 23);
		
		Button btnLeaveTown = formToolkit.createButton(shlOregonTrail, "Leave Town", SWT.NONE);
		btnLeaveTown.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnLeaveTown.setBounds(323, 240, 109, 23);
		
		Label lblTown = new Label(shlOregonTrail, SWT.NONE);
		lblTown.setBounds(10, 10, 49, 13);
		formToolkit.adapt(lblTown, true, true);
		lblTown.setText("TOWN");

	}
}
