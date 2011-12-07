package gui;

import game.World;
import game.River;
import exceptions.InsufficientFundsException;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;

/**
 * River crossing choice/minigame
 * @author Jaron
 *
 */
public class RiverScreen extends Composite {
	private boolean done = false;
	private Canvas canvas;
	private Label lblCross;
	private Image image;
	private Combo crossMethods;
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
		
		
		
		
		lblCross = new Label(this, SWT.NONE);
		lblCross.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		lblCross.setBounds(20, 228, 430, 25);
		lblCross.setText("We need to cross the river! What we gonna do?!");
		
		crossMethods = new Combo(this, SWT.READ_ONLY);
		crossMethods.setItems(new String[] {"Take Ferry", "Ford", "Caulk"});
		crossMethods.setBounds(35, 264, 148, 21);
		crossMethods.setText("Take Ferry");
		
		crossButton = new Button(this, SWT.NONE);
		crossButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				River river =  World.getMap().getLastRiver();
				if(river == null)
					river = World.getMap().getNextRiver(); //backup for the first river
				
				if(crossMethods.getText().equals("Take Ferry"))
				{
					try
					{
						river.takeFerry();
						done = true;
					}
					catch(InsufficientFundsException f)
					{
						System.out.println("whoops insufficient funds");
					}
				}
				else if(crossMethods.getText().equals("Ford")){
					river.ford();
					done = true;
				}
				else{
					river.caulk();
					done = true;
				}
			}
		});
		crossButton.setBounds(209, 256, 231, 34);
		crossButton.setText("CROSS");
		
		canvas = new Canvas(this, SWT.NONE);
		canvas.setBounds(10, 10, 430, 216);
		image = new Image(getDisplay(),getClass().getResourceAsStream("images/river.jpg"));
		
		canvas.addPaintListener(new PaintListener() {
	        public void paintControl(PaintEvent e) {
	         e.gc.drawImage(image,0,0);
	        }
	    });
	}
}
