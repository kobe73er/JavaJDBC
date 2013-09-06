package PreparedStatement;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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



public class Client extends JApplet {
	PreparedStatement statement;
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
		String ID = jtfID.getText().trim();
		
		try {
			for (int i = 0; i < 100; i++) {
				statement.setInt(1, i);
				String name=GenerateString.getRandomString(5);
				statement.setString(2, name);
				statement.executeUpdate();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void initDB() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost/test");
		String queryString = "select * from t1 where ID = ?";
		String insertString = "insert into t1 values(?,?)";

		statement = connection.prepareStatement(insertString);
	}

}
