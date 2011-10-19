package game;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

public class Store {

	protected Shell shlStoer;
	private Text txtAmount;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Store window = new Store();
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
		shlStoer.open();
		shlStoer.layout();
		while (!shlStoer.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlStoer = new Shell();
		shlStoer.setSize(450, 300);
		shlStoer.setText("Oregon Trail");
		shlStoer.setLayout(new FormLayout());
		
		List list = new List(shlStoer, SWT.BORDER);
		list.setItems(new String[] {"ammo", "food", "water"});
		FormData fd_list = new FormData();
		fd_list.bottom = new FormAttachment(100, -23);
		fd_list.right = new FormAttachment(100, -283);
		fd_list.left = new FormAttachment(0, 10);
		list.setLayoutData(fd_list);
		
		Label lblNewLabel = new Label(shlStoer, SWT.NONE);
		fd_list.top = new FormAttachment(lblNewLabel, 6);
		lblNewLabel.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		fd_lblNewLabel.bottom = new FormAttachment(100, -258);
		fd_lblNewLabel.right = new FormAttachment(100, -317);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("Store Inventory");
		
		Label lbl1 = new Label(shlStoer, SWT.NONE);
		FormData fd_lbl1 = new FormData();
		fd_lbl1.top = new FormAttachment(list, 0, SWT.TOP);
		fd_lbl1.left = new FormAttachment(list, 24);
		lbl1.setLayoutData(fd_lbl1);
		lbl1.setText("Name");
		
		Label lbl2 = new Label(shlStoer, SWT.NONE);
		FormData fd_lbl2 = new FormData();
		fd_lbl2.top = new FormAttachment(lbl1, 7);
		fd_lbl2.left = new FormAttachment(lbl1, 0, SWT.LEFT);
		lbl2.setLayoutData(fd_lbl2);
		lbl2.setText("Weight");
		
		Label lbl3 = new Label(shlStoer, SWT.NONE);
		FormData fd_lbl3 = new FormData();
		fd_lbl3.top = new FormAttachment(lbl2, 9);
		fd_lbl3.left = new FormAttachment(lbl1, 0, SWT.LEFT);
		lbl3.setLayoutData(fd_lbl3);
		lbl3.setText("Price");
		
		Label lbl4 = new Label(shlStoer, SWT.NONE);
		FormData fd_lbl4 = new FormData();
		fd_lbl4.top = new FormAttachment(lbl3, 6);
		fd_lbl4.left = new FormAttachment(lbl1, 0, SWT.LEFT);
		lbl4.setLayoutData(fd_lbl4);
		lbl4.setText("Description:");
		
		Label lblName = new Label(shlStoer, SWT.NONE);
		FormData fd_lblName = new FormData();
		fd_lblName.top = new FormAttachment(list, 0, SWT.TOP);
		fd_lblName.left = new FormAttachment(lbl1, 6);
		lblName.setLayoutData(fd_lblName);
		lblName.setText("Sample");
		
		Label lblWeight = new Label(shlStoer, SWT.NONE);
		FormData fd_lblWeight = new FormData();
		fd_lblWeight.bottom = new FormAttachment(lbl2, 0, SWT.BOTTOM);
		fd_lblWeight.left = new FormAttachment(lbl2, 6);
		lblWeight.setLayoutData(fd_lblWeight);
		lblWeight.setText("1");
		
		Label lblPrice = new Label(shlStoer, SWT.NONE);
		FormData fd_lblPrice = new FormData();
		fd_lblPrice.bottom = new FormAttachment(lbl3, 0, SWT.BOTTOM);
		fd_lblPrice.left = new FormAttachment(lbl3, 6);
		lblPrice.setLayoutData(fd_lblPrice);
		lblPrice.setText("2");
		
		Label lblDesc = new Label(shlStoer, SWT.NONE);
		FormData fd_lblDesc = new FormData();
		fd_lblDesc.top = new FormAttachment(lbl4, 0, SWT.TOP);
		fd_lblDesc.left = new FormAttachment(lbl4, 6);
		lblDesc.setLayoutData(fd_lblDesc);
		lblDesc.setText("test");
		
		Button btnPurchase = new Button(shlStoer, SWT.NONE);
		FormData fd_btnPurchase = new FormData();
		fd_btnPurchase.bottom = new FormAttachment(100, -58);
		fd_btnPurchase.left = new FormAttachment(list, 61);
		btnPurchase.setLayoutData(fd_btnPurchase);
		btnPurchase.setText("Purchase");
		
		Button btnExitStore = new Button(shlStoer, SWT.NONE);
		FormData fd_btnExitStore = new FormData();
		fd_btnExitStore.top = new FormAttachment(btnPurchase, 0, SWT.TOP);
		fd_btnExitStore.left = new FormAttachment(btnPurchase, 40);
		btnExitStore.setLayoutData(fd_btnExitStore);
		btnExitStore.setText("Exit Store");
		
		txtAmount = new Text(shlStoer, SWT.BORDER);
		FormData fd_txtAmount = new FormData();
		fd_txtAmount.bottom = new FormAttachment(btnPurchase, -18);
		fd_txtAmount.left = new FormAttachment(list, 90);
		txtAmount.setLayoutData(fd_txtAmount);
		
		Label lblAmount = new Label(shlStoer, SWT.NONE);
		FormData fd_lblAmount = new FormData();
		fd_lblAmount.top = new FormAttachment(txtAmount, 3, SWT.TOP);
		fd_lblAmount.right = new FormAttachment(lbl4, 0, SWT.RIGHT);
		lblAmount.setLayoutData(fd_lblAmount);
		lblAmount.setText("Amount");

	}
}
