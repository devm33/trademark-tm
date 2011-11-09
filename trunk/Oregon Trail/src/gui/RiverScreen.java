package gui;

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
	private boolean crossed;
	
	private Canvas c;
	private Label lblCross;
	private Label lblChoice;
	private ComboViewer methodViewer;
	private Combo crossMethods;
	private Label lblDescription;
	private Button crossButton;
	
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
		
		methodViewer = new ComboViewer(this, SWT.NONE);
		crossMethods = methodViewer.getCombo();
		crossMethods.setItems(new String[] {"Take Ferry", "Ford", "Caulk"});
		crossMethods.setBounds(80, 133, 92, 21);
		
		lblDescription= new Label(this, SWT.NONE);
		lblDescription.setBounds(24, 168, 49, 13);
		lblDescription.setText("New Label");
		
		crossButton = new Button(this, SWT.NONE);
		crossButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				River river = World.getMap().getNextRiver();
				
				if(crossMethods.getText() == "Take Ferry")
				{
					try
					{
						takeFerry(river);
					}
					catch(InsufficientFundsException f)
					{
						// TODO what to do when error is caught
					}
				}
				if(crossMethods.getText() == "Ford")
					ford(river);
				else
					caulk(river);
				
				done = true;
			}
		});
		crossButton.setBounds(80, 231, 78, 34);
		crossButton.setText("CROSS");
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
			crossed = true;
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
		
		if(r.getDepth() >= 3 || fordChance > 7)
			crossed = false;
		else
			crossed = true;
	}
	
	/**
	 * Runs the situation where the user chooses to caulk the wagon
	 * @param r the river to be crossed
	 */
	public void caulk(River r)
	{
		// TODO Take items off the wagon when crossing fails
		
		int caulkChance = (int)(Math.random()*10 + 1);
		
		if(caulkChance > 4)
			crossed = false;
		else
			crossed = true;
	}
}
