/******************************************************************************
*	Program Author: Kishore Reddy Gujja for CSCI 6810 Java and the Internet	  *
*	Date: November, 2015													  *
*******************************************************************************/
import java.awt.Color; //including Java packages used by this program
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.kishore.banking.CheckingAccount;
import com.kishore.banking.SavingsAccount;

class TransferBO extends JPanel {
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JComboBox list1;
    private JComboBox list2;
    private JTextField input;
    private JButton transferM;
    private String CheckingAccountNum;
    private String SavingsAccountNum;

    public TransferBO(final String UName) {
        label1 = new JLabel("From:");
        label2 = new JLabel("To:");
        label3 = new JLabel("Enter Amount:");

        list1 = new JComboBox(new String[] { "Checking Account",
                "Saving Account" });
        list2 = new JComboBox(new String[] { "Checking Account",
                "Saving Account" });

        input = new JTextField(10);
        transferM = new JButton("Transfer");

        /*
         * CheckingAccount CA = new CheckingAccount(); CheckingAccountNum =
         * CA.getChkAccountNumber(UName); SavingsAccount SA = new
         * SavingsAccount(); SavingsAccountNum = SA.getSavAccountNumber(UName);
         */
        transferM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String str = input.getText();
                float transferNum = Float.parseFloat(str); // get the input
                                                            // number from
                                                            // Textfield
                // TransferControl TC = new
                // TransferControl(CheckingAccountNum,SavingsAccountNum,transferNum);
                if (list1.getSelectedItem().equals("Saving Account")
                        && list2.getSelectedItem().equals("Checking Account")) {
                    CheckingAccount CA = new CheckingAccount(UName);
                    CA = CA.getAccountInfo(UName);
                    CheckingAccountNum = CA.getCANum();
                    SavingsAccount SA = new SavingsAccount(UName);
                    SA = SA.getAccountInfo(UName);
                    SavingsAccountNum = SA.getSANum();
                    TransferControl TC = new TransferControl(UName, CheckingAccountNum, SavingsAccountNum, transferNum);
                    TC.TransferToCK();

                    // deposit to checking account
                } else if (list1.getSelectedItem().equals("Checking Account") && list2.getSelectedItem().equals("Saving Account")) {
                    CheckingAccount CA = new CheckingAccount(UName);
                    CA = CA.getAccountInfo(UName);
                    CheckingAccountNum = CA.getCANum();
                    SavingsAccount SA = new SavingsAccount(UName);
                    SA = SA.getAccountInfo(UName);
                    SavingsAccountNum = SA.getSANum();
                    TransferControl TC = new TransferControl(UName,CheckingAccountNum, SavingsAccountNum, transferNum);
                    TC.TransferToSA();

                    // deposit to saving account

                }
            }
        });

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 100;
        gbc.weighty = 100;
        add(label1, gbc, 0, 0, 1, 1);

        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 100;
        gbc.weighty = 100;
        add(list1, gbc, 1, 0, 1, 1);

        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 100;
        gbc.weighty = 100;
        add(label2, gbc, 0, 1, 1, 1);
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 100;
        gbc.weighty = 100;
        add(label3, gbc, 0, 2, 1, 1);

        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 100;
        gbc.weighty = 100;
        add(list2, gbc, 1, 1, 1, 1);

        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 100;
        gbc.weighty = 100;
        add(input, gbc, 1, 2, 1, 1);

        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 100;
        gbc.weighty = 100;
        add(transferM, gbc, 1, 3, 1, 1);
    }

    public void add(Component c, GridBagConstraints gbc, int x, int y, int w,
            int h) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        add(c, gbc);
    }
}