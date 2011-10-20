package game;

/**
 * Inventory class to hold and display Items
 * 
 * @author Stephen Bentley
 */

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ViewForm;

public class Inventory {
	private Item[]itemInventory;
	private int length = 6;
	
	public Inventory(){
		itemInventory = new Item[length];
		
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
	

	protected Shell shlOregonTrail;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Inventory window = new Inventory();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		while (!shlOregonTrail.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlOregonTrail = new Shell();
		shlOregonTrail.setLayout(new GridLayout(3, true));
		String display="";
		
		Label lblItem = new Label(shlOregonTrail, SWT.NONE);
		lblItem.setText("Item                ");
		
		Label lblWeight = new Label(shlOregonTrail, SWT.NONE);
		lblWeight.setText("Weight");

		
		Label lblAmmount = new Label(shlOregonTrail, SWT.NONE);
		lblAmmount.setText("Amount");
		

		Label lblItems0 = new Label(shlOregonTrail, SWT.NONE);
		lblItems0.setText(itemInventory[0].getName());

		
		Label lblItems0wt = new Label(shlOregonTrail, SWT.NONE);
		lblItems0wt.setText(display+itemInventory[0].getWeight());

		
		Label lblItems0amt = new Label(shlOregonTrail, SWT.NONE);
		lblItems0amt.setText(display+itemInventory[0].getNumber());
		
		Label lblItems1 = new Label(shlOregonTrail, SWT.NONE);
		lblItems1.setText(itemInventory[1].getName());

		
		Label lblItems1wt = new Label(shlOregonTrail, SWT.NONE);
		lblItems1wt.setText(display+itemInventory[1].getWeight());

		
		Label lblItems1amt = new Label(shlOregonTrail, SWT.NONE);
		lblItems1amt.setText(display+itemInventory[1].getNumber());

		
		Label lblItems2 = new Label(shlOregonTrail, SWT.NONE);
		lblItems2.setText(itemInventory[2].getName());

		
		Label lblItems2wt = new Label(shlOregonTrail, SWT.NONE);
		lblItems2wt.setText(display+itemInventory[2].getWeight());

		
		Label lblItems2amt = new Label(shlOregonTrail, SWT.NONE);
		lblItems2amt.setText(display+itemInventory[2].getNumber());



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
}
