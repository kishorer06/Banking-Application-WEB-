/******************************************************************************
*	Program Author: Kishore Reddy Gujja for CSCI 6810 Java and the Internet	  *
*	Date: November, 2015													  *
*******************************************************************************/
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.kishore.banking.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ConfirmationBO extends JFrame
{
	private JTabbedPane tabbedPane, billPane;
	private JPanel tabPanel_1,tabPanel_2,tabPanel_3,tabPanel_4, billtabPanel_1, billtabPanel_2;
	private Account acc;

	public ConfirmationBO(String UName, String Customername)
	{
		tabbedPane = new JTabbedPane();
		billPane = new JTabbedPane();
		//Account Overview, Open Account, Transfer, Inquire Transactions, Bill Pay in which Payee and Payment are subtabs
		tabPanel_1 = new AccountOverviewBO(UName, Customername);
		tabPanel_2 = new OpenBankAccountPanel(UName, Customername);
		tabPanel_3 = new TransferBO(UName);
		tabPanel_4 = new InquireTransactionBO(UName);

		billtabPanel_1 = new PayeeBO(UName, Customername);
		billtabPanel_2 = new PaymentBO(UName, Customername);


		billPane.addTab("Payee", billtabPanel_1);
		billPane.addTab("Payment", billtabPanel_2);
		billPane.setSelectedIndex(0);

		tabbedPane.addTab("Account Overview", tabPanel_1);
		tabbedPane.addTab("open account", tabPanel_2);
		tabbedPane.addTab("Transfer", tabPanel_3);
		tabbedPane.addTab("Inquire Transactions", tabPanel_4);
		tabbedPane.addTab("Bill Payment", billPane);
		tabbedPane.setSelectedIndex(0);
		// tabbedPane.addTab("Inquire Transaction", tabPanel_4);
		// tabbedPane.addTab("Bill Payment", tabPanel_5);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		add(tabbedPane);
		setTitle("Welcome To Bank Of Java");
	    setSize(900, 600);
	    Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension d = tk.getScreenSize();
	    int screenHeight = d.height;
	    int screenWidth = d.width;
	    setLocation( screenWidth / 3, screenHeight / 5);
	   	/* tabbedPane.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		        tabbedPane.getSelectedIndex();
		        // Prints the string 3 times if there are 3 tabs etc
		    }
		});*/
	    addWindowListener (new WindowAdapter(){
	    	public void windowClosing (WindowEvent e){
			 	System.exit(0);
		   	}
	    });

	    show();
    }
    public static void main(String[] args){
		String Uname= "";
		//String Customername;
		ConfirmationBO c=new ConfirmationBO(Uname,"");
		JFrame frame = new ConfirmationBO(Uname,""); // initialize a JFrame object
		frame.show();
	}
}
