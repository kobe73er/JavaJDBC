package SimpleJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SimpleJDBC {

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		Connection conection = DriverManager
				.getConnection("jdbc:mysql://localhost/test");
		java.sql.Statement statement=conection.createStatement();
		
		ResultSet rs=statement.executeQuery("select * from t1");
		while(rs.next()){
			System.out.print(rs.getString(1)+" ");
			System.out.print(rs.getString(2));
			System.out.println();
		}
		conection.close();
		

	}

}
