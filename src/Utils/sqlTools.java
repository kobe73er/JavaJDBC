package Utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;

public class sqlTools {
	public static ArrayList queryById(int id, String queryString) {
		ArrayList arrayListOfQueryById = new ArrayList();

		Utils.initDBWithPreparedStatement(queryString);
		try {
			Utils.prepareStatement.setInt(1, id);
			ResultSet rs = Utils.prepareStatement.executeQuery();
			ResultSetMetaData rsMetaData = rs.getMetaData();
			// for(int i=1;i<=rsMetaData.getColumnCount();i++){
			// System.out.printf("%-12s\t",rsMetaData.getColumnName(i));
			// }
			System.out.println();
			while (rs.next()) {
				for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
					System.out.printf("%-12s\t", rs.getObject(i));
					arrayListOfQueryById.add(rs.getObject(i));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayListOfQueryById;

	}

}
