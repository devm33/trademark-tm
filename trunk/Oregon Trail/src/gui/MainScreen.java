package gui;

import game.Wagon;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

/**
 * And yes, everything so far is in one giant main method.
 * I know that sucks ass.
 * -Jaron
 * @author Jaron, David
 *
 */
public class MainScreen {	
	public static int accessInventory = 0;
	/*
	 * currentScreen is:
	 * 0 if Config
	 * 1 if Town
	 * 2 if Store
	 */
	public static int currentScreen = 0;
	
	private static Wagon wagon = new Wagon(0,0,3500,null,null);
	
	public static Wagon getWagon(){
		return wagon;
	}

	public static void main(String[] args) {
		
		/*
		 * BEGIN INITIALIZATION OF CONTROLS (buttons, labels, etc)
		 */
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
		
		Label lblCash = new Label(shell, SWT.NONE);
		lblCash.setText("$0");
		lblCash.setBounds(65, 15, 55, 15);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setBounds(15, 15, 44, 15);
		lblNewLabel.setText("Cash:");
		
		/*Create the composite that the pages will share*/
		final Composite contentPanel = new Composite(shell, SWT.BORDER);
		contentPanel.setBounds(0, 50, 500, 300);
		final StackLayout layout = new StackLayout();
		contentPanel.setLayout(layout);
		shell.open();

		/*Create config, town, and store screens*/
		final ConfigScreen config = new ConfigScreen(contentPanel, SWT.NONE);
		final TownScreen town = new TownScreen(contentPanel, SWT.NONE);
		final StoreScreen store = new StoreScreen(contentPanel, SWT.NONE);;
		final InventoryScreen inventory = new InventoryScreen(contentPanel, SWT.NONE);
		
		/*
		 * END INITIALIZATION OF CONTROLS
		 */

		//Put Config screen on top
		layout.topControl = config;
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
				currentScreen = 1;
				layout.topControl = town;
				contentPanel.layout();
				shell.update();
			}
			
			/*Town Screen Continuation*/
			if (town.choice == 2 && !shell.isDisposed()){
				town.choice = 0;
				town.setVisible(false);
				store.setVisible(true);
				currentScreen = 2;
				layout.topControl = store;
				contentPanel.layout();
				shell.update();
			}
			
			/*Store Screen Continuation*/
			if (store.done == 1 && !shell.isDisposed()){
				store.done = 0;
				store.setVisible(false);
				town.setVisible(true);
				currentScreen = 1;
				layout.topControl = town;
				contentPanel.layout();
				shell.update();
				
			}
			
			/*Inventory Screen Continuation*/
			if (accessInventory == 1 && !shell.isDisposed()){
				accessInventory = 0;
				
				layout.topControl.setVisible(false);
				inventory.setVisible(true);
				layout.topControl = inventory;
				layout.topControl.setVisible(true);
				contentPanel.update();
				shell.update();
			}
			if (inventory.done == 1 && !shell.isDisposed()){
				inventory.done = 0;
				inventory.setVisible(false);
				switch(currentScreen){
					case 0:
						config.setVisible(true);
						layout.topControl = config;
						break;
					case 1:
						town.setVisible(true);
						layout.topControl = town;
						break;
					case 2:
						store.setVisible(true);
						layout.topControl = store;
						break;
				}
				contentPanel.update();
				shell.update();
			}
		}
		display.dispose();
	}
}
