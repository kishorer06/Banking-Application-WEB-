/******************************************************************************
*	Program Author: Kishore Reddy Gujja for CSCI 6810 Java and the Internet	  *
*	Date: November, 2015													  *
*******************************************************************************/
import java.lang.*; //including Java packages used by this program
import java.util.*;
import javax.swing.*;
import com.kishore.banking.*;

public class LoginControl
{
    private Account Acct = new Account();

    public LoginControl(String UName, String PWord) {
		// Acct = new Account(UName, PWord);
		Acct = Acct.signIn(UName, PWord);

        String Name = Acct.getName();
        if(!Name.equals("")){
              ConfirmationBO c = new ConfirmationBO(UName, Name);
              c.show();
        }else{
            JOptionPane.showMessageDialog(null, "Login failed because of invalid username or password.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        }
	}
}