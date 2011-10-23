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
/**
 * TODO: RESET UPON EXIT
 * @author Jaron
 *
 */
public class Store extends Composite{
	public int done = 0;
	private Text txtAmount;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Store(Composite parent, int style){
		super(parent,style);
		
		final List list = new List(this, SWT.BORDER);
		list.setItems(new String[] {"Ammunition", "Food", "Medicine"});
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
		
		final Label lblName = new Label(this, SWT.NONE);
		lblName.setText("No Item Selected");
		lblName.setBounds(239, 45, 101, 15);
		
		final Label lblWeight = new Label(this, SWT.NONE);
		lblWeight.setBounds(245, 67, 35, 15);
		
		final Label lblPrice = new Label(this, SWT.NONE);
		lblPrice.setBounds(233, 91, 28, 15);
		
		final Label lblDesc = new Label(this, SWT.WRAP);
		lblDesc.setText("Select an item to buy from the store inventory.");
		lblDesc.setBounds(267, 112, 128, 57);
		
		Button btnPurchase = new Button(this, SWT.NONE);
		btnPurchase.setText("Purchase");
		btnPurchase.setBounds(235, 214, 60, 25);
		
		Button btnExitStore = new Button(this, SWT.NONE);
		btnExitStore.setText("Exit Store");
		btnExitStore.setBounds(335, 214, 60, 25);
		
		txtAmount = new Text(this, SWT.BORDER);
		txtAmount.setBounds(264, 175, 76, 21);
		
		Label lblAmount = new Label(this, SWT.NONE);
		lblAmount.setText("Amount:");
		lblAmount.setBounds(214, 178, 47, 15);
		
		Label lblWagonCapacity = new Label(this, SWT.NONE);
		lblWagonCapacity.setBounds(198, 10, 90, 17);
		lblWagonCapacity.setText("Wagon Capacity:");
		
		Label lblWCap = new Label(this, SWT.NONE);
		lblWCap.setBounds(294, 10, 63, 13);
		lblWCap.setText(MainGame.getWagon().getTotalWeight()+"/"+MainGame.getWagon().getCapacity());
		
		Label label_1 = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(198, 23, 242, 16);
		
		Label lblResponse = new Label(this, SWT.WRAP);
		lblResponse.setBounds(198, 245, 223, 30);
		lblResponse.setText("Welcome to the Independence Store!");

		//When user selects an item in the store inventory list.
		list.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//lblName.setText(list.getItem(list.getSelectionIndex()));
				/*need to hold weight, price, and description in some database*/
				
				if(list.getSelectionIndex()==0){
					//If Ammunition is selected in the list
					lblName.setText("Ammunition");
					lblWeight.setText("3 lbs");
					lblPrice.setText("$2");
					lblDesc.setText("A box of 20 hunting rifle rounds used for big game.");
				} else if(list.getSelectionIndex()==1){
					//If Food is selected in the list
					lblName.setText("Food");
					lblWeight.setText("5 lbs");
					lblPrice.setText("$5");
					lblDesc.setText("Durable canned foodstuff.");
				} else if(list.getSelectionIndex()==2){
					//If Water is selected in the list
					lblName.setText("Medicine");
					lblWeight.setText("1 lb");
					lblPrice.setText("$10");
					lblDesc.setText("A large container of medicine.");
				} else {
					
				}
			}
		});
		
		//When user clicks the purchase button.
		btnPurchase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(list.getSelectionIndex()==0){
					//If Ammunition is selected in the list
					int buyNum = Integer.parseInt(txtAmount.getText());
					int availCash = Integer.parseInt(MainGame.getWagon().getCash());
					int total = buyNum*2;
					try{
						MainGame.getWagon().getLeader().setMoney(availCash-total);
						MainGame.getWagon().addToInventory(new Ammo(), buyNum);
					}
					catch(InsufficentFundsException f){
						
					}
					catch(WeightCapacityExceededException w){
						
					}
					
				} else if(list.getSelectionIndex()==1){
					//If Food is selected in the list
					int buyNum = Integer.parseInt(txtAmount.getText());
				} else if(list.getSelectionIndex()==2){
					//If Medicine is selected in the list
					int buyNum = Integer.parseInt(txtAmount.getText());
				} else {
					
				}
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
