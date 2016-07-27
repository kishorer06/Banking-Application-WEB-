/******************************************************************************
*	Program Author: Anupama for CSCI 6810 Java and the Internet	  *
*	Date: November, 2015													  *
*******************************************************************************/
import java.awt.*;     //including Java packages used by this program
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.lang.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.kishore.banking.*;

class PaymentBO extends JPanel implements ActionListener
{
    private JButton ScheduleButton, EditButton, CancelButton;
    private JTextField s_amtField, s_dateField;
    private JTextField e_amtField, e_dateField;
    private JComboBox SBox, EBox, DeleteBox, SAccBox, EAccBox;
    private String Uname, AccountNumber, Name, AccountType;
    private List <Payee> payeeList = new ArrayList<Payee>();
    private List <Payment> paymentList = new ArrayList<Payment>();
    private String [] payeeStringList;
    private Payment selectedPayment;
    private String [] paymentStringList;
    private DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    private CheckingAccount CA = new CheckingAccount();
    private SavingsAccount SA = new SavingsAccount();

    public PaymentBO(String UName, String CustomerName)
    {
        //Schedule Payee
            Uname = UName;

            CA = CA.getAccountInfo(Uname);
            SA = SA.getAccountInfo(Uname);

            JLabel scheduleLabel = new JLabel("Schedule Payment");
            scheduleLabel.setFont(new Font("TimesRoman", Font.BOLD, 18));

            JLabel s_select = new JLabel("Select Payee:");

            payeeList = Payee.getPayeeList(Uname);
            payeeStringList = new String[payeeList.size() + 1];
            payeeStringList[0] = "Select the Payee";
            for (int i = 0; i < payeeList.size(); i++) {
                payeeStringList[i+1] = payeeList.get(i).getPayeeName();
            }
            SBox = new JComboBox(payeeStringList);

            JLabel s_account = new JLabel("From Account:");
            SAccBox = new JComboBox();
            SAccBox.addItem("Choose Account Type");
            SAccBox.addItem("Checking Account");
            SAccBox.addItem("Savings Account");

            JLabel s_amtLabel = new JLabel("Amount: ");
            s_amtField = new JTextField(15);

            JLabel s_dateLabel = new JLabel("Date:");
            s_dateField = new JTextField(15);
            s_dateField.setText("YYYY/MM/DD");

            ScheduleButton = new JButton("Schedule");

        //Edit Payee

            JLabel editLabel = new JLabel("Edit Payment");
            editLabel.setFont(new Font("TimesRoman", Font.BOLD, 18));

            JLabel e_select = new JLabel("Select Payment:");
            EBox = new JComboBox();
            EBox.addItem("Choose Payment");

            JLabel e_payeeLabel = new JLabel("Payee:");
            JLabel e_payee = new JLabel();

            JLabel e_account = new JLabel("From Account:");
            EAccBox = new JComboBox();
            EAccBox.addItem("Choose Account Type");
            EAccBox.addItem("Checking Account");
            EAccBox.addItem("Savings Account");

            JLabel e_amtLabel = new JLabel("Amount: ");
            e_amtField = new JTextField(15);

            JLabel e_dateLabel = new JLabel("Date:");
            e_dateField = new JTextField(15);
            e_dateField.setText("YYYY/MM/DD");

            EditButton = new JButton("Edit");

        //Delete Payee
            JLabel cancelLabel = new JLabel("Cancel Payment");
            cancelLabel.setFont(new Font("TimesRoman", Font.BOLD, 18));

            JLabel d_select = new JLabel("Select Payment:");
            DeleteBox = new JComboBox();
            DeleteBox.addItem("Choose Payment");

            CancelButton = new JButton("Cancel");
            getPList();
        // adding three panels to the Payee panel
            GridBagLayout Gridbg = new GridBagLayout();
            setLayout(Gridbg);
            GridBagConstraints gbc = new GridBagConstraints();
            GridBagConstraints separatorConstraint = new GridBagConstraints();

            separatorConstraint.weightx = 1;
            separatorConstraint.fill = GridBagConstraints.HORIZONTAL;
            // separatorConstraint.gridwidth = GridBagConstraints.REMAINDER;

            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 100;
            gbc.weighty = 100;

            //schedule
            add(scheduleLabel, gbc, 0, 0, 1, 1);

            add(s_select, gbc, 0, 1, 1, 1);
            add(SBox, gbc, 1, 1, 1, 1);

            add(s_account, gbc, 0, 2, 1, 1);
            add(SAccBox, gbc, 1, 2, 1, 1);

            add(s_amtLabel, gbc, 0, 3, 1, 1);
            add(s_amtField, gbc, 1, 3, 1, 1);

            add(s_dateLabel, gbc, 0, 4, 1, 1);
            add(s_dateField, gbc, 1, 4, 1, 1);

            add(ScheduleButton, gbc, 1, 5, 1, 1);

            add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint, 0, 6, 1, 1);
            add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint, 1, 6, 1, 1);
            add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint, 2, 6, 1, 1);
            add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint, 3, 6, 1, 1);

            //edit
            add(editLabel, gbc, 0, 7, 1, 1);

            add(e_select, gbc, 0, 8, 1, 1);
            add(EBox, gbc, 1, 8, 1, 1);

            add(e_payeeLabel, gbc, 0, 9, 1, 1);
            add(e_payee, gbc, 1, 9, 1, 1);

            add(e_account, gbc, 0, 10, 1, 1);
            add(EAccBox, gbc, 1, 10, 1, 1);

            add(e_amtLabel, gbc, 0, 11, 1, 1);
            add(e_amtField, gbc, 1, 11, 1, 1);

            add(e_dateLabel, gbc, 0, 12, 1, 1);
            add(e_dateField, gbc, 1, 12, 1, 1);

            add(EditButton, gbc, 1, 13, 1, 1);

            add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint, 0, 14, 1, 1);
            add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint, 1, 14, 1, 1);
            add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint, 2, 14, 1, 1);
            add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint, 3, 14, 1, 1);

            //delete
            add(cancelLabel, gbc, 0, 15, 1, 1);

            add(d_select, gbc, 0, 16, 1, 1);
            add(DeleteBox, gbc, 1, 16, 1, 1);

            add(CancelButton, gbc, 1, 17, 1, 1);

            ScheduleButton.addActionListener(this);
            EditButton.addActionListener(this);
            CancelButton.addActionListener(this);

            EBox.addActionListener (new ActionListener () {
                public void actionPerformed(ActionEvent e) {
                    int i = EBox.getSelectedIndex();
                    if(i != 0){
                        selectedPayment = paymentList.get(i-1);
                        Payee P = Payee.getPayeeInfo(selectedPayment.getPayeeID());
                        if((selectedPayment.getFromAccount()).equals(CA.getCANum())){
                            EAccBox.setSelectedIndex(1);
                        }else if((selectedPayment.getFromAccount()).equals(SA.getSANum())){
                            EAccBox.setSelectedIndex(2);
                        }else{
                            EAccBox.setSelectedIndex(0);
                        }

                        e_amtField.setText(String.valueOf(selectedPayment.getPaymentAmount()));

                        e_dateField.setText(formatter.format(selectedPayment.getPaymentDate()));
                    }
                }
            });
    }

    public void actionPerformed(ActionEvent evt)  //event handling
    {
        //Object source = evt.getSource(); //get who generates this event

        float Amt;
        java.util.Date P_Date;
        String Frm_Acc = "", Stat, Cust_ID;

        String arg = evt.getActionCommand();
        if (arg.equals("Schedule")) { //determine which button is clicked
            Amt = Float.parseFloat(s_amtField.getText());

            if(SAccBox.getSelectedIndex() == 1){
                Frm_Acc = CA.getCANum();
            }else if(SAccBox.getSelectedIndex() == 2){
                Frm_Acc = SA.getSANum();
            }else{

            }

            try{
                P_Date = formatter.parse(s_dateField.getText());
                Stat = "Initiated";

                Cust_ID = Uname;
                int index = SBox.getSelectedIndex();
                Payee selectedPayee = payeeList.get(index-1);

                Payment p_sec = new Payment(Amt, P_Date, selectedPayee.getPayeeId(), Frm_Acc, Stat, Cust_ID);

                if(p_sec.schedulePayment()){
                    getPList();
                    JOptionPane.showMessageDialog(null, "Payment Scheduled successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    SBox.setSelectedIndex(0);
                    SAccBox.setSelectedIndex(0);
                    s_amtField.setText("");
                    s_dateField.setText("YYYY/MM/DD");
                }else{
                    JOptionPane.showMessageDialog(null, "Couldn't Schedule Payment! Try Again!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    SBox.setSelectedIndex(0);
                    SAccBox.setSelectedIndex(0);
                    s_amtField.setText("");
                    s_dateField.setText("YYYY/MM/DD");
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }else if(arg.equals("Edit")){
            Amt = Float.parseFloat(e_amtField.getText());

            if(EAccBox.getSelectedIndex() == 1){
                Frm_Acc = CA.getCANum();
            }else if(EAccBox.getSelectedIndex() == 2){
                Frm_Acc = SA.getSANum();
            }else{

            }


            try{
                P_Date = formatter.parse(e_dateField.getText());

                selectedPayment.setPaymentAmount(Amt);
                selectedPayment.setPaymentDate(P_Date);
                selectedPayment.setFromAccount(Frm_Acc);

                if(selectedPayment.updatePayment()){
                    getPList();
                    JOptionPane.showMessageDialog(null, "Payment Updated successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    EAccBox.setSelectedIndex(0);
                    e_amtField.setText("");
                    e_dateField.setText("YYYY/MM/DD");
                }else{
                    JOptionPane.showMessageDialog(null, "Couldn't Update Payment! Try Again!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    EAccBox.setSelectedIndex(0);
                    e_amtField.setText("");
                    e_dateField.setText("YYYY/MM/DD");
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }else if(arg.equals("Cancel")){
            int i = DeleteBox.getSelectedIndex();
            if(i != 0){
                selectedPayment = paymentList.get(i-1);
                if(selectedPayment.cancelPayment()){
                    getPList();
                    JOptionPane.showMessageDialog(null, "Payment Cancelled successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Couldn't Cancel Payment! Try Again!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }else{

        }
    }

    public void add(Component c, GridBagConstraints gbc, int x, int y, int w, int h){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        add(c, gbc);
    }

     public void getPList(){
        paymentList = Payment.getPaymentList(Uname);
        Payee Pa_ = new Payee();
        paymentStringList = new String[paymentList.size() + 1];
        paymentStringList[0] = "Select the Payment";
        for (int i = 0; i < paymentList.size(); i++) {
            Pa_ = Pa_.getPayeeInfo(paymentList.get(i).getPayeeID());
            paymentStringList[i+1] = String.valueOf(paymentList.get(i).getPaymentAmount()) + " to " + Pa_.getPayeeName() + " on " + formatter.format(paymentList.get(i).getPaymentDate());
        }
        DefaultComboBoxModel e_model = new DefaultComboBoxModel( paymentStringList );
        DefaultComboBoxModel d_model = new DefaultComboBoxModel( paymentStringList );
        EBox.setModel(e_model);
        DeleteBox.setModel(d_model);
    }
}