package bamLogin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utility.DBConnection;

public class CheckPrevLoginDao {
	
	public static int prevLoginUserDtls(String username) throws SQLException {
		
		

		int count = 0;
		String authtoken = null;
		String active = null;
		DBConnection dbConnection = new DBConnection();
		Statement st = null;
		ResultSet rs = null;
		String userid=null;
		Connection connection = (Connection) dbConnection.dbConnection();
		
		try{
			
			st = connection.createStatement();

			String query = "SELECT userid FROM user WHERE username='"+ username + "'";
			rs = st.executeQuery(query);

			while (rs.next()) {
				userid=rs.getString(1);
				
			}
		}
		catch(Exception e){
			System.out.println("Exce :" + e);
			
		}
		
		
		try {
			
			
			
			st = connection.createStatement();

			String query = "SELECT authtoken,active FROM userauth WHERE userid='"+ userid + "'";
			rs = st.executeQuery(query);

			while (rs.next()) {
				authtoken=rs.getString(1);
				active = rs.getString(2);
			}
			if(active.equalsIgnoreCase("Y")){
				if(authtoken!=null){
					count++;
				}
			}
			
		} catch (Exception e) {
			System.out.println("Exception :" + e);

		}
		
		finally{
			connection.close();
		}

		return count;

	}

}
