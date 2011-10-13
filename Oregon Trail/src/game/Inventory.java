package game;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Inventory {

	protected Shell shlOregonTrail;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Inventory window = new Inventory();
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

	}

}
