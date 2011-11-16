package gui;

//***
import game.*;

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
	
	private List list;
	private Button load;
	private Button back;
	private ArrayList<String> games;
	
	public LoadScreen(Composite parent, int style) {
		super(parent, style);
		games = new ArrayList<String>();
		createContents();
		update();
		
		load.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				load(list.getSelectionIndex());
			}
		});
		
		back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//TODO somehow go back here
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
		int i = 0;
		String filename = "savedgame_";
		ArrayList<String> items = new ArrayList<String>();
		String t = "";
		Scanner scan = null;
		File file = new File(filename + i);
		while(file != null && file.exists()) {
			try {
				scan = new Scanner(file);
				t += scan.nextLine() + '\t' + scan.nextLine();
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
		list.setItems((String[])items.toArray());
	}
	
	public void load(int i) {
		if(i < 0 || i > games.size())
			return;
		World.loadGame(games.get(i));
	}
	
	/**
	 * creates controls for the composite
	 */
	private void createContents() {
		Label lbl1 = new Label(this, SWT.NONE);
		lbl1.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.NORMAL));
		lbl1.setBounds(27, 23, 103, 18);
		lbl1.setText("Saved Games:");
		
		List list = new List(this, SWT.BORDER);
		list.setBounds(27, 57, 351, 174);
		
		load = new Button(this, SWT.NONE);
		load.setBounds(194, 248, 94, 28);
		load.setText("Load");
		load.setEnabled(false);
		
		back = new Button(this, SWT.NONE);
		back.setBounds(294,248,84,28);
		back.setText("Back");
		back.setEnabled(true);
	}
}
