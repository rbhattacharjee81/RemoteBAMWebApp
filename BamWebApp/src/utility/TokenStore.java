package utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
import utility.DBConnection;

import com.google.gson.Gson;

@WebServlet("/tokenStore")
public class TokenStore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String startDate = request.getParameter("fromDate");
		String endDate = request.getParameter("toDate");
		String clientName = request.getParameter("clientName");

//		session.setAttribute("startDate", startDate);
//		session.setAttribute("endDate", endDate);
//		session.setAttribute("clientName", clientName);
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    String dates=startDate+"`"+endDate;
	    String json = new Gson().toJson(dates);
	    response.getWriter().write(json);

	}

}
