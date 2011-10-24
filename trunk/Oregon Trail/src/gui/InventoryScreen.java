package gui;

/**
 * Inventory class to hold and display Items
 * 
 * @author Stephen Bentley
 */

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

public class InventoryScreen extends Composite{
	public int done = 0;
	private Item[]itemInventory;
	private int length = 6;
	
/*	public Inventory(){
		itemInventory = new Item[length];
		itemInventory[0] = new Ammo();
		itemInventory[1] = new Food();
		itemInventory[2] = new Water();
	}
	
	public Inventory(Item item0,Item item1, Item item2){
		itemInventory = new Item[length];
		itemInventory[0] = item0;
		itemInventory[1] = item1;
		itemInventory[2] = item2;
	}
	
	public Inventory(Item item0,Item item1, Item item2, Item item3, Item item4){
		itemInventory = new Item[length];
		itemInventory[0] = item0;
		itemInventory[1] = item1;
		itemInventory[2] = item2;
		itemInventory[3] = item3;
		itemInventory[4] = item4;
	}*/

	public InventoryScreen(Composite parent, int style){
		super(parent,style);
		
		itemInventory = new Item[length];
		itemInventory[0] = new Ammo();
		itemInventory[1] = new Food();
		itemInventory[2] = new Medicine();
	
		this.setLayout(new GridLayout(3, true));
		String display="";
		
		Label lblItem = new Label(this, SWT.NONE);
		lblItem.setText("Item                ");
		
		Label lblWeight = new Label(this, SWT.NONE);
		lblWeight.setText("Weight");

		
		Label lblAmmount = new Label(this, SWT.NONE);
		lblAmmount.setText("Amount");
		

		Label lblItems0 = new Label(this, SWT.NONE);
		lblItems0.setText(itemInventory[0].getName());

		
		Label lblItems0wt = new Label(this, SWT.NONE);
		lblItems0wt.setText(display+itemInventory[0].getWeight());

		
		Label lblItems0amt = new Label(this, SWT.NONE);
		lblItems0amt.setText(display+itemInventory[0].getNumber());
		
		Label lblItems1 = new Label(this, SWT.NONE);
		lblItems1.setText(itemInventory[1].getName());

		
		Label lblItems1wt = new Label(this, SWT.NONE);
		lblItems1wt.setText(display+itemInventory[1].getWeight());

		
		Label lblItems1amt = new Label(this, SWT.NONE);
		lblItems1amt.setText(display+itemInventory[1].getNumber());

		
		Label lblItems2 = new Label(this, SWT.NONE);
		lblItems2.setText(itemInventory[2].getName());

		
		Label lblItems2wt = new Label(this, SWT.NONE);
		lblItems2wt.setText(display+itemInventory[2].getWeight());

		
		Label lblItems2amt = new Label(this, SWT.NONE);
		lblItems2amt.setText(display+itemInventory[2].getNumber());
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		Button btnClose = new Button(this, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				done = 1;
			}
		});
		btnClose.setText("Close Inventory");
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
