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

public class Config {

	protected Shell shell;
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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(71, 45, 76, 21);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(71, 70, 76, 21);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(71, 97, 76, 21);
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(71, 124, 76, 21);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setBounds(10, 48, 55, 15);
		lblNewLabel.setText("Leader:");
		
		Label lblConfig = new Label(shell, SWT.NONE);
		lblConfig.setBounds(124, 10, 55, 15);
		lblConfig.setText("CONFIG");
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setBounds(71, 151, 76, 21);
		
		Label lblParty = new Label(shell, SWT.NONE);
		lblParty.setAlignment(SWT.RIGHT);
		lblParty.setBounds(10, 70, 55, 15);
		lblParty.setText("Party 1:");
		
		Label lblParty_3 = new Label(shell, SWT.NONE);
		lblParty_3.setAlignment(SWT.RIGHT);
		lblParty_3.setText("Party 2:");
		lblParty_3.setBounds(10, 97, 55, 15);
		
		Label lblParty_2 = new Label(shell, SWT.NONE);
		lblParty_2.setAlignment(SWT.RIGHT);
		lblParty_2.setText("Party 3:");
		lblParty_2.setBounds(10, 124, 55, 15);
		
		Label lblParty_1 = new Label(shell, SWT.NONE);
		lblParty_1.setAlignment(SWT.RIGHT);
		lblParty_1.setText("Party 4:");
		lblParty_1.setBounds(10, 151, 55, 15);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.RIGHT);
		lblNewLabel_1.setBounds(184, 48, 66, 15);
		lblNewLabel_1.setText("Profession:");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setAlignment(SWT.RIGHT);
		lblNewLabel_2.setBounds(195, 100, 55, 15);
		lblNewLabel_2.setText("Pace:");
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setAlignment(SWT.RIGHT);
		lblNewLabel_3.setBounds(195, 154, 55, 15);
		lblNewLabel_3.setText("Rations:");
		
		Combo combo = new Combo(shell, SWT.READ_ONLY);
		combo.setItems(new String[] {"Farmer", "Banker", "Carpenter"});
		combo.setBounds(256, 45, 91, 23);
		combo.setText("Farmer");
		
		Combo combo_1 = new Combo(shell, SWT.READ_ONLY);
		combo_1.setItems(new String[] {"Meager", "Normal", "Filling"});
		combo_1.setBounds(256, 151, 91, 23);
		combo_1.setText("Meager");
		
		Combo combo_2 = new Combo(shell, SWT.READ_ONLY);
		combo_2.setItems(new String[] {"Slow", "Medium", "Fast"});
		combo_2.setBounds(256, 97, 91, 23);
		combo_2.setText("Slow");

	}
}
