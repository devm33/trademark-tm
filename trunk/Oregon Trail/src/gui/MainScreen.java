package gui;

import game.Store;
import game.Wagon;
import game.World;

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
	private Shell shlOregonTrail;
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
	private WagonScreen wagonView;
	private Wagon wagon;

	public MainScreen() {
		wagon = World.getWagon();
		/*
		 * BEGIN INITIALIZATION OF CONTROLS (buttons, labels, etc)
		 */
		display = new Display();
		shlOregonTrail = new Shell(display);
		shlOregonTrail.setText("Oregon Trail");
		shlOregonTrail.setBounds(100, 100, 500, 400);

		lblCash = new Label(shlOregonTrail, SWT.NONE);
		lblCash.setText("$0");
		lblCash.setBounds(65, 15, 55, 15);

		lbl1 = new Label(shlOregonTrail, SWT.NONE);
		lbl1.setAlignment(SWT.RIGHT);
		lbl1.setBounds(15, 15, 44, 15);
		lbl1.setText("Cash:");

		btnInventory = new Button(shlOregonTrail, SWT.NONE);
		btnInventory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				accessInventory = true;
			}
		});
		btnInventory.setBounds(179, 10, 75, 25);
		btnInventory.setText("Inventory");

		btnQuitGame = new Button(shlOregonTrail, SWT.NONE);
		btnQuitGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlOregonTrail.dispose();
				display.dispose();
			}
		});
		btnQuitGame.setBounds(349, 10, 75, 25);
		btnQuitGame.setText("Quit Game");

		/*Create the composite that the pages will share*/
		contentPanel = new Composite(shlOregonTrail, SWT.BORDER);
		contentPanel.setBounds(0, 50, 500, 300);

		/*
		 * END INITIALIZATION OF CONTROLS
		 */

		layout = new StackLayout();
		contentPanel.setLayout(layout);
		shlOregonTrail.open();

		/*Create config, town, and store screens*/
		config = new ConfigScreen(contentPanel, SWT.NONE);
		town = new TownScreen(contentPanel, SWT.NONE);
		store = new StoreScreen(contentPanel, SWT.NONE);
		wagonView = new WagonScreen(contentPanel,SWT.NONE); 
		inventory = new InventoryScreen(contentPanel, SWT.NONE);

		/*Put Config screen on top*/
		layout.topControl = config;
		contentPanel.layout();
		shlOregonTrail.update();
	}

	public boolean stepGame(){
		if (!display.readAndDispatch())
			display.sleep();

		if (!shlOregonTrail.isDisposed()){
			/*Configuration Screen Continuation*/
			if (config.done){
				lblCash.setText("$"+wagon.getCash());
				config.done = false;
				config.setVisible(false);
				town.setVisible(true);
				currentScreen = 1;
				layout.topControl = town;
				contentPanel.layout();
				shlOregonTrail.update();
			}

			/*Town Screen Continuation*/
			if (town.choice == 2){
				town.choice = 0;
				town.setVisible(false);
				store.setVisible(true);
				currentScreen = 2;
				layout.topControl = store;
				contentPanel.layout();
				shlOregonTrail.update();
			}
			else if(town.choice == 3){
				town.choice = 0;
				wagonView.setVisible(false);
				wagonView.setVisible(true);
				currentScreen = 3;
				layout.topControl = wagonView;
				contentPanel.layout();
				shlOregonTrail.update();
			}

			/*Store Screen Continuation and Update*/
			if (store.done){
				store.done = false;
				store.setVisible(false);
				town.setVisible(true);
				currentScreen = 1;
				layout.topControl = town;
				contentPanel.layout();
				shlOregonTrail.update();
			}
			if (store.needUpdate){
				store.needUpdate = false;
				inventory.update();
				lblCash.setText("$"+World.getWagon().getCash());
			}

			/*Inventory Screen Continuation*/
			if (accessInventory){
				accessInventory = false;
				layout.topControl.setVisible(false);
				inventory.setVisible(true);
				layout.topControl = inventory;
				layout.topControl.setVisible(true);
				contentPanel.update();
				shlOregonTrail.update();
			}
			if (inventory.done){
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
			}
			contentPanel.update();
			shlOregonTrail.update();
		}
		return !shlOregonTrail.isDisposed();
	}

	public void disposeDisplay(){
		display.dispose();
	}

	public void setStore(Store s){
		store.setStore(s);
	}
}
