package game;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class MainGame {	
	
	public static void main(String[] args) {
		try {
			MainGame window = new MainGame();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setBounds(10, 10, 900, 700);
		// create the composite that the pages will share
		final Composite contentPanel = new Composite(shell, SWT.BORDER);
		contentPanel.setBounds(50, 50, 500, 500);
		final StackLayout layout = new StackLayout();
		contentPanel.setLayout(layout);
		shell.open();
		
		final Config config = new Config(contentPanel, SWT.NONE);
		final Store store = new Store(contentPanel, SWT.NONE);
		
		layout.topControl = config;
		contentPanel.layout();
		shell.update();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
			if (config.done == 1){
				config.setVisible(false);
				layout.topControl = store;
				contentPanel.layout();
				shell.update();
			}
		}
		display.dispose();
	}
}
