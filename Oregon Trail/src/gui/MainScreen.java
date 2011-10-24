package gui;

import game.Store;
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
 * The Main game screen that holds other screens
 * @author Jaron, David
 *
 */
public class MainScreen {	
	/*
	 * currentScreen is:
	 * 0 if current screen is Configuration Screen
	 * 1 if current screen is Town Screen
	 * 2 if current screen is Store Screen
	 */
	public static int currentScreen = 0;
	public static boolean accessInventory = false;
	
	private Display display;
	private Shell shell;
	private Label lblCash;
	private Label lbl1;
	private Button btnInventory;
	private Button btnQuitGame;
	private Composite contentPanel;
	private StackLayout layout;
	private ConfigScreen config;
	private TownScreen town;
	private StoreScreen store;
	private InventoryScreen inventory;
	private Wagon wagon;

	public MainScreen(Wagon wagon) {
		this.wagon = wagon;
		/*
		 * BEGIN INITIALIZATION OF CONTROLS (buttons, labels, etc)
		 */
		display = new Display();
		shell = new Shell(display);
		shell.setBounds(100, 100, 500, 400);
		
		lblCash = new Label(shell, SWT.NONE);
		lblCash.setText("$0");
		lblCash.setBounds(65, 15, 55, 15);
		
		lbl1 = new Label(shell, SWT.NONE);
		lbl1.setAlignment(SWT.RIGHT);
		lbl1.setBounds(15, 15, 44, 15);
		lbl1.setText("Cash:");
		
		btnInventory = new Button(shell, SWT.NONE);
		btnInventory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				accessInventory = true;
			}
		});
		btnInventory.setBounds(179, 10, 75, 25);
		btnInventory.setText("Inventory");
		
		btnQuitGame = new Button(shell, SWT.NONE);
		btnQuitGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.dispose();
				display.dispose();
			}
		});
		btnQuitGame.setBounds(349, 10, 75, 25);
		btnQuitGame.setText("Quit Game");
		
		/*Create the composite that the pages will share*/
		contentPanel = new Composite(shell, SWT.BORDER);
		contentPanel.setBounds(0, 50, 500, 300);
		
		/*
		 * END INITIALIZATION OF CONTROLS
		 */
		
		layout = new StackLayout();
		contentPanel.setLayout(layout);
		shell.open();

		/*Create config, town, and store screens*/
		config = new ConfigScreen(contentPanel, SWT.NONE, wagon);
		town = new TownScreen(contentPanel, SWT.NONE);
		store = new StoreScreen(contentPanel, SWT.NONE);;
		inventory = new InventoryScreen(contentPanel, SWT.NONE);
		
		/*Put Config screen on top*/
		layout.topControl = config;
		contentPanel.layout();
		shell.update();
	}
	
	public boolean stepGame(){
		if (!display.readAndDispatch())
			display.sleep();
		
		/*Configuration Screen Continuation*/
		if (config.done && !shell.isDisposed()){
			lblCash.setText("$"+wagon.getCash());
			config.done = false;
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
		if (store.done && !shell.isDisposed()){
			store.done = false;
			store.setVisible(false);
			town.setVisible(true);
			currentScreen = 1;
			layout.topControl = town;
			contentPanel.layout();
			shell.update();
			
		}
		
		/*Inventory Screen Continuation*/
		if (accessInventory && !shell.isDisposed()){
			accessInventory = false;
			layout.topControl.setVisible(false);
			inventory.setVisible(true);
			layout.topControl = inventory;
			layout.topControl.setVisible(true);
			contentPanel.update();
			shell.update();
		}
		if (inventory.done && !shell.isDisposed()){
			inventory.done = false;
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
		return !shell.isDisposed();
	}
	
	public void disposeDisplay(){
		display.dispose();
	}
	
	public void setStore(Store s){
		store.setStore(s);
	}
}
