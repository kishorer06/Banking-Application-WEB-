/******************************************************************************
*	Program Author: Kishore Reddy Gujja for CSCI 6810 Java and the Internet	  *
*	Date: November, 2015													  *
*******************************************************************************/
import javax.swing.JOptionPane;
import com.kishore.banking.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.kishore.banking.CheckingAccount;
import com.kishore.banking.SavingsAccount;

class TransferControl {
    private String Uname;
    private String CheckingAccountNum;
    private String SavingsAccountNum;
    private float amount;

    public TransferControl(String UName, String ChkNum, String SvgNum, float Amt) {
        Uname = UName;
        CheckingAccountNum = ChkNum;
        SavingsAccountNum = SvgNum;
        amount = Amt;
    }

    public void TransferToCK() {
        CheckingAccount CA = new CheckingAccount();
        CA.setCANum(CheckingAccountNum);
        SavingsAccount SA = new SavingsAccount();
        SA.setSANum(SavingsAccountNum);
        if (SA.Withdraw(SavingsAccountNum, amount) && CA.deposit(CheckingAccountNum, amount))
                {
            JOptionPane.showMessageDialog(null, "Transfer Completed!",
                    "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                DateFormat dateFormat = new SimpleDateFormat("MM/DD/YYYY");
                Calendar cal = Calendar.getInstance();
                Transaction TA = new Transaction(cal.getTime(), amount, "Transfered", SavingsAccountNum, CheckingAccountNum, Uname);
                     TA.recordTransaction();

            }
        else
            JOptionPane.showMessageDialog(null, "Transfer Failed entered amount is more than avilable balance",
                    "Confirmation", JOptionPane.INFORMATION_MESSAGE);

    }

    public void TransferToSA() {
        CheckingAccount CA = new CheckingAccount();
        CA.setCANum(CheckingAccountNum);
        SavingsAccount SA = new SavingsAccount();
        SA.setSANum(SavingsAccountNum);
        if (CA.Withdraw(CheckingAccountNum, amount)
                && SA.deposit(SavingsAccountNum, amount))
                {
            JOptionPane.showMessageDialog(null, "Transfer Completed!",
                    "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            DateFormat dateFormat = new SimpleDateFormat("MM/DD/YYYY");
            Calendar cal = Calendar.getInstance();
            Transaction TA = new Transaction(cal.getTime(), amount, "Transfered", CheckingAccountNum, SavingsAccountNum, Uname);
                     TA.recordTransaction();

                }
        else
            JOptionPane.showMessageDialog(null, "Transfer Failed entered amount is more than available balance",
                    "Confirmation", JOptionPane.INFORMATION_MESSAGE);

    }

}