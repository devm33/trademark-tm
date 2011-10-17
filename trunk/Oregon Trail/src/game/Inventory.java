package game;

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
	private static Item[]itemInventory;
	
	public Inventory(){
		itemInventory = new Item[5];
		//itemInventory[0] = new Item(0,0,"Water");
		//itemInventory[1]; //item type 1
		//itemInventory[2];	//item type 2
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
		

		Label lblWater = new Label(shlOregonTrail, SWT.NONE);
		lblWater.setText(itemInventory[0].getName());

		
		Label lblWaterwt = new Label(shlOregonTrail, SWT.NONE);
		lblWaterwt.setText(display+itemInventory[0].getWeight());

		
		Label lblWateramt = new Label(shlOregonTrail, SWT.NONE);
		lblWateramt.setText(display+itemInventory[0].getNumber());
		
		Label lblFood = new Label(shlOregonTrail, SWT.NONE);
		lblFood.setText(itemInventory[1].getName());

		
		Label lblFoodwt = new Label(shlOregonTrail, SWT.NONE);
		lblFoodwt.setText(display+itemInventory[1].getWeight());

		
		Label lblFoodamt = new Label(shlOregonTrail, SWT.NONE);
		lblFoodamt.setText(display+itemInventory[1].getNumber());

		
		Label lblAmmo = new Label(shlOregonTrail, SWT.NONE);
		lblAmmo.setText(itemInventory[2].getName());

		
		Label lblAmmowt = new Label(shlOregonTrail, SWT.NONE);
		lblAmmowt.setText(display+itemInventory[2].getWeight());

		
		Label lblAmmoamt = new Label(shlOregonTrail, SWT.NONE);
		lblAmmoamt.setText(display+itemInventory[2].getNumber());



	}
}
