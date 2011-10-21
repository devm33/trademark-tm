package game;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Label;

/**
 * 
 * @author Jaron, David
 *
 */
public class MainGame {	
	public static int accessInventory = 0;
	
	private static Wagon wagon;

	public static void main(String[] args) {
		try {
			MainGame window = new MainGame();
		} catch (Exception e) {
			e.printStackTrace();
		}

		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setBounds(100, 100, 500, 400);
		
		Button btnInventory = new Button(shell, SWT.NONE);
		btnInventory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				accessInventory = 1;
			}
		});
		btnInventory.setBounds(179, 10, 75, 25);
		btnInventory.setText("Inventory");
		
		Button btnQuitGame = new Button(shell, SWT.NONE);
		btnQuitGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.dispose();
				display.dispose();
			}
		});
		btnQuitGame.setBounds(349, 10, 75, 25);
		btnQuitGame.setText("Quit Game");
		
		// create the composite that the pages will share
		final Composite contentPanel = new Composite(shell, SWT.BORDER);
		contentPanel.setBounds(0, 50, 500, 300);
		final StackLayout layout = new StackLayout();
		contentPanel.setLayout(layout);
		shell.open();

		final Config config = new Config(contentPanel, SWT.NONE);
		final Town town = new Town(contentPanel, SWT.NONE);
		final Store store = new Store(contentPanel, SWT.NONE);

		layout.topControl = config;
		
		Label lblCash = new Label(shell, SWT.NONE);
		lblCash.setText("$0");
		lblCash.setBounds(65, 15, 55, 15);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setBounds(15, 15, 44, 15);
		lblNewLabel.setText("Cash:");
		
		contentPanel.layout();
		shell.update();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
			
			
			/*Configuration Screen Continuation*/
			if (config.done == 1 && !shell.isDisposed()){
				wagon = config.getWagon();
				lblCash.setText("$"+wagon.getCash());
				config.done = 0;
				config.setVisible(false);
				town.setVisible(true);
				layout.topControl = town;
				contentPanel.layout();
				shell.update();
			}
			
			/*Town Screen Continuation*/
			if (town.choice == 2 && !shell.isDisposed()){
				town.choice = 0;
				town.setVisible(false);
				store.setVisible(true);
				layout.topControl = store;
				contentPanel.layout();
				shell.update();
			}
			
			/*Store Screen Continuation*/
			if (store.done == 1 && !shell.isDisposed()){
				store.done = 0;
				store.setVisible(false);
				town.setVisible(true);
				layout.topControl = town;
				contentPanel.layout();
				shell.update();
			}
		}
		display.dispose();
	}
}
