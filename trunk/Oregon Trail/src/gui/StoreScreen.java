package gui;

import game.Store;
import game.World;
import items.Ammo;
import items.Axle;
import items.Clothing;
import items.Food;
import items.Medicine;
import items.Oxen;
import items.Tongue;
import items.Water;
import items.Wheel;

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
				switch(list.getSelectionIndex()){
				case 0:
					//if Oxen is selected
					displayOxen();
					break;
				case 1:
					//if Food is selected
					displayFood();
					break;
				case 2:
					//if Clothing is selected
					displayClothing();
					break;
				case 3:
					//if Ammunition is selected
					displayAmmunition();
					break;
				case 4:
					//if Medicine is selected
					displayMedicine();
					break;
				case 5:
					//if Water is selected
					displayWater();
					break;
				case 6:
					//if Wagon Wheel is selected
					displayWheel();
					break;
				case 7:
					//if Wagon Axle is selected
					displayAxle();
					break;
				case 8: 
					//if Wagon Tongue is selected
					displayTongue();
					break;
				}
			}
		});
		
		//When user clicks the purchase button.
		btnPurchase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(isInteger(txtAmount.getText())){
					try {
						switch(list.getSelectionIndex()){
						case 0:
							//if Oxen is selected
							buyOxen();
							break;
						case 1:
							//if Food is selected
							buyFood();
							break;
						case 2:
							//if Clothing is selected
							buyClothing();
							break;
						case 3:
							//if Ammunition is selected
							buyAmmunition();
							break;
						case 4:
							//if Medicine is selected
							buyMedicine();
							break;
						case 5:
							//if Water is selected
							buyWater();
							break;
						case 6:
							//if Wagon Wheel is selected
							buyWheel();
							break;
						case 7:
							//if Wagon Axle is selected
							buyAxle();
							break;
						case 8: 
							//if Wagon Tongue is selected
							buyTongue();
							break;
						default:
							lblResponse.setText("Huh?");
							break;
						}
					} catch (InsufficientFundsException e1) {
						lblResponse.setText("Come back when you have enough money, you bum!");
					} catch (WeightCapacityExceededException e2){
						lblResponse.setText("What in tarnations! Your wagon can't hold that!");
					}
					lblWagonCapacity.setText(World.getWagon().getTotalWeight()+"/"+World.getWagon().getCapacity());
				} else {
					if(list.getSelectionIndex() == -1){
						lblResponse.setText("You need to choose an item, fool!");
					} else {
						lblResponse.setText("Try specifying a real quantity, dummy.");
					}
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
	
	private void displayOxen(){
		lblName.setText("Oxen");
		lblWeight.setText("Does not affect wagon weight");
		lblPrice.setText("$40");
		lblDesc.setText("A pair of strong animals used to pull your wagon.");
		lblResponse.setText("Freshly stolen from my neighbor's ranch.");
	}
	
	private void displayFood(){
		lblName.setText("Food");
		lblWeight.setText("5 lbs");
		lblPrice.setText("$5");
		lblDesc.setText("A durable canned foodstuff.");
		lblResponse.setText("Better than my wife's poor excuse for cooking.");
	}
	
	private void displayClothing(){
		lblName.setText("Clothing");
		lblWeight.setText("2 lbs");
		lblPrice.setText("$10");
		lblDesc.setText("A handknit cotton outfit");
		lblResponse.setText("Wow your oxen with the latest 1848 rag fashion.");
	}
	
	private void displayAmmunition(){
		lblName.setText("Ammunition");
		lblWeight.setText("3 lbs");
		lblPrice.setText("$2");
		lblDesc.setText("A box of 20 hunting rifle rounds used for big game.");
		lblResponse.setText("Don't blow your face off with these.");
	}
	
	private void displayMedicine(){
		lblName.setText("Medicine");
		lblWeight.setText("1 lb");
		lblPrice.setText("$10");
		lblDesc.setText("A large container of medicine.");
		lblResponse.setText("It's great for headaches and heroin addicts.");
	}
	
	private void displayWater(){
		lblName.setText("Water");
		lblWeight.setText("6 lbs");
		lblPrice.setText("$2");
		lblDesc.setText("A bucket of drinkable water.");
		lblResponse.setText("Clean....maybe not. Drinkable....yes.");
	}
	
	private void displayWheel(){
		lblName.setText("Wagon Wheel");
		lblWeight.setText("75 lbs");
		lblPrice.setText("$10");
		lblDesc.setText("A big, round wooden wagon wheel.");
		lblResponse.setText("Big and round like my wife.");
	}
	
	private void displayAxle(){
		lblName.setText("Wagon Axle");
		lblWeight.setText("125 lbs");
		lblPrice.setText("$10");
		lblDesc.setText("A long, heavy wooden wagon axle.");
		lblResponse.setText("Long and heavy like my di...wagon axles.");
	}
	
	private void displayTongue(){
		lblName.setText("Wagon Tongue");
		lblWeight.setText("100 lbs");
		lblPrice.setText("$10");
		lblDesc.setText("A wooden wagon tongue.");
		lblResponse.setText("It's the newest in oxen-containing technology.");
	}
	
	private void buyOxen() throws InsufficientFundsException, WeightCapacityExceededException{
		currentStore.buy(new Oxen(), Integer.parseInt(txtAmount.getText()), 20, 0);
		currentStore.buy(new Oxen(), Integer.parseInt(txtAmount.getText()), 20, 0);
		lblResponse.setText("Don't worry, I only stole the good ones.");
	}	
	
	private void buyFood() throws InsufficientFundsException, WeightCapacityExceededException{
		currentStore.buy(new Food(), Integer.parseInt(txtAmount.getText()), 5, 5);
		lblResponse.setText("It won't expire for another hour or two.");
	}
	
	private void buyClothing() throws InsufficientFundsException, WeightCapacityExceededException{
		currentStore.buy(new Clothing(), Integer.parseInt(txtAmount.getText()), 10, 2);
		lblResponse.setText("That brown matches well with your brown.");
	}
	
	private void buyAmmunition() throws InsufficientFundsException, WeightCapacityExceededException{
		currentStore.buy(new Ammo(), Integer.parseInt(txtAmount.getText()), 2, 3);
		lblResponse.setText("Happy hunting, shooter!");
	}
	
	private void buyMedicine() throws InsufficientFundsException, WeightCapacityExceededException{
		currentStore.buy(new Medicine(), Integer.parseInt(txtAmount.getText()), 10, 1);
		lblResponse.setText("Don't tell the doctor where you got that...");
	}
	
	private void buyWater() throws InsufficientFundsException, WeightCapacityExceededException{
		currentStore.buy(new Water(), Integer.parseInt(txtAmount.getText()), 2, 6);
		lblResponse.setText("A bucket per day keeps death away.");
	}
	
	private void buyWheel() throws InsufficientFundsException, WeightCapacityExceededException{
		currentStore.buy(new Wheel(), Integer.parseInt(txtAmount.getText()), 10, 75);
		lblResponse.setText("Spike attachments sold separately.");
	}
	
	private void buyAxle() throws InsufficientFundsException, WeightCapacityExceededException{
		currentStore.buy(new Axle(), Integer.parseInt(txtAmount.getText()), 10, 125);
		lblResponse.setText("You sure that's enough? You can never have too many heavy axles.");
	}
	
	private void buyTongue() throws InsufficientFundsException, WeightCapacityExceededException{
		currentStore.buy(new Tongue(), Integer.parseInt(txtAmount.getText()), 10, 100);
		lblResponse.setText("Your Ox will thank you for forcing them to wear that.");
	}
	
	/**
	 * Change the store of the store screen.
	 * 
	 * @param s the new store to access
	 */
	public void setStore(Store s) {
		currentStore = s;
	}
	
	private boolean isInteger(String input){  
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
	
	private void createContents(){
		list = new List(this, SWT.BORDER);
		list.setItems(new String[] {"Oxen", "Food", "Clothing", 
									"Ammunition", "Medicine", "Water",
									"Wagon Wheel", "Wagon Axle", "Wagon Tongue"});
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
		lblResponse.setText("Welcome to the store!");
	}
}
