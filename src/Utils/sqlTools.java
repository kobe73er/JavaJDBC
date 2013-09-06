package Utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;

public class sqlTools {
	public static void queryById(int id, String queryString) {
		Utils.initDBWithPreparedStatement(queryString);
		try {
			Utils.prepareStatement.setInt(1, id);
			ResultSet rs = Utils.prepareStatement.executeQuery();
			ResultSetMetaData rsMetaData = rs.getMetaData();
			DatabaseMetaData dbMetaData = Utils.connection.getMetaData();
			for(int i=1;i<=rsMetaData.getColumnCount();i++){
				System.out.printf("%-12s\t",rsMetaData.getColumnName(i));
			}
			System.out.println();
			while (rs.next()) {
				for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
					System.out.printf("%-12s\t",rs.getObject(i));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
