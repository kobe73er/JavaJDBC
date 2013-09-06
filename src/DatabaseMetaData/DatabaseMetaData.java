package DatabaseMetaData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseMetaData {

	public static void main(String[] args) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager
				.getConnection("jdbc:mysql://localhost/test");
		java.sql.DatabaseMetaData dbMetaData = conn.getMetaData();
		System.out.println("url===>" + dbMetaData.getURL());
		System.out.println(dbMetaData.getDatabaseProductName());
		System.out.println(dbMetaData.getDatabaseMajorVersion());
		System.out.println(dbMetaData.getDatabaseMinorVersion());
		System.out.println(dbMetaData.getDatabaseProductVersion());
		System.out.println(dbMetaData.getJDBCMajorVersion());
		System.out.println(dbMetaData.getJDBCMinorVersion());
		System.out.println(dbMetaData.getMaxConnections());
		System.out.println(dbMetaData.getMaxTableNameLength());
		System.out.println(dbMetaData.getMaxColumnsInTable());
		System.out.println(dbMetaData.getConnection());

		ResultSet rs = dbMetaData.getTables(null, null, null,
				new String[] { "TABLE" });
		while (rs.next()) {
			System.out.print(rs.getString("TABLE_NAME") + " ");
		}
		conn.close();

	}

}
