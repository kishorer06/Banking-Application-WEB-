/******************************************************************************
*	Program Author: Vijay for CSCI 6810 Java and the Internet	  *
*	Date: November, 2015													  *
*******************************************************************************/
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import com.kishore.banking.*;

//including Java packages used by this program

class InquireTransactionControl extends JPanel {

	private JTextField from;
	private JTextField to;
	private JButton Inquire;
	String str1;
	String str2;
	String UserName;
	Account user;

	public InquireTransactionControl(String fromDate, String toDate, String UName) {
		str1 = fromDate;
		str2 = toDate;
		UserName = UName;
		user = new Account(UName);
	}

	public void Inquire() {
		Vector []arr = user.Inquire(str1, str2);
		Vector columnNames = arr[0];
		Vector data = arr[1];
		if(data.isEmpty()){
			JOptionPane.showMessageDialog(null,"No Transactions available", "Confirmation",JOptionPane.INFORMATION_MESSAGE);
		}else{
			JPanel panel = new JPanel();
			JTable table = new JTable(data, columnNames);
			TableColumn column;
			for (int i = 0; i < table.getColumnCount(); i++) {
				column = table.getColumnModel().getColumn(i);
				column.setMaxWidth(250);
			}
			JScrollPane scrollPane = new JScrollPane(table);
			panel.add(scrollPane);
			JFrame frame = new JFrame();
			frame.add(panel);
			frame.setSize(700, 200);
			frame.setVisible(true);
		}
	}


}