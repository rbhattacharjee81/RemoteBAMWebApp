package bamLogout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import utility.DBConnection;

public class LogoutDao {
	
	public static String updateLogut(String userid) {

		String response = "";
		DBConnection dbConnection = new DBConnection();
		Statement st = null;
		PreparedStatement ps = null;
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(
				calendar.getTime().getTime());

		Connection connection = (Connection) dbConnection.dbConnection();
		try {

			String updateAfterLogin = "UPDATE m_junction.userauth set authtoken=? , active=? , logintime=? where userid=? ";
			ps = connection.prepareStatement(updateAfterLogin);

			ps.setString(1, "");
			ps.setString(2, "N");
			ps.setTimestamp(3, ourJavaTimestampObject);
			ps.setString(4, userid);

			ps.executeUpdate();

			connection.close();

			response = "success";

		} catch (Exception e) {
			System.out.println("Exception :" + e);
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response = "fail";
			}
			response = "fail";
		}

		return response;

	}
}
