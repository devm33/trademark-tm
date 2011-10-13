package game;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
//import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import java.util.List;
import java.util.ArrayList;

public class Config {

	protected Shell shlOregontrail;
	private Text txtName4;
	private Text txtLeaderName;
	private Text txtName3;
	private Text txtName2;
	private Text txtName1;
	private Combo dropProfession;
	private Combo dropPace;
	private Combo dropRations;
	private Button btnStart;
	
	private Leader partyLeader;
	private Traveler party1;
	private Traveler party2;
	private Traveler party3;
	private Traveler party4;
	private Wagon wagon;
	private List<Traveler> memberList;

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
		memberList = new ArrayList<Traveler>();
		memberList.add(party1);
		memberList.add(party2);
		memberList.add(party3);
		memberList.add(party4);
		
		wagon = new Wagon(0,0,0,partyLeader, memberList);
		
		shlOregontrail = new Shell();
		shlOregontrail.setSize(395, 293);
		shlOregontrail.setText("Oregon Trail");
		
		txtLeaderName = new Text(shlOregontrail, SWT.BORDER);
		txtLeaderName.setText("Jonathan");
		txtLeaderName.setBounds(71, 65, 76, 21);
		
		txtName1 = new Text(shlOregontrail, SWT.BORDER);
		txtName1.setText("Wilson");
		txtName1.setBounds(71, 90, 76, 21);
		
		txtName2 = new Text(shlOregontrail, SWT.BORDER);
		txtName2.setText("Sarah");
		txtName2.setBounds(71, 117, 76, 21);
		
		txtName3 = new Text(shlOregontrail, SWT.BORDER);
		txtName3.setText("Sebastian");
		txtName3.setBounds(71, 144, 76, 21);
		
		Label lblNewLabel = new Label(shlOregontrail, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setBounds(10, 68, 55, 15);
		lblNewLabel.setText("Leader:");
		
		Label lblConfig = new Label(shlOregontrail, SWT.NONE);
		lblConfig.setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.BOLD));
		lblConfig.setBounds(146, 10, 91, 32);
		lblConfig.setText("CONFIG");
		
		txtName4 = new Text(shlOregontrail, SWT.BORDER);
		txtName4.setText("Elizabeth");
		txtName4.setBounds(71, 171, 76, 21);
		
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
		
		dropProfession = new Combo(shlOregontrail, SWT.READ_ONLY);
		dropProfession.setItems(new String[] {"Farmer", "Banker", "Carpenter"});
		dropProfession.setBounds(256, 65, 91, 23);
		dropProfession.setText("Farmer");
		
		dropRations = new Combo(shlOregontrail, SWT.READ_ONLY);
		dropRations.setItems(new String[] {"Meager", "Normal", "Filling"});
		dropRations.setBounds(256, 171, 91, 23);
		dropRations.setText("Meager");
		
		dropPace = new Combo(shlOregontrail, SWT.READ_ONLY);
		dropPace.setItems(new String[] {"Slow", "Medium", "Fast"});
		dropPace.setBounds(256, 117, 91, 23);
		dropPace.setText("Slow");
		
		btnStart = new Button(shlOregontrail, SWT.NONE);
		btnStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String leaderName = "";
				leaderName = txtLeaderName.getText();
				if(leaderName.equals(""))
					leaderName = "Jonathon";
				
				if(dropProfession.getText().equals("Farmer")){
					partyLeader = new Farmer(leaderName);
				} else if(dropProfession.getText().equals("Banker")){
					partyLeader = new Banker(leaderName);
				} else {
					partyLeader = new Carpenter(leaderName);
				}
				
				if(dropPace.getText().equals("Slow")){
					wagon.setPace(0);
				} else if(dropPace.getText().equals("Medium")){
					wagon.setPace(1);
				} else {
					wagon.setPace(2);
				}
				
				if(dropRations.getText().equals("Meager")){
					wagon.setRations(0);
				} else if(dropRations.getText().equals("Normal")){
					wagon.setRations(1);
				} else {
					wagon.setRations(2);
				}
				
				if(txtName1.getText().equals("")){
					party1 = new Traveler("Wilson");
				} else {
					party1 = new Traveler(txtName1.getText());
				}
				
				if(txtName2.getText().equals("")){
					party2 = new Traveler("Sarah");
				} else {
					party2 = new Traveler(txtName2.getText());
				}
				
				if(txtName3.getText().equals("")){
					party3 = new Traveler("Sebastian");
				} else {
					party3 = new Traveler(txtName3.getText());
				}
				
				if(txtName4.getText().equals("")){
					party4 = new Traveler("Elizabeth");
				} else {
					party4 = new Traveler(txtName4.getText());
				}
				
				System.out.println("Leader: " + partyLeader.getName());
				System.out.println("Cash: " + partyLeader.getMoney());
				System.out.println("Profession: " + dropProfession.getText());
				System.out.println("Pace: " + dropPace.getText() + " (" + wagon.getPace() + ")");
				System.out.println("Rations: " + dropRations.getText() + " ("+ wagon.getRations() + ")");
				System.out.println("Member1: " + party1.getName());
				System.out.println("Member2: " + party2.getName());
				System.out.println("Member3: " + party3.getName());
				System.out.println("Member4: " + party4.getName());
			}
		});
		btnStart.setBounds(146, 220, 91, 25);
		btnStart.setText("Start");

	}
}
