package gui;

import game.World;
import game.River;
import exceptions.InsufficientFundsException;

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
						river.takeFerry();
					}
					catch(InsufficientFundsException f)
					{
						System.out.println("whoops insufficient funds");
					}
				}
				else if(crossMethods.getText().equals("Ford")){
					river.ford();
				}
				else{
					river.caulk();
				}
				
				done = true;
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
}
