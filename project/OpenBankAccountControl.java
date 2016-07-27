/******************************************************************************
*	Program Author: Kishore Reddy Gujja for CSCI 6810 Java and the Internet	  *
*	Date: November, 2015													  *
*******************************************************************************/
import java.lang.*; //including Java packages used by this program
import javax.swing.*;
import java.util.*;
import com.kishore.banking.*;

public class OpenBankAccountControl
{

    public OpenBankAccountControl(String AcountType, String  AcountNumber, String  Name, String  UName, float  Balance) {
		//Use CheckingAccount object to invoke method openAcct()
	    Transaction tr;
        CheckingAccount CAexists = new CheckingAccount();
        CAexists = CAexists.getAccountInfo(UName);
        SavingsAccount SAexists = new SavingsAccount();
        SAexists = SAexists.getAccountInfo(UName);
        if (AcountType.equals("Checking")) {
            if(CAexists.getCANum().equals("")){
                CheckingAccount CA = new CheckingAccount(AcountNumber, Name, UName, Balance);
                if (CA.openAcct()) {
                        Date date = new Date();
                        tr = new Transaction(date, Balance, "Deposit", "", AcountNumber, UName);
                    if(tr.recordTransaction()){
                        //System.out.println("successful!");
                        JOptionPane.showMessageDialog(null, "Opening a Checking Account is Successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else
                //System.out.println("fail!");
                JOptionPane.showMessageDialog(null, "Opening a Checking Account failed.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Checking Account already exists.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(AcountType.equals("Savings")){
            if(SAexists.getSANum().equals("")){
                SavingsAccount SA = new SavingsAccount(AcountNumber, Name, UName, Balance);
                if (SA.openAcct()) {
                        Date date = new Date();
                        tr = new Transaction(date, Balance, "Deposit", "", AcountNumber, UName);
                    if(tr.recordTransaction()){
                        //System.out.println("successful!");
                        JOptionPane.showMessageDialog(null, "Opening a Savings Account is Successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else
                //System.out.println("fail!");
                JOptionPane.showMessageDialog(null, "Opening a Savings Account failed.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Savings Account already exists.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Select Account Type.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
	}
}