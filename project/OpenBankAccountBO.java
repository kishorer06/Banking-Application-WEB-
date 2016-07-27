/******************************************************************************
*	Program Author: Kishore Reddy Gujja for CSCI 6810 Java and the Internet	  *
*	Date: November, 2015													  *
*******************************************************************************/
import java.awt.*;     //including Java packages used by this program
import java.awt.event.*;
import javax.swing.*;

class OpenBankAccountPanel extends JPanel implements ActionListener
{
    private JButton OpenButton;
    private JTextField UsernameField, NameField, AccountNumberField, BalanceField;
    private JComboBox CheckingOrSavingsBox;
    private String UName, AccountNumber, Name, AccountType;
    private float Balance;

    public OpenBankAccountPanel(String UName, String CustomerName)
    {
        OpenButton = new JButton("Open"); //initializing two button references

        CheckingOrSavingsBox = new JComboBox();
        CheckingOrSavingsBox.addItem("Choose Account Type");
		CheckingOrSavingsBox.addItem("Checking");
		CheckingOrSavingsBox.addItem("Savings");

        UsernameField = new JTextField(15);
        UsernameField.setText(UName);
        NameField = new JTextField(CustomerName);
        AccountNumberField = new JTextField(15);
        BalanceField = new JTextField(15);
        BalanceField.setText("0.0");

        //JLabel TypeLabel = new JLabel("Choose Account Type: ");
        JLabel NameLabel = new JLabel("Customer Name:");
        JLabel UsernameLabel = new JLabel("Username: ");
        JLabel NumberLabel = new JLabel("Account Number:");
        JLabel BalanceLabel = new JLabel("Opening Deposit:");

        JPanel TypePanel = new JPanel();
        JPanel UsernamePanel = new JPanel();
        JPanel NamePanel = new JPanel();
        JPanel NumberPanel = new JPanel();
        JPanel BalancePanel = new JPanel();

        TypePanel.add(CheckingOrSavingsBox);
        UsernamePanel.add(UsernameLabel);
        UsernamePanel.add(UsernameField);
        NamePanel.add(NameLabel);
        NamePanel.add(NameField);
        NumberPanel.add(NumberLabel);
        NumberPanel.add(AccountNumberField);
        BalancePanel.add(BalanceLabel);
        BalancePanel.add(BalanceField);

        add(TypePanel);
        add(UsernamePanel);
        add(NamePanel);
        add(NumberPanel);
        add(BalancePanel);

        add(OpenButton);  //add the two buttons on to this panel
        OpenButton.addActionListener(this); //event listener registration
    }

    public void actionPerformed(ActionEvent evt)  //event handling
    {
        //Object source = evt.getSource(); //get who generates this event
        String arg = evt.getActionCommand();
        if (arg.equals("Open")) { //determine which button is clicked
            UName = UsernameField.getText(); //take actions
            Name = NameField.getText();
            AccountNumber = AccountNumberField.getText();
            String balStr = BalanceField.getText();
            Balance = Float.parseFloat(balStr);
            AccountType = (String)CheckingOrSavingsBox.getSelectedItem();
            if (AccountType.equals("Choose Account Type"))
                JOptionPane.showMessageDialog(null, "Please Choose an Account Type!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            else if (AccountNumber.length() != 10 )
                     JOptionPane.showMessageDialog(null, "Please Enter an Account Number with Exactly 10 Characters!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                 else {
					 OpenBankAccountControl OBAcct_Ctrl = new OpenBankAccountControl(AccountType, AccountNumber, Name, UName, Balance);
				 }

            //Acct = new Account(UName, PsWord, PsWord1, Name);
            //if (Acct.signUp())
                //JOptionPane.showMessageDialog(null, "Account has been open!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            //else
                //JOptionPane.showMessageDialog(null, "Account creation failed due to an invalid email address or unmatched passwords or the email address exists.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		}
    }
}

