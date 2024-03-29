package gui;

import items.Item;
import game.Inventory;
import game.Store;
import game.World;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import exceptions.InsufficientFundsException;
import exceptions.WeightCapacityExceededException;
import org.eclipse.wb.swt.SWTResourceManager;
/**
 * Store composite
 * @author Jaron
 *
 */
public class StoreScreen extends Composite{
	private boolean done = false;
	private Text txtAmount;
	private Store currentStore;
	private List list;
	private Label lblName;
	private Label lblWeight;
	private Label lblPrice;
	private Label lblDesc;
	private Button btnPurchase;
	private Button btnExitStore;
	private Label lblAmount;
	private Label lblWagonCapacity;
	private Label lblResponse;
	
	private int lastIndex;
	
	private final String[] defaultItems = {"Oxen", "Food", "Clothing", "Ammunition", "Medicine", "Water", "Wagon Wheel", "Wagon Axle", "Wagon Tongue"};
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public StoreScreen(Composite parent, int style){
		super(parent,style);
		
		createContents();
		
		//When user selects an item in the store inventory list.
		list.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lastIndex = list.getSelectionIndex();
				display(lastIndex);
			}
		});
		
		//When user clicks the purchase button.
		btnPurchase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(lastIndex < 0)
					lblResponse.setText("You need to choose an item, fool!");
				else if(!isInteger(txtAmount.getText()))
					lblResponse.setText("Try specifying a real quantity, dummy.");
				else{
					try {
						buy(lastIndex);
					} catch (InsufficientFundsException e1) {
						lblResponse.setText("Come back when you have enough money, you bum!");
					} catch (WeightCapacityExceededException e2){
						lblResponse.setText("What in tarnations! Your wagon can't hold that!");
					}
					lblWagonCapacity.setText(World.getWagon().getTotalWeight()+"/"+World.getWagon().getCapacity());
				}
			}
		});
		
		//When user clicks the exit store button
		btnExitStore.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				done = true;
			}
		});
	}
	
	
	/**
	 * when user selects an item in the menu
	 */
	private void display(int i){
		if(i > currentStore.getInventory().getLength())
			System.out.println("problem's right ere capt'n");
		Item item = currentStore.getInventory().getItem(i);
		lblName.setText(item.getName());
		lblWeight.setText(""+item.getWeight()+" lbs");
		lblPrice.setText("$"+currentStore.getInventory().getPrice(item, currentStore.getPrices()));
		lblDesc.setText(item.getDescription());
		lblResponse.setText(item.getResponse());
	}
	
	/**
	 * when user attempts to buy an item from inventory
	 */
	private void buy(int i) throws InsufficientFundsException, WeightCapacityExceededException{
		Item item = currentStore.getInventory().getItem(i);
		currentStore.buy(item, Integer.parseInt(txtAmount.getText()));
		lblResponse.setText(item.getBoughtResponse());
	}
	
	/**
	 * Change the store of the store screen.
	 * 
	 * @param s the new store to access
	 */
	public void setStore(Store s) {
		currentStore = s;
		update();
		//createContents();
	}
	
	/**
	 * turns string into int
	 * @param input a number string
	 * @return the integer version of that number string
	 */
	private boolean isInteger(String input){  
	   try{  
	      Integer.parseInt(input);  
	      return true;  
	   }  
	   catch(Exception e){  
	      return false;  
	   }  
	} 
	
	/**
	 * check if user is exiting the store
	 * @return
	 */
	public boolean isDone(){
		return done;
	}
	
	/**
	 * reset done boolean so user can re-enter store
	 */
	public void resetDone(){
		done = false;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	
	/**
	 * update dynamic items on screen
	 */
	public void update() {
		
		lastIndex = -1;
		
		if(currentStore != null) {
			Inventory tempInv = currentStore.getInventory();
			//System.out.println(tempInv+"\n\n");
			String[] items = new String[tempInv.getLength()];
			for(int x = 0; x < tempInv.getLength(); x++) {
				items[x] = tempInv.getItem(x).getName();
			}
			list.setItems(items);
			lblResponse.setText("Welcome to "+currentStore.getName()+"!");
		}
		else
			list.setItems(defaultItems);
		lblName.setText("No Item Selected");
		lblDesc.setText("Select an item to buy from the store inventory.");
		lblWagonCapacity.setText(World.getWagon().getTotalWeight()+"/"+World.getWagon().getCapacity());
	}
	
	/**
	 * creates controls for the composite
	 */
	private void createContents(){
		
		lastIndex = -1;
		
		list = new List(this, SWT.BORDER);
		String[] items = defaultItems;
		
		if(currentStore != null) {
			Inventory tempInv = currentStore.getInventory();
			items = new String[tempInv.getLength()];
			for(int x = 0; x < tempInv.getLength(); x++) {
				items[x] = tempInv.getItem(x).getName();
			}
		}
		
		list.setItems(items);
		list.setBounds(33, 10, 141, 229);
		
		Label lbl1 = new Label(this, SWT.NONE);
		lbl1.setText("Name:");
		lbl1.setBounds(198, 45, 35, 15);
		
		Label lbl2 = new Label(this, SWT.NONE);
		lbl2.setText("Weight:");
		lbl2.setBounds(198, 67, 41, 15);
		
		Label lbl3 = new Label(this, SWT.NONE);
		lbl3.setText("Price:");
		lbl3.setBounds(198, 91, 29, 15);
		
		Label lbl4 = new Label(this, SWT.NONE);
		lbl4.setText("Description:");
		lbl4.setBounds(198, 112, 63, 15);
		
		lblName = new Label(this, SWT.NONE);
		lblName.setText("No Item Selected");
		lblName.setBounds(239, 45, 101, 15);
		
		lblWeight = new Label(this, SWT.WRAP);
		lblWeight.setBounds(245, 67, 195, 15);
		
		lblPrice = new Label(this, SWT.NONE);
		lblPrice.setBounds(233, 91, 47, 15);
		
		lblDesc = new Label(this, SWT.WRAP);
		lblDesc.setText("Select an item to buy from the store inventory.");
		lblDesc.setBounds(267, 112, 128, 57);
		
		btnPurchase = new Button(this, SWT.NONE);
		btnPurchase.setText("Purchase");
		btnPurchase.setBounds(235, 214, 60, 25);
		
		btnExitStore = new Button(this, SWT.NONE);
		btnExitStore.setText("Exit Store");
		btnExitStore.setBounds(335, 214, 60, 25);
		
		txtAmount = new Text(this, SWT.BORDER);
		txtAmount.setBounds(264, 175, 76, 21);
		
		lblAmount = new Label(this, SWT.NONE);
		lblAmount.setText("Amount:");
		lblAmount.setBounds(214, 178, 47, 15);
		
		Label lbl5 = new Label(this, SWT.NONE);
		lbl5.setBounds(198, 10, 90, 17);
		lbl5.setText("Wagon Capacity:");
		
		lblWagonCapacity = new Label(this, SWT.NONE);
		lblWagonCapacity.setBounds(294, 10, 63, 13);
		lblWagonCapacity.setText(World.getWagon().getTotalWeight()+"/"+World.getWagon().getCapacity());
		
		Label lbl6 = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		lbl6.setBounds(198, 23, 242, 16);
		
		lblResponse = new Label(this, SWT.WRAP);
		lblResponse.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblResponse.setAlignment(SWT.CENTER);
		lblResponse.setBounds(43, 249, 352, 41);
		if(currentStore != null)
			lblResponse.setText("Welcome to "+currentStore.getName()+"!");
	}
}
