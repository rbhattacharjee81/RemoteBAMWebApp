package bamLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import utility.DBConnection;

public class UpdateLoginDao {
	public static int getLoginUserDtls(String userid) {

		int count = 0;
		DBConnection dbConnection = new DBConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			Connection connection = (Connection) dbConnection.dbConnection();
			st = connection.createStatement();

			String query = "SELECT count(*) FROM userauth WHERE userid='"
					+ userid + "'";
			rs = st.executeQuery(query);

			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("Exception :" + e);

		}

		return count;

	}

	public static String firstLoginInsert(String userid, String authtoken) {

		String response = "";
		DBConnection dbConnection = new DBConnection();
		Statement st = null;

		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(
				calendar.getTime().getTime());

		Connection connection = (Connection) dbConnection.dbConnection();
		try {

			st = connection.createStatement();

			String query = "INSERT INTO m_junction.userauth (userid,authtoken,active,logintime) VALUES('"
					+ userid
					+ "','"
					+ authtoken
					+ "','"
					+ "Y"
					+ "','"
					+ ourJavaTimestampObject + "')";
			System.out.println("Query :" + query);
			st.executeUpdate(query);
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

	public static String updateLogin(String userid, String token) {

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

			ps.setString(1, token);
			ps.setString(2, "Y");
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
