package game;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class MainGame {

	protected Shell shlOregonTrail;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainGame window = new MainGame();
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
		shlOregonTrail.setLayout(new StackLayout());
		
		Composite card1 = formToolkit.createComposite(shlOregonTrail, SWT.NONE);
		formToolkit.paintBordersFor(card1);

	}
}
