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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;

/**
 * The Main game screen that holds other screens
 * @author Jaron, David
 *
 */
public class MainScreen {	

	public enum screen{
		CONFIG, TOWN, INN, STORE, WAGON, INVENTORY, MAP
	}
	public static screen currentScreen = screen.CONFIG;
	public static boolean accessInventory = false;
	public static boolean accessWagon = false;
	public static boolean accessMap = false;
	
	private Wagon wagon;
	
	private Display display;
	private Shell shell;
	private Image icon;
	private Label lblCash;
	private Label lblDate;
	private Label lblDay;
	private Label lbl1;
	private Label lbl2;
	private Button btnInventory;
	private Button btnMap;
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
		btnMap.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		
		shell.open();

		/*Create config, town, store, inventory, and wagonView screens*/
		createScreens();

		/*Put Config screen on top*/
		layout.topControl = config;
		contentPanel.layout();
		shell.update();
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

		if (!shell.isDisposed()){
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
			
			/*Update items in inventory and player cash*/
			updateInventoryAndCash();
			
			contentPanel.update();
			shell.update();
		}
		
		return !shell.isDisposed();
	}
	
	private void updateInventoryAndCash(){
		
		inventory.update();
		if(World.getWagon().getLeader()!=null && World.getWagon().getCash()!=null){
		lblCash.setText("$"+World.getWagon().getCash());
		}
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
			currentScreen = screen.TOWN;
			layout.topControl = town;
			contentPanel.layout();
			shell.update();
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
			currentScreen = screen.STORE;
			layout.topControl = store;
			contentPanel.layout();
			shell.update();
		}
		//Player chooses LEAVE TOWN
		else if(town.choice == 3){
			town.choice = 0;
			town.setVisible(false);
			wagonView.setVisible(true);
			wagonView.update();
			currentScreen = screen.WAGON;
			layout.topControl = wagonView;
			contentPanel.layout();
			shell.update();
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
			currentScreen = screen.TOWN;
			layout.topControl = town;
			contentPanel.layout();
			shell.update();
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
			shell.update();
		}
		if (inventory.done){
			inventory.done = false;
			inventory.setVisible(false);
			switch(currentScreen){
			case CONFIG:
				/*if previous screen was config*/
				config.setVisible(true);
				layout.topControl = config;
				break;
			case TOWN:
				/*if previous screen was town*/
				town.setVisible(true);
				layout.topControl = town;
				break;
			case STORE:
				/*if previous screen was store*/
				store.setVisible(true);
				layout.topControl = store;
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
			shell.update();
		}
		if (wagonView.done){
			wagonView.done = false;
			wagonView.setVisible(false);
			switch(currentScreen){
			case CONFIG:
				/*if previous screen was config*/
				config.setVisible(true);
				layout.topControl = config;
				break;
			case TOWN:
				/*if previous screen was town*/
				town.setVisible(true);
				layout.topControl = town;
				break;
			case STORE:
				/*if previous screen was store*/
				store.setVisible(true);
				layout.topControl = store;
				break;
			}
		}
	}
	
	private void continueMap(){
		
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
		shell = new Shell(display);
		shell.setSize(470, 390);
		shell.setText("Oregon Trail");
		shell.setBounds(400, 200, 470, 390);
		icon = new Image(display,getClass().getResourceAsStream("images/icon.ico"));
		shell.setImage(icon);

		lblCash = new Label(shell, SWT.NONE);
		lblCash.setText("$0");
		lblCash.setBounds(117, 29, 55, 15);
		
		lblDate = new Label(shell, SWT.NONE);
		lblDate.setText("May 32, 2658");
		lblDate.setBounds(10, 8, 131, 15);
		
		lblDay = new Label(shell, SWT.NONE);
		lblDay.setText("123");
		lblDay.setBounds(39, 29, 37, 15);

		lbl1 = new Label(shell, SWT.NONE);
		lbl1.setAlignment(SWT.RIGHT);
		lbl1.setBounds(67, 29, 44, 15);
		lbl1.setText("Cash:");
		
		lbl2 = new Label(shell, SWT.NONE);
		lbl2.setBounds(10, 29, 23, 15);
		lbl2.setText("Day:");

		btnInventory = new Button(shell, SWT.NONE);
		btnInventory.setBounds(297, 23, 75, 25);
		btnInventory.setText("Inventory");
		btnInventory.setEnabled(false);
		
		btnMap = new Button(shell, SWT.NONE);
		btnMap.setBounds(377, 23, 75, 25);
		btnMap.setText("Map");

		btnWagon = new Button(shell, SWT.NONE);
		btnWagon.setBounds(223, 23, 68, 25);
		btnWagon.setText("Wagon");
		btnWagon.setEnabled(false);
		
		/*Create the composite that the pages will share*/
		contentPanel = new Composite(shell, SWT.BORDER);
		contentPanel.setBounds(2, 50, 450, 300);
		
		layout = new StackLayout();
		contentPanel.setLayout(layout);
	}
}
