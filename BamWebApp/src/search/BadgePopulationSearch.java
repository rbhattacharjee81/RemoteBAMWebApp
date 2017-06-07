package search;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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


@WebServlet("/badgePopulation")
public class BadgePopulationSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet (HttpServletRequest request,
     	HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String inSessionAuthtoken=(String)session.getAttribute(("authtokennormal"));
	//	System.out.println("inSessionAuthtoken : " + inSessionAuthtoken);
		String authTokenHtml = request.getParameter("tokenValue").trim();
		//System.out.println("authTokenHtml : " + authTokenHtml);
		String htmlAuthDecrpted = EncryptDecryptStringWithDES.decrypt(authTokenHtml);

		String clientName = request.getParameter("clientName");

		String startDate = (String) request.getParameter("fromDate");
		String endDate = (String) request.getParameter("toDate");
	
		if (authTokenHtml != null && htmlAuthDecrpted.equalsIgnoreCase(inSessionAuthtoken)) {
			String totalActivities = "";
			String underProcessingActivities = "";
			String successfulActivities = "";
			String failedActivities = "";
			
			try {

				DBConnection dbConnection = new utility.DBConnection();
				Statement st = null;
				ResultSet rs = null;
				Connection connection = (Connection) dbConnection.dbConnection();

		
				

				st = connection.createStatement();
				// for underProcessing
				String queryunderProcessing = "SELECT (SELECT COUNT(distinct timestamp) FROM process_state_table WHERE  Final_status = '')+(SELECT COUNT(distinct DateTimeStamp) from mj_report WHERE Final_status = '') AS SumCount";
				rs = st.executeQuery(queryunderProcessing);

				while (rs.next()) {
					

					underProcessingActivities = rs.getString(1);
				

				}
				
				// for successful
				String querySuccessful = "SELECT (SELECT COUNT(distinct timestamp) FROM process_state_table WHERE  Final_status = 'Processed')+(SELECT COUNT(distinct DateTimeStamp) from mj_report WHERE Final_status = 'Processed') AS SumCount";
				rs = st.executeQuery(querySuccessful);

				while (rs.next()) {
					

					successfulActivities = rs.getString(1);
				

				}
				// for failed 
				
				String queryFailed = "SELECT (SELECT COUNT(distinct timestamp) FROM process_state_table WHERE  Final_status = 'Failure')+(SELECT COUNT(distinct DateTimeStamp) from mj_report WHERE Final_status = 'Failure') AS SumCount";
				rs = st.executeQuery(queryFailed);

				while (rs.next()) {
					

					failedActivities = rs.getString(1);
				

				}
				
			

			} catch (Exception e) {
				System.out.println(e);
			}
			int totalActivityInt=0;
			int failedActivitiesInt = Integer.parseInt(failedActivities);
			int successfulActivitiesInt = Integer.parseInt(successfulActivities);
			int underProcessingActivitiesInt = Integer.parseInt(underProcessingActivities);
			totalActivityInt=failedActivitiesInt+underProcessingActivitiesInt+successfulActivitiesInt;
			totalActivities = String.valueOf(totalActivityInt);
			System.out.println("Valid Session : ");
			
			  Map<String, String> options = new LinkedHashMap<>();
			    options.put("totalActivities", totalActivities);
			    options.put("underProcessing", underProcessingActivities);
			    options.put("successfulActivities", successfulActivities);
			    options.put("failedActivities", failedActivities);
			    String json = new Gson().toJson(options);
			    System.out.println("OPTIONS :" + options);
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
		} 

		else {
			response.sendError(402, "error");
			response.setContentType("text/plain");
			System.out.println("Invalid Session");
		}
		
	}
	
	protected void doPost (HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String inSessionAuthtoken=(String)session.getAttribute(("authtokennormal"));
	//	System.out.println("inSessionAuthtoken : " + inSessionAuthtoken);
		String authTokenHtml = request.getParameter("tokenValue").trim();
		//System.out.println("authTokenHtml : " + authTokenHtml);
		String htmlAuthDecrpted = EncryptDecryptStringWithDES.decrypt(authTokenHtml);

		String clientName = request.getParameter("clientName");

		String startDateLoc = (String) request.getParameter("fromDate");
		String endDateLoc = (String) request.getParameter("toDate");
	
		String startDate = null;
		String endDate = null;
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date date = null;
		try {
			date = (Date) formatter.parse(startDateLoc);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
		startDate = newFormat.format(date);

		Date endDatedate = null;
		try {
			endDatedate = (Date) formatter.parse(endDateLoc);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		endDate = newFormat.format(endDatedate);
		
		if (authTokenHtml != null && htmlAuthDecrpted.equalsIgnoreCase(inSessionAuthtoken)) {
			
			String totalActivities = "";
			String underProcessingActivities = "";
			String successfulActivities = "";
			String failedActivities = "";
			
			try {

				DBConnection dbConnection = new utility.DBConnection();
				Statement st = null;
				ResultSet rs = null;
				Connection connection = (Connection) dbConnection.dbConnection();

		
				

				st = connection.createStatement();
				// for underProcessing
				String queryunderProcessing = "SELECT (SELECT COUNT(p.timestamp) FROM process_state_table p WHERE  p.Final_status = '' and p.client_name='"+clientName+"' and cast(p.timestamp as date) between '"+startDate+"' and '"+endDate+"')+(SELECT COUNT(m.DateTimeStamp) from mj_report m WHERE m.Final_status = '' and m.client_name='"+clientName+"' and cast(m.DateTimeStamp as date) between '"+startDate+"' and '"+endDate+"') AS SumCount";
				rs = st.executeQuery(queryunderProcessing);

				while (rs.next()) {
					

					underProcessingActivities = rs.getString(1);
				

				}
				
				// for successful
				String querySuccessful = "SELECT (SELECT COUNT(p.timestamp) FROM process_state_table p WHERE  p.Final_status = 'Processed' and p.client_name='"+clientName+"' and cast(p.timestamp as date) between '"+startDate+"' and '"+endDate+"')+(SELECT COUNT(m.DateTimeStamp) from mj_report m WHERE m.Final_status = 'Processed' and m.client_name='"+clientName+"' and cast(m.DateTimeStamp as date) between '"+startDate+"' and '"+endDate+"') AS SumCount";
				rs = st.executeQuery(querySuccessful);

				while (rs.next()) {
					

					successfulActivities = rs.getString(1);
				

				}
				// for failed 
				
				String queryFailed = "SELECT (SELECT COUNT(p.timestamp) FROM process_state_table p WHERE  p.Final_status = 'Failure' and p.client_name='"+clientName+"' and cast(p.timestamp as date) between '"+startDate+"' and '"+endDate+"')+(SELECT COUNT(m.DateTimeStamp) from mj_report m WHERE m.Final_status = 'Failure' and m.client_name='"+clientName+"' and cast(m.DateTimeStamp as date) between '"+startDate+"' and '"+endDate+"') AS SumCount";
				rs = st.executeQuery(queryFailed);

				while (rs.next()) {
					

					failedActivities = rs.getString(1);
				

				}
				
			

			} catch (Exception e) {
				System.out.println(e);
			}
			int totalActivityInt=0;
			int failedActivitiesInt = Integer.parseInt(failedActivities);
			int successfulActivitiesInt = Integer.parseInt(successfulActivities);
			int underProcessingActivitiesInt = Integer.parseInt(underProcessingActivities);
			totalActivityInt=failedActivitiesInt+underProcessingActivitiesInt+successfulActivitiesInt;
			totalActivities = String.valueOf(totalActivityInt);
			System.out.println("Valid Session : ");
			
			  Map<String, String> options = new LinkedHashMap<>();
			    options.put("totalActivities", totalActivities);
			    options.put("underProcessing", underProcessingActivities);
			    options.put("successfulActivities", successfulActivities);
			    options.put("failedActivities", failedActivities);
			   

				
			   
			    session.removeAttribute(startDate);
				session.removeAttribute(endDate);
				session.setAttribute("startDate", startDate);
				session.setAttribute("endDate", endDate);
			    String json = new Gson().toJson(options);
			    System.out.println("OPTIONS :" + options);
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
		} 

		else {
			response.sendError(402, "error");
			response.setContentType("text/plain");
			System.out.println("Invalid Session");
		}

	}
	
	
	

}
