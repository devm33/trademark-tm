package gui;

import game.World;
import game.Inventory;
import items.Ammo;
import items.Food;
import items.Item;
import items.Medicine;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * Inventory class to hold and display Items
 * 
 * @author Stephen Bentley
 */
public class InventoryScreen extends Composite{
	private boolean done = false;
	private Item[]itemInventory;
	private int length = 6;
	
	private Label lblItem;
	private Label lblWeight;
	private Label lblAmmount;
	private Label lblItems0;
	private Label lblItems0wt;
	private Label lblItems0amt;
	private Label lblItems1;
	private Label lblItems1wt;
	private Label lblItems1amt;
	private Label lblItems2;
	private Label lblItems2wt;
	private Label lblItems2amt;
	private Button btnClose;
	private String display="";
	
	/**
	 * Creates a new inventory screen and inventory items
	 * @param parent
	 * @param style
	 */
	public InventoryScreen(Composite parent, int style){
		super(parent,style);
		
		itemInventory = new Item[length];
		itemInventory[0] = new Ammo();
		//itemInventory[1] = new Axle();
		//itemInventory[2] = new Clothing();
		itemInventory[1] = new Food();
		itemInventory[2] = new Medicine();
		//itemInventory[5] = new Oxen();
		//itemInventory[6] = new Tongue();
		//itemInventory[7] = new Water();
		//itemInventory[8] = new Wheel();
		createContents();
		
		//Logic when user clicks the Close Inventory button
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				done = true;
			}
		});
	}
	
	/**
	 * updates the item names, weight, and amounts
	 */
	public void update(){
		
		int inventoryLength = World.getWagon().getInventory().getLength();
		Inventory inv = World.getWagon().getInventory();
		int[] amounts = new int[inventoryLength];
		int[] weights = new int[inventoryLength];
		
		for(int i=0; i<inventoryLength; i++)
		{
			amounts[i] = inv.getItemInventory()[i].getNumber();
			weights[i] = inv.getItemInventory()[i].getWeight();
		}
			
			lblItems0wt.setText(""+weights[0]*amounts[0]);
			lblItems1wt.setText(""+weights[1]*amounts[1]);
			lblItems2wt.setText(""+weights[2]*amounts[2]);
			lblItems0amt.setText(""+amounts[0]);
			lblItems1amt.setText(""+amounts[1]);
			lblItems2amt.setText(""+amounts[2]);
	}

	/**
	 * check if inventory contains a certain item
	 * @param i the item to check for
	 * @return if the item exists in inventory
	 */
	public boolean contains(Item i) {
		for (int x=0; x<length; x++){
			if (itemInventory[x].getName()==i.getName()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * returns capacity of inventory
	 * @return
	 */
	public int getLength(){
		return length;
	}
	
	/**
	 * returns the item at index x
	 * @param x the index of the item to return
	 * @return the item at index x
	 */
	public Item getItem(int x){
		return itemInventory[x];
	}
	
	/**
	 * sets quantity of an item in inventory
	 * @param item the item index
	 * @param num the new number of the item
	 */
	public void setItemNum(int item, int num){
		itemInventory[item].setNumber(num);
	}
	
	/**
	 * returns an array of the Items in the inventory
	 * @return
	 */
	public Item[] getItemInventory(){
		return itemInventory;
	}
	
	/**
	 * check if user is finished with inventory screen
	 * @return
	 */
	public boolean isDone(){
		return done;
	}
	
	/**
	 * reset done variable so user can re-enter inventory screen
	 */
	public void resetDone(){
		done = false;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	/**
	 * creates controls for the composite
	 */
	private void createContents(){
		this.setLayout(new GridLayout(3, true));
		
		lblItem = new Label(this, SWT.NONE);
		lblItem.setText("Item                ");
		
		lblWeight = new Label(this, SWT.NONE);
		lblWeight.setText("Weight");

		
		lblAmmount = new Label(this, SWT.NONE);
		lblAmmount.setText("Amount");
		

		lblItems0 = new Label(this, SWT.NONE);
		lblItems0.setText(itemInventory[0].getName());

		
		lblItems0wt = new Label(this, SWT.NONE);
		lblItems0wt.setText("0000");

		
		lblItems0amt = new Label(this, SWT.NONE);
		lblItems0amt.setText("0000");
		
		lblItems1 = new Label(this, SWT.NONE);
		lblItems1.setText(itemInventory[1].getName());

		
		lblItems1wt = new Label(this, SWT.NONE);
		lblItems1wt.setText("0000");

		
		lblItems1amt = new Label(this, SWT.NONE);
		lblItems1amt.setText("0000");

		
		lblItems2 = new Label(this, SWT.NONE);
		lblItems2.setText(itemInventory[2].getName());

		
		lblItems2wt = new Label(this, SWT.NONE);
		lblItems2wt.setText("0000");

		
		lblItems2amt = new Label(this, SWT.NONE);
		lblItems2amt.setText("0000");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		btnClose = new Button(this, SWT.NONE);
		btnClose.setText("Close Inventory");
		new Label(this, SWT.NONE);
	}
}
