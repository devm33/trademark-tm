package gui;

import game.Store;
import game.World;
import items.Ammo;
import items.Food;
import items.Medicine;

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
	public boolean done = false;
	public boolean needUpdate = false;
	private Text txtAmount;
	private Store currentStore;
	private List list;
	private Label lbl1;
	private Label lbl2;
	private Label lbl3;
	private Label lbl4;
	private Label lblName;
	private Label lblWeight;
	private Label lblPrice;
	private Label lblDesc;
	private Button btnPurchase;
	private Button btnExitStore;
	private Label lblAmount;
	private Label lbl5;
	private Label lblWagonCapacity;
	private Label lbl6;
	private Label lblResponse;
	
	/**
	 * Change the store of the store screen.
	 * 
	 * @param s the new store to access
	 */
	public void setStore(Store s) {
		currentStore = s;
	}
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public StoreScreen(Composite parent, int style){
		super(parent,style);
		
		list = new List(this, SWT.BORDER);
		list.setItems(new String[] {"Ammunition", "Food", "Medicine"});
		list.setBounds(33, 10, 141, 229);
		
		lbl1 = new Label(this, SWT.NONE);
		lbl1.setText("Name:");
		lbl1.setBounds(198, 45, 35, 15);
		
		lbl2 = new Label(this, SWT.NONE);
		lbl2.setText("Weight:");
		lbl2.setBounds(198, 67, 41, 15);
		
		lbl3 = new Label(this, SWT.NONE);
		lbl3.setText("Price:");
		lbl3.setBounds(198, 91, 29, 15);
		
		lbl4 = new Label(this, SWT.NONE);
		lbl4.setText("Description:");
		lbl4.setBounds(198, 112, 63, 15);
		
		lblName = new Label(this, SWT.NONE);
		lblName.setText("No Item Selected");
		lblName.setBounds(239, 45, 101, 15);
		
		lblWeight = new Label(this, SWT.NONE);
		lblWeight.setBounds(245, 67, 50, 15);
		
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
		
		lbl5 = new Label(this, SWT.NONE);
		lbl5.setBounds(198, 10, 90, 17);
		lbl5.setText("Wagon Capacity:");
		
		lblWagonCapacity = new Label(this, SWT.NONE);
		lblWagonCapacity.setBounds(294, 10, 63, 13);
		lblWagonCapacity.setText(World.getWagon().getTotalWeight()+"/"+World.getWagon().getCapacity());
		
		lbl6 = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		lbl6.setBounds(198, 23, 242, 16);
		
		lblResponse = new Label(this, SWT.WRAP);
		lblResponse.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblResponse.setAlignment(SWT.CENTER);
		lblResponse.setBounds(43, 249, 352, 41);
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
					lblResponse.setText("Don't blow your face off with these.");
				} else if(list.getSelectionIndex()==1){
					//If Food is selected in the list
					lblName.setText("Food");
					lblWeight.setText("5 lbs");
					lblPrice.setText("$5");
					lblDesc.setText("Durable canned foodstuff.");
					lblResponse.setText("Better than my wife's poor excuse for cooking.");
				} else if(list.getSelectionIndex()==2){
					//If Medicine is selected in the list
					lblName.setText("Medicine");
					lblWeight.setText("1000 lbs");
					lblPrice.setText("$10");
					lblDesc.setText("A large container of medicine.");
					lblResponse.setText("It's great for headaches and heroin addicts.");
				} else {
					
				}
			}
		});
		
		//When user clicks the purchase button.
		btnPurchase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				needUpdate = true;
				
				if(isInteger(txtAmount.getText())){
					try {
						if(list.getSelectionIndex()==0){
							//If Ammunition is selected in the list
							currentStore.buy(new Ammo(), Integer.parseInt(txtAmount.getText()), 2);
							lblResponse.setText("Happy hunting, shooter!");
						} else if(list.getSelectionIndex()==1){
							//If Food is selected in the list
							currentStore.buy(new Food(), Integer.parseInt(txtAmount.getText()), 5);
							lblResponse.setText("It won't expire for another hour or two.");
						} else if(list.getSelectionIndex()==2){
							//If Medicine is selected in the list
							currentStore.buy(new Medicine(), Integer.parseInt(txtAmount.getText()), 10);
							lblResponse.setText("Don't tell the doctor where you got that...");
						} else {
							lblResponse.setText("You need to choose an item, fool!");
						}
					} catch (InsufficientFundsException e1) {
						lblResponse.setText("Come back when you have enough money, you bum!");
					} catch (WeightCapacityExceededException e2){
						lblResponse.setText("Your wagon can't hold that!");
					}
					lblWagonCapacity.setText(World.getWagon().getTotalWeight()+"/"+World.getWagon().getCapacity());
				} else {
					lblResponse.setText("Try specifying a real quantity, dummy.");
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
	
	public boolean isInteger(String input){  
	   try{  
	      Integer.parseInt(input);  
	      return true;  
	   }  
	   catch(Exception e){  
	      return false;  
	   }  
	} 
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
