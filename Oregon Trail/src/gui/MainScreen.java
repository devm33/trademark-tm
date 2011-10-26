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
	 * 3 if current screen is Wagon Screen
	 * 4 if current screen is Inventory Screen
	 */
	public static int currentScreen = 0;
	public static boolean accessInventory = false;
	public static boolean accessWagon = false;
	
	private Wagon wagon;
	
	private Display display;
	private Shell shlOregonTrail;
	private Label lblCash;
	private Label lblDate;
	private Label lblDay;
	private Label lbl1;
	private Label lbl2;
	private Button btnInventory;
	private Button btnQuitGame;
	private Button btnWagon;
	
	private Composite contentPanel;
	private StackLayout layout;
	
	private ConfigScreen config;
	private TownScreen town;
	private StoreScreen store;
	private InventoryScreen inventory;
	private WagonScreen wagonView;

	public MainScreen() {
		wagon = World.getWagon();

		createContents();
		
		//Logic when user clicks the Inventory button
		btnInventory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				accessInventory = true;
			}
		});
		
		//Logic when user clicks the Wagon button
		btnWagon.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0){
				accessWagon = true;
			}
		});
		
		//Logic when user clicks the Quit Game button
		btnQuitGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlOregonTrail.dispose();
				display.dispose();
			}
		});
		
		shlOregonTrail.open();

		/*Create config, town, store, inventory, and wagonView screens*/
		createScreens();

		/*Put Config screen on top*/
		layout.topControl = config;
		contentPanel.layout();
		shlOregonTrail.update();
	}
	
	private void createScreens(){
		config = new ConfigScreen(contentPanel, SWT.NONE);
		town = new TownScreen(contentPanel, SWT.NONE);
		store = new StoreScreen(contentPanel, SWT.NONE);
		inventory = new InventoryScreen(contentPanel, SWT.NONE);
		wagonView = new WagonScreen(contentPanel,SWT.NONE); 
	}

	public boolean stepGame(){
		if (!display.readAndDispatch())
			display.sleep();

		if (!shlOregonTrail.isDisposed()){
			/*Configuration Screen Continuation*/
			continueConfig();

			/*Town Screen Continuation*/
			continueTown();

			/*Store Screen Continuation and Update*/
			continueStore();

			/*Inventory Screen Continuation*/
			continueInventory();
			
			/*Wagon Screen Continuation*/
			continueWagon();
			
			contentPanel.update();
			shlOregonTrail.update();
		}
		return !shlOregonTrail.isDisposed();
	}
	
	/**
	 * logic for handling post-config screens
	 */
	private void continueConfig(){
		if (config.done){
			btnInventory.setEnabled(true);
			btnWagon.setEnabled(true);
			lblCash.setText("$"+wagon.getCash());
			config.done = false;
			config.setVisible(false);
			town.setVisible(true);
			currentScreen = 1;
			layout.topControl = town;
			contentPanel.layout();
			shlOregonTrail.update();
		}
	}
	
	/**
	 * logic for handling post-town screens
	 */
	private void continueTown(){
		//Player chooses INN
		if(town.choice == 1){
			//INN LOGIC
		}
		//Player chooses STORE
		else if(town.choice == 2){
			town.choice = 0;
			town.setVisible(false);
			store.setVisible(true);
			currentScreen = 2;
			layout.topControl = store;
			contentPanel.layout();
			shlOregonTrail.update();
		}
		//Player chooses LEAVE TOWN
		else if(town.choice == 3){
			town.choice = 0;
			town.setVisible(false);
			wagonView.setVisible(true);
			wagonView.update();
			currentScreen = 3;
			layout.topControl = wagonView;
			contentPanel.layout();
			shlOregonTrail.update();
		}
	}
	
	/**
	 * logic for handling post-store screens and updating cash for purchases
	 */
	private void continueStore(){
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
	}
	
	/**
	 * logic for handling which screen is shown upon opening/closing inventory
	 */
	private void continueInventory(){
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
				/*if previous screen was config*/
				config.setVisible(true);
				layout.topControl = config;
				break;
			case 1:
				/*if previous screen was town*/
				town.setVisible(true);
				layout.topControl = town;
				break;
			case 2:
				/*if previous screen was store*/
				store.setVisible(true);
				layout.topControl = store;
				break;
			case 3:
				/*if previous screen was wagon view*/
				wagonView.setVisible(true);
				layout.topControl = wagonView;
				break;
			}
		}
	}
	
	private void continueWagon(){
		if (accessWagon){
			accessWagon = false;
			layout.topControl.setVisible(false);
			wagonView.setVisible(true);
			layout.topControl = wagonView;
			layout.topControl.setVisible(true);
			contentPanel.update();
			shlOregonTrail.update();
		}
		if (wagonView.done){
			wagonView.done = false;
			wagonView.setVisible(false);
			switch(currentScreen){
			case 0:
				/*if previous screen was config*/
				config.setVisible(true);
				layout.topControl = config;
				break;
			case 1:
				/*if previous screen was town*/
				town.setVisible(true);
				layout.topControl = town;
				break;
			case 2:
				/*if previous screen was store*/
				store.setVisible(true);
				layout.topControl = store;
				break;
			case 4:
				/*if previous screen was inventory*/
				inventory.setVisible(true);
				layout.topControl = inventory;
				break;
			}
		}
	}
	
	public void disposeDisplay(){
		display.dispose();
	}

	public void setStore(Store s){
		store.setStore(s);
	}
	
	/**
	 * create display, shell, and controls
	 */
	private void createContents(){
		display = new Display();
		shlOregonTrail = new Shell(display);
		shlOregonTrail.setSize(646, 402);
		shlOregonTrail.setText("Oregon Trail");
		shlOregonTrail.setBounds(100, 100, 500, 400);

		lblCash = new Label(shlOregonTrail, SWT.NONE);
		lblCash.setText("$0");
		lblCash.setBounds(223, 8, 55, 15);
		
		lblDate = new Label(shlOregonTrail, SWT.NONE);
		lblDate.setText("May 1, 1848");
		lblDate.setBounds(10, 8, 131, 15);
		
		lblDay = new Label(shlOregonTrail, SWT.NONE);
		lblDay.setText("32");
		lblDay.setBounds(39, 29, 37, 15);

		lbl1 = new Label(shlOregonTrail, SWT.NONE);
		lbl1.setAlignment(SWT.RIGHT);
		lbl1.setBounds(173, 8, 44, 15);
		lbl1.setText("Cash:");
		
		lbl2 = new Label(shlOregonTrail, SWT.NONE);
		lbl2.setBounds(10, 29, 23, 15);
		lbl2.setText("Day:");

		btnInventory = new Button(shlOregonTrail, SWT.NONE);
		btnInventory.setBounds(224, 24, 75, 25);
		btnInventory.setText("Inventory");
		btnInventory.setEnabled(false);
		
		btnQuitGame = new Button(shlOregonTrail, SWT.NONE);
		btnQuitGame.setBounds(399, 24, 75, 25);
		btnQuitGame.setText("Quit Game");

		btnWagon = new Button(shlOregonTrail, SWT.NONE);
		btnWagon.setBounds(150, 24, 68, 25);
		btnWagon.setText("Wagon");
		btnWagon.setEnabled(false);
		
		/*Create the composite that the pages will share*/
		contentPanel = new Composite(shlOregonTrail, SWT.BORDER);
		contentPanel.setBounds(0, 50, 500, 300);
		
		layout = new StackLayout();
		contentPanel.setLayout(layout);
	}
}
