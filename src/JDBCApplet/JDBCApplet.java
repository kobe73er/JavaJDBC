package JDBCApplet;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JDBCApplet extends JApplet {

	Statement statement;
	private JTextField jtfID = new JTextField(5);
	private JTextField jtfName = new JTextField(5);
	private JButton jbtShow = new JButton("Show Grade");

	public void init() {
		try {
			initDB();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JPanel jPanel1 = new JPanel();
		jPanel1.add(new JLabel("ID"));
		jPanel1.add(jtfID);
		jPanel1.add(new JLabel("name"));
		jPanel1.add(jtfName);
		jPanel1.add(jbtShow);

		jbtShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jbtShowActionPerformed();
			}
		});

		add(jPanel1, BorderLayout.NORTH);

	}

	protected void jbtShowActionPerformed() {
		// TODO Auto-generated method stub
		Set<String> set=new HashSet<String>();
		String ID = jtfID.getText().trim();
		String queryString = "select * from t1 where ID =" + ID;
		try {
			ResultSet rs = statement.executeQuery(queryString);
			//
			while (rs.next()) {
				String name = rs.getString(2);
				set.add(name);
				System.out.println(name);
			}
			jtfName.setText(set.toString());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void initDB() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost/test");
		statement = connection.createStatement();
	}

}
