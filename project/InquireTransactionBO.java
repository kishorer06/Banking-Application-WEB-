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
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

class InquireTransactionBO extends JPanel {
	private JTextField from;
	private JTextField to;
	private JButton Inquire;

	public InquireTransactionBO(final String UName) {

		JLabel fromLabel = new JLabel("From: ");
		from = new JTextField(20);
		from.setText("YYYY-MM-DD");

		JLabel toLabel = new JLabel("To: ");
		to = new JTextField(20);
		to.setText("YYYY-MM-DD");
		Inquire = new JButton("Inquire");

		to.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				String str1 = from.getText();
				String str2 = to.getText();
				InquireTransactionControl e = new InquireTransactionControl(str1, str2, UName);
				e.Inquire();
			}
		});
		Inquire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String str1 = from.getText();
				String str2 = to.getText();
				InquireTransactionControl e = new InquireTransactionControl(
						str1, str2, UName);
				e.Inquire();
			}
		});

		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 100;
		gbc.weighty = 100;

		add(fromLabel, gbc, 0, 1, 1, 1);
		add(from, gbc, 1, 1, 1, 1);

		add(toLabel, gbc, 0, 2, 1, 1);
		add(to, gbc, 1, 2, 1, 1);

		add(Inquire, gbc, 1, 3, 1, 1);
	}

	public void add(Component c, GridBagConstraints gbc, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		add(c, gbc);
	}
}