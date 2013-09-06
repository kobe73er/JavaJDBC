package Utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class sqlTools {
	public static void queryById(int id,String queryString){
		Utils.initDBWithPreparedStatement(queryString);
		try {
			Utils.prepareStatement.setInt(1,id);
			ResultSet rs=Utils.prepareStatement.executeQuery();
			while(rs.next()){
				System.out.println(rs.getObject(1));
				System.out.println(rs.getObject(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
