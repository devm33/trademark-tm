package gui;

import game.Wagon;
import game.Inventory;
import items.Food;
import game.World;
import game.River;
import people.Leader;
import exceptions.InsufficientFundsException;
import java.lang.Math;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;

/**
 * River crossing choice/minigame
 * @author Jaron
 *
 */
public class RiverScreen extends Composite {
	private boolean done = false;
	private Canvas c;
	private Label lblCross;
	private Label lblChoice;
	private ComboViewer methodViewer;
	private Combo crossMethods;
	private Label lblDescription;
	private Button crossButton;
	private Label lblName;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RiverScreen(Composite parent, int style) {
		super(parent, style);
		
		createContents();
	}
	
	/**
	 * check if user is finished with river screen
	 * @return
	 */
	public boolean isDone(){
		return done;
	}
	
	/**
	 * reset done variable so user can re-enter river screen
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
		
		c = new Canvas(this, SWT.NONE);
		c.setBounds(271, 10, 169, 255);
		
		lblCross = new Label(this, SWT.NONE);
		lblCross.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		lblCross.setBounds(24, 40, 231, 25);
		lblCross.setText("we need to cross the river!");
		
		lblChoice = new Label(this, SWT.NONE);
		lblChoice.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		lblChoice.setBounds(24, 64, 222, 25);
		lblChoice.setText("What we gonna do?!?");
		
		crossMethods = new Combo(this, SWT.READ_ONLY);
		crossMethods.setItems(new String[] {"Take Ferry", "Ford", "Caulk"});
		crossMethods.setBounds(80, 133, 92, 21);
		crossMethods.setText("Take Ferry");
		
		lblDescription= new Label(this, SWT.NONE);
		lblDescription.setAlignment(SWT.CENTER);
		lblDescription.setBounds(24, 160, 222, 57);
		
		crossButton = new Button(this, SWT.NONE);
		crossButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				River river = World.getMap().getNextRiver();
				
				if(crossMethods.getText().equals("Take Ferry"))
				{
					try
					{
						takeFerry(river);
					}
					catch(InsufficientFundsException f)
					{
						System.out.println("whoops insufficient funds");
					}
				}
				if(crossMethods.getText().equals("Ford")){
					ford(river);
				}
				if(crossMethods.getText().equals("Caulk")){
					caulk(river);
				}
			}
		});
		crossButton.setBounds(21, 231, 231, 34);
		crossButton.setText("CROSS");
		
		Label lblResult = new Label(this, SWT.NONE);
		lblResult.setBounds(10, 271, 430, 19);
		
		lblName = new Label(this, SWT.NONE);
		lblName.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		lblName.setBounds(24, 10, 231, 34);
	}
	
	/**
	 *  Runs the situation where the user chooses to take a ferry
	 * @param r the river to be crossed
	 */
	public void takeFerry(River r) throws InsufficientFundsException
	{
		Leader leader = World.getWagon().getLeader();
		if(leader.getMoney() < r.getCost())
			throw new InsufficientFundsException();
		else
		{
			leader.setMoney(leader.getMoney()-r.getCost());
			done = true;
			World.getWagon().setNotification("You spent some money to cross safely.");
			System.out.println("FERRY RESULT");
		}
	}
	
	/**
	 * Runs the situation where the user chooses to ford the river
	 * @param r the river to be crossed
	 */
	public void ford(River r)
	{
		// TODO Take items off the wagon when crossing fails
		
		int fordChance = (int)(Math.random()*10 + 1);
		
		if(r.getDepth() >= 3 || fordChance > 7){
			done = true;
			Food f = World.getWagon().getInventory().getFood();
			if(f.getNumber() > 25)
				f.setNumber(f.getNumber()-25);
			else
				f.setNumber(0);
			World.getWagon().setNotification("You forded across the river but lost some food");
			System.out.println("BAD FORD RESULT");
		}
		else{
			done = true;
			World.getWagon().setNotification("You successfully ford across the river.");
			System.out.println("GOOD FORD RESULT");
		}
	}
	
	/**
	 * Runs the situation where the user chooses to caulk the wagon
	 * @param r the river to be crossed
	 */
	public void caulk(River r)
	{
		// TODO Take items off the wagon when crossing fails
		
		int caulkChance = (int)(Math.random()*10 + 1);
		
		if(caulkChance > 4){
			done = true;
			Food f = World.getWagon().getInventory().getFood();
			if(f.getNumber() > 25)
				f.setNumber(f.getNumber()-25);
			else
				f.setNumber(0);
			World.getWagon().setNotification("You crossed the river but lost some food");
			System.out.println("BAD CAULK RESULT");
		}
		else{
			done = true;
			World.getWagon().setNotification("You successfully crossed the river by caulking the wagon");
			System.out.println("GOOD CAULK RESULT");
		}
	}
}
