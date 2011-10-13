package m5;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Store {

	protected Shell shlStoer;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Store window = new Store();
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
		shlStoer.open();
		shlStoer.layout();
		while (!shlStoer.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlStoer = new Shell();
		shlStoer.setSize(450, 300);
		shlStoer.setText("Oregon Trail");

	}

}
