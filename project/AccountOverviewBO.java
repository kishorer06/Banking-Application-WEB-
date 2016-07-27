/******************************************************************************
*	Program Author: Kishore Reddy Gujja for CSCI 6810 Java and the Internet	  *
*	Date: November, 2015													  *
*******************************************************************************/
import java.awt.*;     //including Java packages used by this program
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import com.kishore.banking.*;


class AccountOverviewBO extends JPanel{

    private String UName, Name;

    public AccountOverviewBO(String U_Name, String CustomerName){
        UName = U_Name;
        Name = CustomerName;

        JLabel welcomeLabel = new JLabel("Welcome " + CustomerName);
        welcomeLabel.setFont(new Font("TimesRoman", Font.BOLD, 18));

        GridBagLayout Gridbg = new GridBagLayout();
        setLayout(Gridbg);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 100;
        gbc.weighty = 1;

        // add(welcomeLabel);
        add(welcomeLabel, gbc, 0, 0, 1, 1);

        JPanel CAPanel = new JPanel();
        JPanel SAPanel = new JPanel();

        CheckingAccount CA = new CheckingAccount();
        CA = CA.getAccountInfo(UName);

        SavingsAccount SA = new SavingsAccount();
        SA = SA.getAccountInfo(UName);

        if(!CA.getCANum().equals("")){
            JLabel CNumberLabel = new JLabel("Account Number:");
            JLabel CNumber = new JLabel();
            CNumber.setText(CA.getCANum());
            JLabel CBalanceLabel = new JLabel("Balance:");
            JLabel CBalance = new JLabel();
            CBalance.setText(String.valueOf(CA.getBal()));


            CAPanel.add(CNumberLabel);
            CAPanel.add(CNumber);
            CAPanel.add(CBalanceLabel);
            CAPanel.add(CBalance);

            // add(CAType, gbc, 0, 3, 1, 1);
            // add(CNumberLabel, gbc, 0, 4, 1, 1);
            // add(CNumber, gbc, 1, 4, 1, 1);
            // add(CBalanceLabel, gbc, 0, 5, 1, 1);
            // add(CBalance, gbc, 1, 5, 1, 1);

            TitledBorder title1 = BorderFactory.createTitledBorder("Checking Account");
            CAPanel.setBorder(title1);

            add(CAPanel, gbc, 0, 2, 1, 1);
        }

        if(!SA.getSANum().equals("")){
            JLabel SNumberLabel = new JLabel("Account Number:");
            JLabel SNumber = new JLabel();
            SNumber.setText(SA.getSANum());
            JLabel SBalanceLabel = new JLabel("Balance:");
            JLabel SBalance = new JLabel();
            SBalance.setText(String.valueOf(SA.getBal()));

            SAPanel.add(SNumberLabel);
            SAPanel.add(SNumber);
            SAPanel.add(SBalanceLabel);
            SAPanel.add(SBalance);

            // add(SAType, gbc, 0, 7, 1, 1);
            // add(SNumberLabel, gbc, 0, 8, 1, 1);
            // add(SNumber, gbc, 1, 8, 1, 1);
            // add(SBalanceLabel, gbc, 0, 9, 1, 1);
            // add(SBalance, gbc, 1, 9, 1, 1);

            TitledBorder title2 = BorderFactory.createTitledBorder("Savings Account");
            SAPanel.setBorder(title2);

            add(SAPanel, gbc, 0, 3, 1, 1);
        }



    }

    // public void actionPerformed(ActionEvent evt){
    //     String CheckingAccountNum;
    //     String SavingsAccountNum;

    //     String arg = evt.getActionCommand();
    //     if (arg.equals("View Balance")) {
    //         AccountType = (String)CheckingOrSavingsBox.getSelectedItem();

    //         if(AccountType.equals("Checking")){
    //             CheckingAccount CA = new CheckingAccount();
    //             CA = CA.getAccountInfo(UName);
    //             CheckingAccountNum = CA.getCANum();

    //             AccountNumberField.setText(CheckingAccountNum);
    //             double checkingBalance = CA.getBalance(CheckingAccountNum);
    //             System.out.println("Checking Balance: " + checkingBalance );
    //             String str1 = Double.toString(checkingBalance);

    //             if(checkingBalance == -1)
    //                 JOptionPane.showMessageDialog(null, "Invalid Account Number", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    //             else
    //                 BalanceField.setText(str1);
    //         }

    //         else if(AccountType.equals("Savings")){
    //             SavingsAccount SA = new SavingsAccount();
    //             SA = SA.getAccountInfo(UName);
    //             SavingsAccountNum = SA.getSANum();
    //             AccountNumberField.setText(SavingsAccountNum);
    //             double savingsBalance = SA.getBalance(SavingsAccountNum);
    //             String str2 = Double.toString(savingsBalance);
    //             if(savingsBalance == -1)
    //                 JOptionPane.showMessageDialog(null, "Invalid Account Number", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    //             else
    //                 BalanceField.setText(str2);
    //         }

    //         else if(AccountType.equals("Choose Account Type"))
    //             JOptionPane.showMessageDialog(null, "Please Choose an Account Type!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    //         else if (AccountNumber.length() != 10 )
    //             JOptionPane.showMessageDialog(null, "Please Enter an Account Number with Exactly 10 Characters!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    //     }
    // }

    public void add(Component c, GridBagConstraints gbc, int x, int y, int w, int h){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        add(c, gbc);
    }
}
