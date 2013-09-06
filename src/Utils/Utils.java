package Utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Random;

public class Utils {
	public static Statement statement;
	public static PreparedStatement prepareStatement;
	private static HashSet<String> HashsetOfTableName = new HashSet<String>();

	public enum Status {
		EXSITS, NOTEXSITS
	};

	public static Status JudgeTableExsitsOrNot(String tablename) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost/test");
			DatabaseMetaData dbMetaData = connection.getMetaData();
			ResultSet rs = dbMetaData.getTables(null, null, null,
					new String[] { "TABLE" });
			while (rs.next()) {
				HashsetOfTableName.add(rs.getString("TABLE_NAME"));
			}
			for (String iterator : HashsetOfTableName) {
				if (iterator.equals(tablename)) {
					System.out.println("table exsits");
					Utils.initDBWithStatement();
					System.out.println("Execute delete from " + tablename);
					Utils.statement.executeUpdate("delete from " + tablename);
					return Status.EXSITS;
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("table not exsits");
		return Status.NOTEXSITS;

	}

	public static void initDBWithPreparedStatement(String sqlString) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost/test");

			prepareStatement = connection.prepareStatement(sqlString);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void initDBWithStatement() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost/test");
			statement = connection.createStatement();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String GenerateString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static String GenerateNumber(int length) { // length表示生成字符串的长度
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static void InsertSomeDataIntoTable(String tablename, int rownum,
			String sqlString) {

		JudgeTableExsitsOrNot(tablename);

		initDBWithPreparedStatement(sqlString);

		try {
			for (int i = 1; i <= rownum; i++) {

				String lastname = GenerateString(5);
				String firstname = GenerateString(5);
				String mi = GenerateString(1);
				String address = GenerateString(6);
				String state = GenerateString(2);
				String telephone = GenerateNumber(8);

				prepareStatement.setInt(1, i);
				prepareStatement.setString(2, firstname);
				prepareStatement.setString(3, lastname);
				prepareStatement.setString(4, mi);
				prepareStatement.setString(5, address);
				prepareStatement.setString(6, state);
				prepareStatement.setString(7, telephone);

				prepareStatement.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
