package gui;

import game.World;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * Screen for loading a saved game (if there are any)
 * 
 * 
 * @author Devraj Mehta
 *
 */

public class LoadScreen extends Composite {
	
	private boolean done;
	private boolean goBack;
	private List list;
	private Label lblNewerSavesAppear;
	private Button load;
	private Button back;
	private ArrayList<String> games;
	
	public LoadScreen(Composite parent, int style) {
		super(parent, style);
		goBack = false;
		done = false;
		games = new ArrayList<String>();
		createContents();
		
		load.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(load(list.getSelectionIndex()))
					done = true;
			}
		});
		
		back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				goBack = true;
				done = true;
			}
		});
	}
	
	/**
	 * Update list data from files
	 * 
	 * format for game file: (first couple lines)
	 * leader name
	 * date
	 * ...
	 */
	public void update() {
		done = false;
		goBack = false;
		int i = 0;
		String filename = "src/game/savedgames/savedgame_";
		ArrayList<String> items = new ArrayList<String>();
		String t = "";
		Scanner scan = null;
		File file = new File(filename + i);
		while(file != null && file.exists()) {
			try {
				t = "";
				scan = new Scanner(file);
				t += scan.nextLine() + "      " + scan.nextLine();
				items.add(t);
				t = "";
				while(scan.hasNextLine())
					t += scan.nextLine() + '\n';
				games.add(t);
				i++;
				file = new File(filename + i);
			} catch (FileNotFoundException e) {
				file = null;
			} catch (NoSuchElementException e) {
				System.out.println("Invalid game file: " + filename + i);
			}
		}
		if(!items.isEmpty()) {
			String[] arr = new String[items.size()];
			i = 0;
			for(String s : items)
				arr[i++] = s;
			System.out.println(items.size() + " saved game" + ((items.size()==1)?"":"s"));
			for(String s : arr)
				System.out.println(s);
			list.setItems(arr);
		}
		load.setEnabled(!items.isEmpty());
			
	}
	
	public boolean load(int i) {
		return i >= 0 && i < games.size() && World.loadGame(games.get(i));
	}
	
	/**
	 * creates controls for the composite
	 */
	private void createContents() {
		Label lbl1 = new Label(this, SWT.NONE);
		lbl1.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lbl1.setBounds(27, 23, 132, 18);
		lbl1.setText("Saved Games:");
		
		list = new List(this, SWT.BORDER);
		list.setBounds(27, 57, 351, 174);
		
		load = new Button(this, SWT.NONE);
		load.setBounds(194, 248, 94, 28);
		load.setText("Load");
		load.setEnabled(false);
		
		back = new Button(this, SWT.NONE);
		back.setBounds(294,248,84,28);
		back.setText("Back");
		back.setEnabled(true);
		
		lblNewerSavesAppear = new Label(this, SWT.WRAP);
		lblNewerSavesAppear.setAlignment(SWT.CENTER);
		lblNewerSavesAppear.setBounds(27, 248, 161, 28);
		lblNewerSavesAppear.setText("Newer Saves appear lower in the list");
	}
	
	/**
	 * @return true if the load screen is done
	 */
	public boolean isDone() {
		return done;
	}
	
	/**
	 * @return true if the load screen was nixed and back button was pressed
	 */
	public boolean goBack() {
		return goBack;
	}
	
	/**
	 * resets the booleans keeping track of the loadscreen's state
	 */
	public void resetBools() {
		goBack = false;
		done = false;
	}
}
