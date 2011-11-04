package gui;

import items.Food;
import exceptions.WeightCapacityExceededException;
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
		CONFIG, TOWN, INN, STORE, FIELD, HUNT, RIVER
	}
	public static screen currentScreen = screen.CONFIG;
	
	public static boolean accessInventory = false;
	public static boolean accessWagon = false;
	public static boolean accessMap = false;
	public static boolean accessHunt = false;
	public static boolean accessRiver = false;
	public static boolean win = false;
	public static boolean lose = false;
	private static boolean newGame = false;
	
	private static boolean Townstate = true;
	
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
	private InnScreen inn;
	private StoreScreen store;
	private FieldScreen field;
	private InventoryScreen inventory;
	private WagonScreen wagonView;
	private MapScreen map;
	private HuntingScreen hunt;
	private RiverScreen river;
	private WinScreen winView;
	private LoseScreen loseView;

	public MainScreen() {
		wagon = World.getWagon();

		createContents();		
		shell.open();

		createScreens();

		layout.topControl = config;
		
		Button btnSuper = new Button(shell, SWT.NONE);
		btnSuper.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				World.getWagon().setCapacity(99999999);
				try {
					World.getWagon().addToInventory(new Food(), 99999);
				} catch (WeightCapacityExceededException e) {
					// TODO Auto-generated catch block
				}
			}
		});
		btnSuper.setBounds(147, 3, 75, 25);
		btnSuper.setText("super");
		contentPanel.layout();
		shell.update();
	}

	public boolean stepGame(){
		if (!display.readAndDispatch())
			display.sleep();

		if (!shell.isDisposed()){
			/*Screen Continuation*/
		
			continueConfig();
			continueTown();
			continueInn();
			continueStore();
			continueField();
			continueInventory();
			continueWagon();
			continueMap();
			continueHunting();
			continueRiver();
			continueWin();
			continueLose();
		
			updateCash();
			updateWagon();
			updateInventory();
			updateMap();
			
			refresh();
		}
		
		return !shell.isDisposed();
	}
	
	private void createScreens(){
		config = new ConfigScreen(contentPanel, SWT.NONE);
		town = new TownScreen(contentPanel, SWT.NONE);
		inn = new InnScreen(contentPanel, SWT.NONE);
		store = new StoreScreen(contentPanel, SWT.NONE);
		field = new FieldScreen(contentPanel, SWT.NONE);
		inventory = new InventoryScreen(contentPanel, SWT.NONE);
		wagonView = new WagonScreen(contentPanel,SWT.NONE);
		map = new MapScreen(contentPanel,SWT.NONE);
		hunt = new HuntingScreen(contentPanel,SWT.NONE);
		river = new RiverScreen(contentPanel,SWT.NONE);
		winView = new WinScreen(contentPanel,SWT.NONE);
		loseView = new LoseScreen(contentPanel,SWT.NONE);
	}
	
	private void updateCash(){
		if(World.getWagon().getLeader()!=null && World.getWagon().getCash()!=null){
		lblCash.setText("$"+World.getWagon().getCash());
		}
	}
	
	private void updateWagon(){
		if(World.getWagon().getLeader()!=null)
			wagonView.update();
	}
	
	private void updateInventory(){
		inventory.update();
	}
	
	private void updateMap(){
		map.update();
	}
	
	/**
	 * logic for handling post-config screens
	 */
	private void continueConfig(){
		if (config.isDone()){
			btnInventory.setEnabled(true);
			btnWagon.setEnabled(true);
			btnMap.setEnabled(true);
			config.resetDone();
			screenTransition(config, town);
			currentScreen = screen.TOWN;
			field.update();
		}
	}
	
	/**
	 * logic for handling post-town screens
	 */
	private void continueTown(){
		//Player chooses INN
		if(town.getChoice() == 1){
			town.resetChoice();
			screenTransition(town,inn);
			currentScreen = screen.INN;
		}
		//Player chooses STORE
		else if(town.getChoice() == 2){
			town.resetChoice();
			screenTransition(town,store);
			currentScreen = screen.STORE;
		}
		//Player chooses LEAVE TOWN
		else if(town.getChoice() == 3){
			Townstate=false;
			town.resetChoice();
			screenTransition(town,field);
			currentScreen = screen.FIELD;
		}
	}
	
	/**
	 * logic for handling post-inn screens
	 */
	private void continueInn(){
		if (inn.isDone()){
			inn.resetDone();
			screenTransition(inn,town);
			currentScreen = screen.TOWN;
		}
	}
	
	/**
	 * logic for handling post-store screens and updating cash for purchases
	 */
	private void continueStore(){
		if (store.isDone()){
			store.resetDone();
			screenTransition(store,town);
			currentScreen = screen.TOWN;
		}
	}
	
	/**
	 * logic for handling post-Field screens
	 */
	private void continueField(){
		if (accessHunt){
			accessHunt = false;
		}
		if (accessRiver){
			accessRiver = false;
		}
		if (Townstate){
		}
	}
	
	/**
	 * logic for handling which screen is shown upon opening/closing inventory
	 */
	private void continueInventory(){
		if (accessInventory){
			accessInventory = false;
			screenTransition(getCurrentComposite(),inventory);
		}
		if (inventory.isDone()){
			inventory.resetDone();
			screenTransition(inventory,getCurrentComposite());
		}
	}
	
	private void continueWagon(){
		if (accessWagon){
			accessWagon = false;
			screenTransition(getCurrentComposite(),wagonView);
		}
		if (wagonView.isDone()){
			wagonView.resetDone();
			screenTransition(wagonView,getCurrentComposite());
		}
	}
	
	/**
	 * logic for handling post-map screens
	 */
	private void continueMap(){
		if (accessMap){
			accessMap = false;
			screenTransition(getCurrentComposite(),map);
		}
		if (map.isDone()){
			map.resetDone();
			screenTransition(map,getCurrentComposite());
		}
	}
	
	/**
	 * logic for handling post-hunting screens
	 */
	private void continueHunting(){
		if(hunt.isDone()){
			hunt.resetDone();
		}
	}
	
	/**
	 * logic for handling post-river screens
	 */
	private void continueRiver(){
		if(river.isDone()){
			river.resetDone();
		}
	}
	
	/**
	 * logic for handling post-win screens (either new game or quit)
	 */
	private void continueWin(){
		
	}
	
	/**
	 * logic for handling post-lose screens (either new game or quit)
	 */
	private void continueLose(){
		
	}
	
	private void screenTransition(Composite oldScreen, Composite newScreen){
		map.setVisible(false);
		inventory.setVisible(false);
		wagonView.setVisible(false);
		oldScreen.setVisible(false);
		newScreen.setVisible(true);
		layout.topControl = newScreen;
	}
	
	private void refresh(){
		contentPanel.update();
		shell.update();
	}
	
	public void disposeDisplay(){
		display.dispose();
	}

	public void setStore(Store s){
		store.setStore(s);
	}
	
	private Composite getCurrentComposite(){
		Composite comp;
		switch(currentScreen){
		case CONFIG:
			comp = config;
			break;
		case TOWN:
			comp = town;
			break;
		case INN:
			comp = inn;
			break;
		case STORE:
			comp = store;
			break;
		case FIELD:
			comp = field;
			break;
		case HUNT:
			comp = hunt;
			break;
		case RIVER:
			comp = river;
			break;
		default:
			comp = config;
			break;
		}
		return comp;
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
		lblDate.setText("May 1, 1984");
		lblDate.setBounds(10, 8, 131, 15);
		
		lblDay = new Label(shell, SWT.NONE);
		lblDay.setText("1");
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
		//Logic when user clicks the Inventory button
		btnInventory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				accessInventory = true;
			}
		});
		
		btnMap = new Button(shell, SWT.NONE);
		btnMap.setEnabled(false);
		btnMap.setBounds(377, 23, 75, 25);
		btnMap.setText("Map");
		//Logic when user clicks the Map button
		btnMap.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				accessMap = true;
			}
		});

		btnWagon = new Button(shell, SWT.NONE);
		btnWagon.setBounds(223, 23, 68, 25);
		btnWagon.setText("Wagon");
		btnWagon.setEnabled(false);
		//Logic when user clicks the Wagon button
		btnWagon.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0){
				accessWagon = true;
			}
		});
		
		/*Create the composite that the pages will share*/
		contentPanel = new Composite(shell, SWT.BORDER);
		contentPanel.setBounds(2, 50, 450, 300);
		
		layout = new StackLayout();
		contentPanel.setLayout(layout);
	}
	
	public boolean inTown(){
		return Townstate;
	}
}
