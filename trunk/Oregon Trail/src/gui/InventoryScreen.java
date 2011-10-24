package gui;

import game.World;
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
	public boolean done = false;
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
	
	public InventoryScreen(Composite parent, int style){
		super(parent,style);
		
		itemInventory = new Item[length];
		itemInventory[0] = new Ammo();
		itemInventory[1] = new Food();
		itemInventory[2] = new Medicine();
	
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
		lblItems0wt.setText(display+itemInventory[0].getWeight());

		
		lblItems0amt = new Label(this, SWT.NONE);
		lblItems0amt.setText("00");
		
		lblItems1 = new Label(this, SWT.NONE);
		lblItems1.setText(itemInventory[1].getName());

		
		lblItems1wt = new Label(this, SWT.NONE);
		lblItems1wt.setText(display+itemInventory[1].getWeight());

		
		lblItems1amt = new Label(this, SWT.NONE);
		lblItems1amt.setText("00");

		
		lblItems2 = new Label(this, SWT.NONE);
		lblItems2.setText(itemInventory[2].getName());

		
		lblItems2wt = new Label(this, SWT.NONE);
		lblItems2wt.setText(display+itemInventory[2].getWeight());

		
		lblItems2amt = new Label(this, SWT.NONE);
		lblItems2amt.setText("00");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		btnClose = new Button(this, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				done = true;
			}
		});
		btnClose.setText("Close Inventory");
		new Label(this, SWT.NONE);
	}

	public int getLength(){
		return length;
	}
	
	public Item getItem(int x){
		return itemInventory[x];
	}
	
	public void setItemNum(int item, int num){
		itemInventory[item].setNumber(num);
	}
	
	public Item[] getItemInventory(){
		return itemInventory;
	}
	
	public void update(){
		lblItems0amt.setText(""+World.getWagon().getInventory().getItemInventory()[0].getNumber());
		lblItems1amt.setText(""+World.getWagon().getInventory().getItemInventory()[1].getNumber());
		lblItems2amt.setText(""+World.getWagon().getInventory().getItemInventory()[2].getNumber());
	}

	public boolean contains(Item i) {
		// TODO Auto-generated method stub
		for (int x=0; x<length; x++){
			if (itemInventory[x].getName()==i.getName()){
				return true;
			}
			
		}
		return false;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
