/******************************************************************************
*	Program Author: Kishore Reddy Gujja for CSCI 6810 Java and the Internet	  *
*	Date: November, 2015													  *
*******************************************************************************/
import java.awt.*;     //including Java packages used by this program
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.lang.*;
import com.kishore.banking.*;

class PayeeBO extends JPanel implements ActionListener
{
    private JButton AddButton, EditButton, DeleteButton;
    private JTextField a_nameField, a_acNumField, a_add1Field, a_add2Field, a_add3Field, a_add4Field, a_phoneField;
    private JTextField e_nameField, e_acNumField, e_add1Field, e_add2Field, e_add3Field, e_add4Field, e_phoneField;
    private JComboBox EditBox, DeleteBox;
    private String Uname, AccountNumber, Name, AccountType;
    private List <Payee> payeeList = new ArrayList<Payee>();
    private Payee selctedPayee;
    private String [] payeeStringList;

    public PayeeBO(String UName, String CustomerName)
    {
        Uname = UName;
        //Add Payee
            JLabel addLabel = new JLabel("Add Payee");
            addLabel.setFont(new Font("TimesRoman", Font.BOLD, 18));

            JLabel a_nameLabel = new JLabel("Name: ");
            a_nameField = new JTextField(15);

            JLabel a_acNumLabel = new JLabel("Account Number:");
            a_acNumField = new JTextField(15);

            JLabel a_addressLabel = new JLabel("Address:");

            // JLabel a_add1Label = new JLabel("House Number:");
            a_add1Field = new JTextField(15);

            JLabel a_phoneLabel = new JLabel("Phone Number:");
            a_phoneField = new JTextField(15);

            AddButton = new JButton("Add");

        //Edit Payee
            JLabel editLabel = new JLabel("Edit Payee");
            editLabel.setFont(new Font("TimesRoman", Font.BOLD, 18));

            JLabel e_select = new JLabel("Select Payee:");

            Payee P = new Payee();

            EditBox = new JComboBox();

            JLabel e_nameLabel = new JLabel("Name: ");
            e_nameField = new JTextField(15);

            JLabel e_acNumLabel = new JLabel("Account Number:");
            e_acNumField = new JTextField(15);

            JLabel e_addressLabel = new JLabel("Address:");

            e_add1Field = new JTextField(15);

            JLabel e_phoneLabel = new JLabel("Phone Number:");
            e_phoneField = new JTextField(15);

            EditButton = new JButton("Edit");

        //Delete Payee
            JLabel deleteLabel = new JLabel("Delete Payee");
            deleteLabel.setFont(new Font("TimesRoman", Font.BOLD, 18));

            JLabel d_select = new JLabel("Select Payee:");

            DeleteBox = new JComboBox();

            DeleteButton = new JButton("Delete");

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

            //add
            add(addLabel, gbc, 0, 0, 1, 1);

            add(a_nameLabel, gbc, 0, 1, 1, 1);
            add(a_nameField, gbc, 1, 1, 1, 1);

            add(a_acNumLabel, gbc, 0, 2, 1, 1);
            add(a_acNumField, gbc, 1, 2, 1, 1);

            add(a_addressLabel, gbc, 0, 3, 1, 1);

            // add(a_add1Label, gbc, 0, 4, 1, 1);
            add(a_add1Field, gbc, 1, 3, 1, 1);

            add(a_phoneLabel, gbc, 0, 6, 1, 1);
            add(a_phoneField, gbc, 1, 6, 1, 1);

            add(AddButton, gbc, 2, 6, 1, 1);

            add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint, 0, 8, 1, 1);
            add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint, 1, 8, 1, 1);
            add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint, 2, 8, 1, 1);
            add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint, 3, 8, 1, 1);

            //edit
            add(editLabel, gbc, 0, 9, 1, 1);

            add(e_select, gbc, 0, 10, 1, 1);
            add(EditBox, gbc, 1, 10, 1, 1);

            add(e_nameLabel, gbc, 0, 11, 1, 1);
            add(e_nameField, gbc, 1, 11, 1, 1);

            add(e_acNumLabel, gbc, 0, 12, 1, 1);
            add(e_acNumField, gbc, 1, 12, 1, 1);

            add(e_addressLabel, gbc, 0, 13, 1, 1);

            // add(e_add1Label, gbc, 0, 14, 1, 1);
            add(e_add1Field, gbc, 1, 13, 1, 1);

            add(e_phoneLabel, gbc, 0, 16, 1, 1);
            add(e_phoneField, gbc, 1, 16, 1, 1);

            add(EditButton, gbc, 2, 16, 1, 1);

            add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint, 0, 18, 1, 1);
            add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint, 1, 18, 1, 1);
            add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint, 2, 18, 1, 1);
            add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint, 3, 18, 1, 1);
            //delete
            add(deleteLabel, gbc, 0, 19, 1, 1);

            add(d_select, gbc, 0, 20, 1, 1);
            add(DeleteBox, gbc, 1, 20, 1, 1);

            add(DeleteButton, gbc, 2, 20, 1, 1);

            AddButton.addActionListener(this);
            EditButton.addActionListener(this);
            DeleteButton.addActionListener(this);

            EditBox.addActionListener (new ActionListener () {
                public void actionPerformed(ActionEvent e) {
                    int i = EditBox.getSelectedIndex();
                    if(i != 0){
                        selctedPayee = payeeList.get(i-1);
                        e_nameField.setText(selctedPayee.getPayeeName());
                        e_add1Field.setText(selctedPayee.getPayeeAddress());
                        e_phoneField.setText(selctedPayee.getPayeePhoneNum());
                        e_acNumField.setText(selctedPayee.getPayerAcNum());
                    }
                }
            });
    }

    public void actionPerformed(ActionEvent evt)  //event handling
    {
        //Object source = evt.getSource(); //get who generates this event
        String PName, PAccNum, PAddress, PPhone;
        String arg = evt.getActionCommand();
        if (arg.equals("Add")) { //determine which button is clicked
            PName = a_nameField.getText();
            PAccNum= a_acNumField.getText();
            // PAddress = a_add1Field.getText() + "\n" + a_add2Field.getText() + "\n" + a_add3Field.getText() + "\n" + a_add4Field.getText();
            PAddress = a_add1Field.getText();
            PPhone= a_phoneField.getText();

            Payee p_add = new Payee(PName, PAccNum, PAddress, PPhone, Uname);

            if(p_add.addPayee()){
                getPList();
                JOptionPane.showMessageDialog(null, "Payee has been added successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                a_nameField.setText("");
                a_acNumField.setText("");
                a_add1Field.setText("");
                a_phoneField.setText("");
            }else{
                JOptionPane.showMessageDialog(null, "Couldn't add Payee! Try Again!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                a_nameField.setText("");
                a_acNumField.setText("");
                a_add1Field.setText("");
                a_phoneField.setText("");
            }
		} else if(arg.equals("Edit")){
            PName = e_nameField.getText();
            PAccNum= e_acNumField.getText();
            PAddress = e_add1Field.getText();
            PPhone= e_phoneField.getText();

            selctedPayee.setPayeeName(PName);
            selctedPayee.setPayeePhoneNum(PPhone);
            selctedPayee.setPayeeAddress(PAddress);
            selctedPayee.setPayerAcNum(PAccNum);

            if(selctedPayee.updatePayee()){
                getPList();
                JOptionPane.showMessageDialog(null, "Payee Info has been edited successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                e_nameField.setText("");
                e_acNumField.setText("");
                e_add1Field.setText("");
                e_phoneField.setText("");
            }else{
                JOptionPane.showMessageDialog(null, "Couldn't edit Payee Info! Try Again!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                e_nameField.setText("");
                e_acNumField.setText("");
                e_add1Field.setText("");
                e_phoneField.setText("");
            }
        }else if(arg.equals("Delete")){
            int i = DeleteBox.getSelectedIndex();
            if(i != 0){
                selctedPayee = payeeList.get(i-1);
                if(selctedPayee.deletePayee()){
                    getPList();
                    JOptionPane.showMessageDialog(null, "Deleted Payee successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Couldn't Delete Payee! Try Again!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
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
        payeeList = Payee.getPayeeList(Uname);
        payeeStringList = new String[payeeList.size() + 1];
        payeeStringList[0] = "Select the Payee";
        for (int i = 0; i < payeeList.size(); i++) {
            payeeStringList[i+1] = payeeList.get(i).getPayeeName();
        }
        DefaultComboBoxModel e_model = new DefaultComboBoxModel( payeeStringList );
        DefaultComboBoxModel d_model = new DefaultComboBoxModel( payeeStringList );
        EditBox.setModel(e_model);
        DeleteBox.setModel(d_model);
    }
}