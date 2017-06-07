package search;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bamLogin.EncryptDecryptStringWithDES;

import com.google.gson.Gson;

import utility.DBConnection;

@WebServlet("/clientName")
public class PopulateClientList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String clientName = null;

		DBConnection dbConnection = new DBConnection();
		Statement st = null;
		ResultSet rs = null;
		String json=null;
		
		List<String> list = new ArrayList<String>();
		try {
			Connection connection = (Connection) dbConnection.dbConnection();
			st = connection.createStatement();
			String query = "SELECT distinct (client_name) FROM m_junction.process_state_table;";
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				clientName = rs.getString(1);
			
				
				list.add(clientName);
				
				
			}
			json = new Gson().toJson(list);
			System.out.println("Client Names :" + list);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
