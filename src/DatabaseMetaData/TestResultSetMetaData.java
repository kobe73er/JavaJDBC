package DatabaseMetaData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class TestResultSetMetaData {

	public static void main(String[] args) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager
				.getConnection("jdbc:mysql://localhost/test");

		Statement statement = conn.createStatement();

		ResultSet rs = statement.executeQuery("select * from t1");
		ResultSetMetaData rsMetaData = rs.getMetaData();
		
		for(int i=1;i<=rsMetaData.getColumnCount();i++){
			System.out.printf("%-12s\t",rsMetaData.getColumnName(i));
		}
		System.out.println();
		while(rs.next()){
			for(int i=1;i<=rsMetaData.getColumnCount();i++)
				System.out.printf("%-12s\t",rs.getObject(i));
			System.out.println();
		}
		
		conn.close();

	}

}
