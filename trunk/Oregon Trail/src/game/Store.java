package game;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Store extends Composite{
	public static int done = 0;
	private Text txtAmount;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Store(Composite parent, int style){
		super(parent,style);
		
		final List list = new List(this, SWT.BORDER);
		list.setItems(new String[] {"Ammunition", "Food", "Water"});
		list.setBounds(33, 10, 141, 229);
		
		Label lbl1 = new Label(this, SWT.NONE);
		lbl1.setText("Name:");
		lbl1.setBounds(198, 10, 35, 15);
		
		Label lbl2 = new Label(this, SWT.NONE);
		lbl2.setText("Weight:");
		lbl2.setBounds(198, 32, 41, 15);
		
		Label lbl3 = new Label(this, SWT.NONE);
		lbl3.setText("Price:");
		lbl3.setBounds(198, 56, 29, 15);
		
		Label lbl4 = new Label(this, SWT.NONE);
		lbl4.setText("Description:");
		lbl4.setBounds(198, 77, 63, 15);
		
		final Label lblName = new Label(this, SWT.NONE);
		lblName.setText("No Item Selected");
		lblName.setBounds(239, 10, 101, 15);
		
		final Label lblWeight = new Label(this, SWT.NONE);
		lblWeight.setBounds(245, 32, 35, 15);
		
		final Label lblPrice = new Label(this, SWT.NONE);
		lblPrice.setBounds(233, 56, 28, 15);
		
		final Label lblDesc = new Label(this, SWT.WRAP);
		lblDesc.setText("Select an item to buy from the store inventory.");
		lblDesc.setBounds(267, 77, 128, 57);
		
		Button btnPurchase = new Button(this, SWT.NONE);
		btnPurchase.setText("Purchase");
		btnPurchase.setBounds(235, 179, 60, 25);
		
		Button btnExitStore = new Button(this, SWT.NONE);
		btnExitStore.setText("Exit Store");
		btnExitStore.setBounds(335, 179, 60, 25);
		
		txtAmount = new Text(this, SWT.BORDER);
		txtAmount.setBounds(264, 140, 76, 21);
		
		Label lblAmount = new Label(this, SWT.NONE);
		lblAmount.setText("Amount:");
		lblAmount.setBounds(214, 143, 47, 15);

		//When user selects an item in the store inventory list.
		list.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//lblName.setText(list.getItem(list.getSelectionIndex()));
				/*need to hold weight, price, and description in some database*/
				
				if(list.getSelectionIndex()==0){
					//If Ammunition is selected in the list
					lblName.setText("Ammunition");
					lblWeight.setText("5 lbs");
					lblPrice.setText("$20");
					lblDesc.setText("Hunting rifle rounds used for big game.");
				} else if(list.getSelectionIndex()==1){
					//If Food is selected in the list
					lblName.setText("Food");
					lblWeight.setText("4 lbs");
					lblPrice.setText("$10");
					lblDesc.setText("Durable canned foodstuff.");
				} else if(list.getSelectionIndex()==2){
					//If Water is selected in the list
					lblName.setText("Water");
					lblWeight.setText("10 lbs");
					lblPrice.setText("$5");
					lblDesc.setText("A bucket of water.");
				} else {
					
				}
			}
		});
		
		//When user clicks the purchase button.
		btnPurchase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		
		//When user clicks the exit store button
		btnExitStore.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				done = 1;
			}
		});
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
