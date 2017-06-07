package search;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import utility.DBConnection;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import graphPieChart.ActivityGraphData;

@WebServlet("/activityPieServletClientSpc")
public class ActivityPieServletClientSpc extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ActivityPieServletClientSpc() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String clientName= request.getParameter("clientName");
		int graphPlotPie = populateActivityPieVisits(clientName);
		int populateFailureListPieChart = populateFailurePieChart(clientName);
		int populateUnderProcessListPieChart = populateUnderProcessPieChart(clientName);

	

		Gson gson = new Gson();

		// String jsonSuccess = gson.toJson(graphPlotPie);
		// String jsonFail = gson.toJson(populateFailureListPieChart);
		// String jsonUnder = gson.toJson(populateUnderProcessListPieChart);

		// JSONArray jsonAraaySuccess = new JSONArray(graphPlotPie);
		// JSONArray jsonAraayFail = new JSONArray(populateFailureListPieChart);
		// JSONArray jsonAraayUnder = new
		// JSONArray(populateUnderProcessListPieChart);
		//
		//
		//
		// String jsonStringSuceess = gson.toJson(graphPlotPie);
		// String jsonStringFailure = gson.toJson(populateFailureListPieChart);
		// String jsonStringUnder =
		// gson.toJson(populateUnderProcessListPieChart);
		Object[] arr1 = new Object[2];
		arr1[0]= new String ("Successful Activity");
		arr1[1] = new Integer(graphPlotPie);
		
		Object[] arr2 = new Object[2];
		
		arr2[0] = new String("Failed Activity");
		arr2[1] = new Integer(populateFailureListPieChart);
		
		Object[] arr3 = new Object[2];
		
		arr3[0] = new String("Under Processing Activity");
		arr3[1] = new Integer(populateUnderProcessListPieChart);
		
//		String[] dataSuccessful = { "Successful Activity", graphPlotPieString };
//		String[] dataFailed = { "Failed Activity",
//				stringpopulateFailureListPieChart };
//		String[] dataUnder = { "Under Processing Activity",
//				tringpopulateUnderProcessListPieChart };

		Object finalStringReturn[][] = { arr1,arr2,arr3 };
		
		//String finalStringReturnall[][][]={finalStringReturn};

		String json = gson.toJson(finalStringReturn);
		System.out.println("finalStringReturn : " + json);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	private int populateActivityPieVisits(String clientName) {

		// List<ActivitySuccessfullPieData> populateActivityPieDataList = new
		// ArrayList<ActivitySuccessfullPieData>();
		int successPieData = 0;
		DBConnection dbConnection = new DBConnection();
		Statement st = null;
		ResultSet rs = null;

		try {

			Connection connection = (Connection) dbConnection.dbConnection();
			st = connection.createStatement();
			String query = "SELECT (SELECT COUNT(distinct timestamp) FROM process_state_table WHERE  Final_status = 'Processed' and client_name = '"+clientName+"' )+(SELECT COUNT(distinct DateTimeStamp) from mj_report WHERE Final_status = 'Processed' and Client_Name= '"+clientName+"') AS SumCount";
			rs = st.executeQuery(query);

			while (rs.next()) {
				// ActivitySuccessfullPieData s1 = new
				// ActivitySuccessfullPieData();
				// s1.setSuccessfulActivity("Successful Activity");
				successPieData = (rs.getInt(1));

			}

		} catch (Exception e) {
			System.out.println(e);
		}

		// s1.setSuccessfulActivity("Successful Activity");
		// s1.setNoSuccessfulActivities(200);
		//
		//
		// populateActivityPieDataList.add(s1);

		return successPieData;
	}

	private int populateFailurePieChart(String clientName) {

		// List<ActivityFailurePieData> populateFailureListPieChart = new
		// ArrayList<ActivityFailurePieData>();
		// ActivityFailurePieData s1 = new ActivityFailurePieData();
		int failPieData = 0;
		DBConnection dbConnection = new DBConnection();
		Statement st = null;
		ResultSet rs = null;

		try {

			Connection connection = (Connection) dbConnection.dbConnection();
			st = connection.createStatement();
			String query = "SELECT (SELECT COUNT(distinct timestamp) FROM process_state_table WHERE  Final_status = 'Failure' and client_name = '"+clientName+"')+(SELECT COUNT(distinct DateTimeStamp) from mj_report WHERE Final_status = 'Failure' and Client_Name= '"+clientName+"') AS SumCount";
			rs = st.executeQuery(query);

			while (rs.next()) {
				// ActivityFailurePieData s1 = new ActivityFailurePieData();
				failPieData = rs.getInt(1);
				// s1.setNoFailureActivities(rs.getInt(1));
				//
				// populateFailureListPieChart.add(s1);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		// s1.setFailureActivity("Failure Activity");
		// s1.setNoFailureActivities(60);
		//
		//
		// populateFailureListPieChart.add(s1);
		//
		// System.out.println("Data Activity Graph : "
		// + populateFailureListPieChart);

		return failPieData;
	}

	private int populateUnderProcessPieChart(String clientName) {

		// List<ActivityUnderProcessPieData> populateUnderProcessPieChart = new
		// ArrayList<ActivityUnderProcessPieData>();
		// ActivityUnderProcessPieData s1 = new ActivityUnderProcessPieData();
		int underPieData = 0;
		DBConnection dbConnection = new DBConnection();
		Statement st = null;
		ResultSet rs = null;

		try {

			Connection connection = (Connection) dbConnection.dbConnection();
			st = connection.createStatement();
			String query = "SELECT (SELECT COUNT(distinct timestamp) FROM process_state_table WHERE  Final_status = '' and client_name = '"+clientName+"')+(SELECT COUNT(distinct DateTimeStamp) from mj_report WHERE Final_status = '' and Client_Name= '"+clientName+"') AS SumCount";
			rs = st.executeQuery(query);

			while (rs.next()) {
				// ActivityUnderProcessPieData s1 = new
				// ActivityUnderProcessPieData();
				// s1.setUnderProcessActivity("Under Process Activity");
				underPieData = (rs.getInt(1));

				// populateUnderProcessPieChart.add(s1);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		// s1.setUnderProcessActivity("Under Process Activity");
		// s1.setNoUnderProcessActivities(20);
		//
		// populateUnderProcessPieChart.add(s1);

		// System.out.println("Data Activity Graph : "
		// + populateUnderProcessPieChart);

		return underPieData;
	}
}
