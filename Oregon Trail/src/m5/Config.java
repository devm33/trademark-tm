package m5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

public class Config {

	protected Shell shlOregontrail;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Config window = new Config();
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
		shlOregontrail.open();
		shlOregontrail.layout();
		while (!shlOregontrail.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlOregontrail = new Shell();
		shlOregontrail.setSize(395, 293);
		shlOregontrail.setText("Oregon Trail");
		
		text = new Text(shlOregontrail, SWT.BORDER);
		text.setBounds(71, 65, 76, 21);
		
		text_1 = new Text(shlOregontrail, SWT.BORDER);
		text_1.setBounds(71, 90, 76, 21);
		
		text_2 = new Text(shlOregontrail, SWT.BORDER);
		text_2.setBounds(71, 117, 76, 21);
		
		text_3 = new Text(shlOregontrail, SWT.BORDER);
		text_3.setBounds(71, 144, 76, 21);
		
		Label lblNewLabel = new Label(shlOregontrail, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setBounds(10, 68, 55, 15);
		lblNewLabel.setText("Leader:");
		
		Label lblConfig = new Label(shlOregontrail, SWT.NONE);
		lblConfig.setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.BOLD));
		lblConfig.setBounds(146, 10, 91, 32);
		lblConfig.setText("CONFIG");
		
		text_4 = new Text(shlOregontrail, SWT.BORDER);
		text_4.setBounds(71, 171, 76, 21);
		
		Label lblParty = new Label(shlOregontrail, SWT.NONE);
		lblParty.setAlignment(SWT.RIGHT);
		lblParty.setBounds(10, 90, 55, 15);
		lblParty.setText("Party 1:");
		
		Label lblParty_3 = new Label(shlOregontrail, SWT.NONE);
		lblParty_3.setAlignment(SWT.RIGHT);
		lblParty_3.setText("Party 2:");
		lblParty_3.setBounds(10, 117, 55, 15);
		
		Label lblParty_2 = new Label(shlOregontrail, SWT.NONE);
		lblParty_2.setAlignment(SWT.RIGHT);
		lblParty_2.setText("Party 3:");
		lblParty_2.setBounds(10, 144, 55, 15);
		
		Label lblParty_1 = new Label(shlOregontrail, SWT.NONE);
		lblParty_1.setAlignment(SWT.RIGHT);
		lblParty_1.setText("Party 4:");
		lblParty_1.setBounds(10, 171, 55, 15);
		
		Label lblNewLabel_1 = new Label(shlOregontrail, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.RIGHT);
		lblNewLabel_1.setBounds(184, 68, 66, 15);
		lblNewLabel_1.setText("Profession:");
		
		Label lblNewLabel_2 = new Label(shlOregontrail, SWT.NONE);
		lblNewLabel_2.setAlignment(SWT.RIGHT);
		lblNewLabel_2.setBounds(195, 120, 55, 15);
		lblNewLabel_2.setText("Pace:");
		
		Label lblNewLabel_3 = new Label(shlOregontrail, SWT.NONE);
		lblNewLabel_3.setAlignment(SWT.RIGHT);
		lblNewLabel_3.setBounds(195, 174, 55, 15);
		lblNewLabel_3.setText("Rations:");
		
		Combo combo = new Combo(shlOregontrail, SWT.READ_ONLY);
		combo.setItems(new String[] {"Farmer", "Banker", "Carpenter"});
		combo.setBounds(256, 65, 91, 23);
		combo.setText("Farmer");
		
		Combo combo_1 = new Combo(shlOregontrail, SWT.READ_ONLY);
		combo_1.setItems(new String[] {"Meager", "Normal", "Filling"});
		combo_1.setBounds(256, 171, 91, 23);
		combo_1.setText("Meager");
		
		Combo combo_2 = new Combo(shlOregontrail, SWT.READ_ONLY);
		combo_2.setItems(new String[] {"Slow", "Medium", "Fast"});
		combo_2.setBounds(256, 117, 91, 23);
		combo_2.setText("Slow");
		
		Button btnNewButton = new Button(shlOregontrail, SWT.NONE);
		btnNewButton.setBounds(146, 220, 91, 25);
		btnNewButton.setText("Start");

	}
}
