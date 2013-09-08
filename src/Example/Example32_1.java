package Example;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Utils.Utils;
import Utils.sqlTools;

public class Example32_1 extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<String> IdArrayList = new ArrayList<String>();
	static String[] StringArrayInJComboBox;
	static JComboBox<String> JComboBoxOfId = new JComboBox<String>();

	public void init() {
		String insertString = "insert into staff values(?,?,?,?,?,?,?)";
		Utils.InsertSomeDataIntoTable("staff", 100, insertString);

		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(new initJCombox());
		executor.shutdown();

		JButton btnSearch = new JButton("search");
		JButton btnInsert = new JButton("insert");
		JButton btnUpdate = new JButton("udpate");
		JButton btnClear = new JButton("clear");

		JPanel jpanelButton = new JPanel();

		jpanelButton.add(btnSearch);
		jpanelButton.add(btnInsert);
		jpanelButton.add(btnUpdate);
		jpanelButton.add(btnClear);

		final JTextField jtfLastName = new JTextField(5);
		final JTextField jtfAddress = new JTextField(5);
		final JTextField jtfCity = new JTextField(5);
		final JTextField jtfState = new JTextField(5);
		final JTextField jtfTelephone = new JTextField(10);

		final JTextField[] jtfArray = { jtfLastName, jtfAddress, jtfCity, jtfState,
				jtfTelephone };

		JPanel jpanel1 = new JPanel();

		jpanel1.add(new JLabel("ID"));
		jpanel1.add(JComboBoxOfId);
		jpanel1.add(new JLabel("LastName"));
		jpanel1.add(jtfLastName);
		jpanel1.add(new JLabel("Address"));
		jpanel1.add(jtfAddress);
		jpanel1.add(new JLabel("City"));
		jpanel1.add(jtfCity);
		jpanel1.add(new JLabel("State"));
		jpanel1.add(jtfState);
		jpanel1.add(new JLabel("Telephone"));
		jpanel1.add(jtfTelephone);

		add(jpanel1);
		add(jpanelButton, BorderLayout.SOUTH);

		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				int ID = Integer.parseInt((String) JComboBoxOfId
						.getSelectedItem());
				String s[] = Utils.ConvertArrayListToStringList(sqlTools
						.queryById(ID, "select * from staff where id = ?"));
				String id = s[0];
				String lastname = s[1];
				String firstname = s[2];
				String mi = s[3];
				String address = s[4];
				String state = s[5];
				String telephone = s[6];

				jtfLastName.setText(lastname);
				jtfAddress.setText(address);
				jtfState.setText(state);
				jtfCity.setText(state);
				jtfTelephone.setText(telephone);
			}
		});

		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int i=0;i<jtfArray.length;i++){
					jtfArray[i].setText("");
				}
			}
		});
		JComboBoxOfId.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent itemtext) {

			}
		});

	}

	public static class initJCombox implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Utils.initDBWithStatement();
				ResultSet rsID = Utils.statement
						.executeQuery("select id from staff");
				while (rsID.next()) {
					IdArrayList.add(rsID.getString(1));
				}
				Collections.sort(IdArrayList);
				StringArrayInJComboBox = new String[IdArrayList.size()];
				int i = 0;
				for (String str : IdArrayList) {
					StringArrayInJComboBox[i++] = str;
				}
				for (String s : StringArrayInJComboBox)
					JComboBoxOfId.addItem(s);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}
}