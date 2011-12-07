package gui;

import game.World;
import game.Inventory;
import items.*;

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
	
	private Label[] lblItemsname;
	private Label[] lblItemswt;
	private Label[] lblItemsamt;
	private Button[] btnItems;
	
	private Button btnClose;
	//private String display="";
	
	/**
	 * Creates a new inventory screen and inventory items
	 * @param parent
	 * @param style
	 */
	public InventoryScreen(Composite parent, int style){
		super(parent,style);
		
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
		int amount, weight;
		String name;
		
		for(int i=0; i<inventoryLength; i++)
		{
			amount = inv.getItem(i).getNumber();
			weight = inv.getItem(i).getWeight();
			name = inv.getItem(i).getName();
			
			lblItemsname[i].setText(name);
			lblItemswt[i].setText(""+weight*amount);
			lblItemsamt[i].setText(""+amount);
			if(amount != 0)
				btnItems[i].setEnabled(true);
			else
				btnItems[i].setEnabled(false);
			
			if(name.equals("Ammo"))
				btnItems[i].setText("hunt");
			else if(name.equals("Food") || name.equals("Water") || name.equals("Oxen") || name.equals("Clothing"))
				btnItems[i].setEnabled(false);
		}
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
		GridLayout g = new GridLayout();
		g.numColumns = 4;
		g.makeColumnsEqualWidth = true;
		g.verticalSpacing = 0;
		g.horizontalSpacing = 0;
		g.marginHeight = 0;
		g.marginWidth = 0;
		
		this.setLayout(g);
		
		Label lblItem = new Label(this, SWT.NONE);
		lblItem.setText("Item   ");
		
		Label lblWeight = new Label(this, SWT.NONE);
		lblWeight.setText("Weight");

		
		Label lblAmmount = new Label(this, SWT.NONE);
		lblAmmount.setText("Amount");
		
		Label lblButton = new Label(this, SWT.NONE);
		lblButton.setText("");
		
		
		int inventoryLength = World.getWagon().getInventory().getLength();
		Inventory inv = World.getWagon().getInventory();
		String name;
		lblItemsname = new Label[inventoryLength];
		lblItemswt = new Label[inventoryLength];
		lblItemsamt = new Label[inventoryLength];
		btnItems = new Button[inventoryLength];
		for(int i=0; i < inventoryLength; i++) {
			lblItemsname[i] = new Label(this, SWT.NONE);
			lblItemsname[i].setText("name of item");
			lblItemswt[i] = new Label(this, SWT.NONE);
			lblItemswt[i].setText("0000");
			lblItemsamt[i] = new Label(this, SWT.NONE);
			lblItemsamt[i].setText("0000");
			btnItems[i] = new Button(this, SWT.NONE);
			btnItems[i].setText("use");
			btnItems[i].setEnabled(false);
			btnItems[i].addSelectionListener(new UseButton(i));
		}

		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		btnClose = new Button(this, SWT.NONE);
		btnClose.setText("Close Inventory");
		new Label(this, SWT.NONE);
	}
	
	//class for calling the use methods of items
	public class UseButton extends SelectionAdapter {
		//the index of the item this button corresponds to
		private int index;
		//create a new use button
		public UseButton(int i) {
			index = i;
		}
		@Override
		public void widgetSelected(SelectionEvent e) {
			World.getWagon().getInventory().getItem(index).use();
		}
		
	}
	
}
