package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

import people.Banker;
import people.Carpenter;
import people.Farmer;
import people.Leader;
import people.Traveler;

import game.Wagon;
import game.World;

import java.util.List;
import java.util.ArrayList;

/**
 * Configuration Screen
 * 
 * @author Jaron
 *
 */
public class ConfigScreen extends Composite{
	/**
	 * done is set to 1 upon exit of configuration screen
	 */
	private boolean done = false;
	
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
	private List<Traveler> memberList;

	/**
	 * Create the composite
	 * Places and initializes all the controls
	 * @param parent
	 * @param style
	 * @param w the wagon
	 */
	public ConfigScreen(Composite parent, int style) {
		super(parent, style);
		
		createContents();

		//When user clicks the "Start Journey" button.
		btnStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Set leader name and profession
				initializeLeader();

				//Create party members
				initializeParty();
				
				//Set initial pace of wagon to user selection
				setPace();

				//Set initial ration rate based on user selection.
				setRations();
				
				done = true;
			}
		});		
	}
	
	/**
	 * names and creates a leader
	 */
	private void initializeLeader(){
		//Leader is named by user or defaults to Jonathan
		String leaderName = "";
		leaderName = txtLeaderName.getText();
		if(leaderName.equals(""))
			leaderName = "Jonathan";

		//Set leader profession (Farmer, Banker, or Carpenter)
		if(dropProfession.getText().equals("Farmer")){
			partyLeader = new Farmer(leaderName);
		} else if(dropProfession.getText().equals("Banker")){
			partyLeader = new Banker(leaderName);
		} else {
			partyLeader = new Carpenter(leaderName);
		}
	}
	
	/**
	 * names and creates four party members
	 */
	private void initializeParty(){
		//First party member is named or defaults to Wilson
		if(txtName1.getText().equals("")){
			party1 = new Traveler("Wilson");
		} else {
			party1 = new Traveler(txtName1.getText());
		}

		//Second party member is named or defaults to Sarah
		if(txtName2.getText().equals("")){
			party2 = new Traveler("Sarah");
		} else {
			party2 = new Traveler(txtName2.getText());
		}

		//Third party member is named or defaults to Sebastian
		if(txtName3.getText().equals("")){
			party3 = new Traveler("Sebastian");
		} else {
			party3 = new Traveler(txtName3.getText());
		}

		//Fourth party member is named or defaults to Elizabeth
		if(txtName4.getText().equals("")){
			party4 = new Traveler("Elizabeth");
		} else {
			party4 = new Traveler(txtName4.getText());
		}
		
		//Add all party members to the party member list.
		memberList = new ArrayList<Traveler>();
		memberList.add(party1);
		memberList.add(party2);
		memberList.add(party3);
		memberList.add(party4);

		//add the people to the wagon
		World.getWagon().setMembers(memberList);
		World.getWagon().setLeader(partyLeader);
	}
	
	/**
	 * sets the pace rate of the wagon
	 */
	private void setPace(){
		if(dropPace.getText().equals("Leisurely")){
			World.getWagon().setPace(5);
		} else if(dropPace.getText().equals("Steady")){
			World.getWagon().setPace(10);
		} else {
			World.getWagon().setPace(15);
		}
	}
	
	/**
	 * sets the rations rate of the wagon
	 */
	private void setRations(){
		if(dropRations.getText().equals("Bare-Bones")){
			World.getWagon().setRations(1);
		} else if(dropRations.getText().equals("Meager")){
			World.getWagon().setRations(2);
		} else if(dropRations.getText().equals("Normal")){
			World.getWagon().setRations(3);
		} else {
			World.getWagon().setRations(4);
		}
	}
	
	/**
	 * checks if the user is finished with the configuration screen
	 * @return if the user is done with the configuration screen
	 */
	public boolean isDone(){
		return done;
	}

	/**
	 * resets the done boolean for revisit to the configuration screen
	 */
	public void resetDone(){
		done = false;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	/**
	 * creates controls for the configuration screen
	 */
	private void createContents(){
		txtLeaderName = new Text(this, SWT.BORDER);
		txtLeaderName.setText("Jonathan");
		txtLeaderName.setBounds(98, 65, 76, 21);

		txtName1 = new Text(this, SWT.BORDER);
		txtName1.setText("Wilson");
		txtName1.setBounds(98, 90, 76, 21);

		txtName2 = new Text(this, SWT.BORDER);
		txtName2.setText("Sarah");
		txtName2.setBounds(98, 117, 76, 21);

		txtName3 = new Text(this, SWT.BORDER);
		txtName3.setText("Sebastian");
		txtName3.setBounds(98, 144, 76, 21);

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setBounds(37, 68, 55, 15);
		lblNewLabel.setText("Leader:");

		Label lblConfig = new Label(this, SWT.NONE);
		lblConfig.setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.BOLD));
		lblConfig.setBounds(173, 10, 91, 32);
		lblConfig.setText("CONFIG");

		txtName4 = new Text(this, SWT.BORDER);
		txtName4.setText("Elizabeth");
		txtName4.setBounds(98, 171, 76, 21);

		Label lblParty = new Label(this, SWT.NONE);
		lblParty.setAlignment(SWT.RIGHT);
		lblParty.setBounds(37, 90, 55, 15);
		lblParty.setText("Party 1:");

		Label lblParty_3 = new Label(this, SWT.NONE);
		lblParty_3.setAlignment(SWT.RIGHT);
		lblParty_3.setText("Party 2:");
		lblParty_3.setBounds(37, 117, 55, 15);

		Label lblParty_2 = new Label(this, SWT.NONE);
		lblParty_2.setAlignment(SWT.RIGHT);
		lblParty_2.setText("Party 3:");
		lblParty_2.setBounds(37, 144, 55, 15);

		Label lblParty_1 = new Label(this, SWT.NONE);
		lblParty_1.setAlignment(SWT.RIGHT);
		lblParty_1.setText("Party 4:");
		lblParty_1.setBounds(37, 171, 55, 15);

		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.RIGHT);
		lblNewLabel_1.setBounds(211, 68, 66, 15);
		lblNewLabel_1.setText("Profession:");

		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setAlignment(SWT.RIGHT);
		lblNewLabel_2.setBounds(222, 120, 55, 15);
		lblNewLabel_2.setText("Pace:");

		Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setAlignment(SWT.RIGHT);
		lblNewLabel_3.setBounds(222, 174, 55, 15);
		lblNewLabel_3.setText("Rations:");

		dropProfession = new Combo(this, SWT.READ_ONLY);
		dropProfession.setItems(new String[] {"Farmer", "Banker", "Carpenter"});
		dropProfession.setBounds(283, 65, 91, 23);
		dropProfession.setText("Farmer");

		dropRations = new Combo(this, SWT.READ_ONLY);
		dropRations.setItems(new String[] {"Bare-Bones", "Meager", "Normal", "WellFed"});
		dropRations.setBounds(283, 171, 91, 23);
		dropRations.setText("Normal");

		dropPace = new Combo(this, SWT.READ_ONLY);
		dropPace.setItems(new String[] {"Leisurely", "Steady", "Grueling"});
		dropPace.setBounds(283, 117, 91, 23);
		dropPace.setText("Steady");

		btnStart = new Button(this, SWT.NONE);
		btnStart.setBounds(173, 220, 91, 25);
		btnStart.setText("Start Journey");
	}
}
