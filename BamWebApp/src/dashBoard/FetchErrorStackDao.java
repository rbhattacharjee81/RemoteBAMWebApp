package dashBoard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import utility.DBConnection;


public class FetchErrorStackDao {
	
	public static Map errorStack(String catCode, String timeStamp){
		
		Map<String, String> unitMap = new HashMap<String, String>();
		try {
			DBConnection dbConnection = new utility.DBConnection();
			Statement st = null;
			ResultSet rs = null;
			Connection connection = (Connection) dbConnection.dbConnection();
		    
			String reason = "";
			String exceptionLog = "";

			st = connection.createStatement();
			String query = "SELECT m.Stage_state,m.Error_description  FROM m_junction.error_table m where m.Type='" + catCode + "' and m.date='" + timeStamp + "' union SELECT b.Stage_state,b.Error_description  FROM m_junction.business_error_table b where b.Type='" + catCode + "' and b.date='" + timeStamp + "'";
			rs = st.executeQuery(query);

			while (rs.next()) {
				reason = rs.getString(1);
				exceptionLog = rs.getString(2);
				unitMap.put(reason, exceptionLog);
			}
			
			


		} catch (Exception e) {
			System.out.println(e);
		}
		return unitMap;
	}

}
