package bamLogin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dashBoard.Client;
import utility.DBConnection;

public class LoginDao {

	public static List<LoginResponse> loginAuth(String username, String password) {

		List<LoginResponse> listOfResponse = new ArrayList<LoginResponse>();
		LoginResponse loginResponse = new LoginResponse();
		DBConnection dbConnection = new DBConnection();
		Statement st = null;
		ResultSet rs = null;
		Connection connection = (Connection) dbConnection.dbConnection();
		try {
			
			st = connection.createStatement();
			String useridDb = "";
			String userNameDB = "";
			String userPassowrdDb = "";
			String userRoleDb = "";

			String query = "SELECT * FROM user WHERE username='" + username
					+ "'";
			rs = st.executeQuery(query);

			while (rs.next()) {
				useridDb = rs.getString(1);
				userNameDB = rs.getString(2);
				userPassowrdDb = rs.getString(3);
				userRoleDb = rs.getString(4);

				System.out.println("userid :" + useridDb + " role :"
						+ userRoleDb);
				
			}

			if (userNameDB.equalsIgnoreCase(username)
					&& userPassowrdDb.equalsIgnoreCase(password)) {
				loginResponse = new LoginResponse();
				loginResponse.setResponse("success");
				loginResponse.setUserId(useridDb);
				loginResponse.setUserRole(userRoleDb);
				loginResponse.setEncryptedToken(EncryptDecryptImpl.createEcryptToken());
				listOfResponse.add(loginResponse);

			} else {
				loginResponse = new LoginResponse();
				loginResponse.setResponse("fail");
				loginResponse.setUserId("");
				loginResponse.setUserRole("");
				loginResponse.setEncryptedToken("");
				listOfResponse.add(loginResponse);
			}

		} catch (Exception e) {
			System.out.println("Exception :" + e);
			loginResponse = new LoginResponse();
			loginResponse.setResponse("fail");
			loginResponse.setUserId("");
			loginResponse.setUserRole("");
			loginResponse.setEncryptedToken("");
			listOfResponse.add(loginResponse);
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return listOfResponse;
	}
}
