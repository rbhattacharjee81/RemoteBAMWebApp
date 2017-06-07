package search;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utility.DBConnection;

import com.google.gson.Gson;

import graphPieChart.ActivityGraphData;

@WebServlet("/activityGraphServletClientSpc")
public class ActivityGraphServletClientSpc extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ActivityGraphServletClientSpc() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String clientName = request.getParameter("clientName");

		String startDate = (String) session.getAttribute("startDate");
		String endDate = (String) session.getAttribute("endDate");
		
		
		List<ActivityGraphData> graphPlotActivityListSuccess = null;
		try {
			graphPlotActivityListSuccess = populateActivitySuccess(clientName, startDate, endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ActivityGraphData> graphPlotActivityListFail = null;
		try {
			graphPlotActivityListFail = populateActivityFailure(clientName, startDate, endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ActivityGraphData> graphPlotActivityListUnderProcessing = null;
		try {
			graphPlotActivityListUnderProcessing = populateActivityUnderProcessing(clientName, startDate, endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();

		String jsonStringSuceess = gson.toJson(graphPlotActivityListSuccess);
		String jsonStringFailure = gson.toJson(graphPlotActivityListFail);
		String jsonStringUnder = gson.toJson(graphPlotActivityListUnderProcessing);
		String s = "PriceTicks";
		response.setContentType("application/json");
		System.out.println("jsonString :" + "{" + '"' + s + '"' + ":" + jsonStringSuceess + "}");
		String finalJsonSuccess = "{" + '"' + s + '"' + ":" + jsonStringSuceess + "}";
		String finalJsonFailure = "{" + '"' + s + '"' + ":" + jsonStringFailure + "}";
		String finalJsonUnder = "{" + '"' + s + '"' + ":" + jsonStringUnder + "}";
		String bothJson = "[" + finalJsonSuccess + "," + finalJsonFailure + "," + finalJsonUnder + "]";
		System.out.println("All JSON : " + bothJson);
		response.getWriter().write(bothJson);

	}

	private List<ActivityGraphData> populateActivitySuccess(String clientName, String startDate, String endDate)
			throws ParseException {

		List<ActivityGraphData> populateActivityGraphDataList = new ArrayList<ActivityGraphData>();
//		String startDateLoc = null;
//		String endDateLoc = null;
//		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
//		Date date = null;
//		try {
//			date = (Date) formatter.parse(startDate);
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
//		startDateLoc = newFormat.format(date);
//
//		Date endDatedate = null;
//		try {
//			endDatedate = (Date) formatter.parse(endDate);
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		endDateLoc = newFormat.format(endDatedate);

		DBConnection dbConnection = new DBConnection();
		Statement st = null;
		ResultSet rs = null;

		int noOfsuccessActi = 0;

		java.util.LinkedList hitList = searchBetweenDates(

				new java.text.SimpleDateFormat("yyyy-MM-dd").parse(endDate),

				new java.text.SimpleDateFormat("yyyy-MM-dd").parse(startDate));

		String[] comboDates = new String[hitList.size()];

		for (int i = 0; i < hitList.size(); i++)

			comboDates[i] = new java.text.SimpleDateFormat("yyyy-MM-dd").format(((java.util.Date) hitList.get(i)));

		for (int i = 0; i < comboDates.length; i++) {
			// System.out.println(comboDates[i]);
			String locDates = comboDates[i];
			System.out.println("LOC DATES : " + locDates);

			try {

				Connection connection = (Connection) dbConnection.dbConnection();
				st = connection.createStatement();
				String query = "select( SELECT COUNT(*) FROM process_state_table WHERE Final_status = 'Processed' and client_name = '"
						+ clientName + "' and cast(TIMESTAMP as date)='" + locDates
						+ "') + (SELECT COUNT(*) FROM mj_report WHERE Final_status = 'Processed' and Client_Name= '"
						+ clientName + "' and cast(DateTimeStamp as date)='" + locDates + "') as sumcount";
				rs = st.executeQuery(query);

				while (rs.next()) {
					ActivityGraphData s1 = new ActivityGraphData();
					noOfsuccessActi = rs.getInt(1);
					s1.setNoOfActivities(noOfsuccessActi);
					s1.setAllDates(locDates);

					populateActivityGraphDataList.add(s1);
				}

			} catch (Exception e) {
				System.out.println(e);
			}

		}

		// ActivityGraphData s2 = new ActivityGraphData();
		// s2.setNoOfActivities(300);
		// s2.setAllDates("26-08-16");
		//
		// populateActivityGraphDataList.add(s2);
		//
		// ActivityGraphData s3 = new ActivityGraphData();
		// s3.setNoOfActivities(400);
		// s3.setAllDates("27-08-16");
		//
		// populateActivityGraphDataList.add(s3);
		//
		// ActivityGraphData s4 = new ActivityGraphData();
		// s4.setNoOfActivities(500);
		// s4.setAllDates("28-08-16");
		// populateActivityGraphDataList.add(s4);
		//
		// populateActivityGraphDataList.add(s1);
		// populateActivityGraphDataList.add(s2);
		// populateActivityGraphDataList.add(s3);
		// populateActivityGraphDataList.add(s4);
		// System.out.println("Data Activity Graph : " +
		// populateActivityGraphDataList);

		return populateActivityGraphDataList;
	}

	private List<ActivityGraphData> populateActivityUnderProcessing(String clientName, String startDate, String endDate)
			throws ParseException {

		List<ActivityGraphData> populateActivityGraphDataListUnique = new ArrayList<ActivityGraphData>();
		// ActivityGraphData s1 = new ActivityGraphData();
		// s1.setNoOfActivities(720);
		// s1.setAllDates("25-08-16");
		//
		// populateActivityGraphDataListUnique.add(s1);
		//
		// ActivityGraphData s2 = new ActivityGraphData();
		// s2.setNoOfActivities(100);
		// s2.setAllDates("26-08-16");
		//
		// populateActivityGraphDataListUnique.add(s2);
		//
		// ActivityGraphData s3 = new ActivityGraphData();
		// s3.setNoOfActivities(450);
		// s3.setAllDates("27-08-16");
		//
		// populateActivityGraphDataListUnique.add(s3);
		//
		// ActivityGraphData s4 = new ActivityGraphData();
		// s4.setNoOfActivities(50);
		// s4.setAllDates("28-08-16");
		// populateActivityGraphDataListUnique.add(s4);
		//
		// populateActivityGraphDataListUnique.add(s1);
		// populateActivityGraphDataListUnique.add(s2);
		// populateActivityGraphDataListUnique.add(s3);
		// populateActivityGraphDataListUnique.add(s4);
//		String startDateLoc = null;
//		String endDateLoc = null;
//		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
//		Date date = null;
//		try {
//			date = (Date) formatter.parse(startDate);
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
//		startDateLoc = newFormat.format(date);
//
//		Date endDatedate = null;
//		try {
//			endDatedate = (Date) formatter.parse(endDate);
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		endDateLoc = newFormat.format(endDatedate);

		DBConnection dbConnection = new DBConnection();
		Statement st = null;
		ResultSet rs = null;

		int noOfsuccessActi = 0;

		java.util.LinkedList hitList = searchBetweenDates(

				new java.text.SimpleDateFormat("yyyy-MM-dd").parse(startDate),

				new java.text.SimpleDateFormat("yyyy-MM-dd").parse(endDate));

		String[] comboDates = new String[hitList.size()];

		for (int i = 0; i < hitList.size(); i++)

			comboDates[i] = new java.text.SimpleDateFormat("yyyy-MM-dd").format(((java.util.Date) hitList.get(i)));

		for (int i = 0; i < comboDates.length; i++) {
			// System.out.println(comboDates[i]);
			String locDates = comboDates[i];
			System.out.println("LOC DATES : " + locDates);

			try {

				Connection connection = (Connection) dbConnection.dbConnection();
				st = connection.createStatement();
				String query = "select( SELECT COUNT(*) FROM process_state_table WHERE Final_status = '' and client_name = '"
						+ clientName + "' and cast(TIMESTAMP as date)='" + locDates
						+ "') + (SELECT COUNT(*) FROM mj_report WHERE Final_status = '' and Client_Name= '" + clientName
						+ "' and cast(DateTimeStamp as date)='" + locDates + "') as sumcount";
				rs = st.executeQuery(query);

				while (rs.next()) {
					ActivityGraphData s1 = new ActivityGraphData();
					noOfsuccessActi = rs.getInt(1);
					s1.setNoOfActivities(noOfsuccessActi);
					s1.setAllDates(locDates);

					populateActivityGraphDataListUnique.add(s1);
				}

			} catch (Exception e) {
				System.out.println(e);
			}

		}
		System.out.println("Data Activity Graph : " + populateActivityGraphDataListUnique);

		return populateActivityGraphDataListUnique;
	}

	private List<ActivityGraphData> populateActivityFailure(String clientName, String startDate, String endDate)
			throws ParseException {

		List<ActivityGraphData> populateActivityGraphDataListUnique = new ArrayList<ActivityGraphData>();
		// DateFormat dateFormat = new SimpleDateFormat("dd-mm-yy");
		// Date myDate = new Date(System.currentTimeMillis());
		// String startDate = dateFormat.format(myDate);
		// System.out.println("startDate : " + startDate);
		// Calendar cal = Calendar.getInstance();
		// cal.setTime(myDate);
		// cal.add(Calendar.DATE, -4);
		// String endDate = dateFormat.format(cal.getTime());
		// System.out.println("endDate : " + endDate);

//		String startDateLoc = null;
//		String endDateLoc = null;
//		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
//		Date date = null;
//		try {
//			date = (Date) formatter.parse(startDate);
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
//		startDateLoc = newFormat.format(date);
//
//		Date endDatedate = null;
//		try {
//			endDatedate = (Date) formatter.parse(endDate);
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		endDateLoc = newFormat.format(endDatedate);

		DBConnection dbConnection = new DBConnection();
		Statement st = null;
		ResultSet rs = null;

		int noOfsuccessActi = 0;

		java.util.LinkedList hitList = searchBetweenDates(

				new java.text.SimpleDateFormat("yyyy-MM-dd").parse(endDate),

				new java.text.SimpleDateFormat("yyyy-MM-dd").parse(startDate));

		String[] comboDates = new String[hitList.size()];

		for (int i = 0; i < hitList.size(); i++)

			comboDates[i] = new java.text.SimpleDateFormat("yyyy-MM-dd").format(((java.util.Date) hitList.get(i)));

		for (int i = 0; i < comboDates.length; i++) {
			// System.out.println(comboDates[i]);
			String locDates = comboDates[i];
			System.out.println("LOC DATES : " + locDates);

			try {

				Connection connection = (Connection) dbConnection.dbConnection();
				st = connection.createStatement();
				String query = "select( SELECT COUNT(*) FROM process_state_table WHERE Final_status = 'Failure' and client_name = '"
						+ clientName + "' and cast(TIMESTAMP as date)='" + locDates
						+ "') + (SELECT COUNT(*) FROM mj_report WHERE Final_status = 'Failure' and Client_Name= '"
						+ clientName + "' and cast(DateTimeStamp as date)='" + locDates + "') as sumcount";
				System.out.println("Fetch Activity Qyery : " + query);
				rs = st.executeQuery(query);

				while (rs.next()) {
					ActivityGraphData s1 = new ActivityGraphData();
					noOfsuccessActi = rs.getInt(1);
					s1.setNoOfActivities(noOfsuccessActi);
					s1.setAllDates(locDates);

					populateActivityGraphDataListUnique.add(s1);
				}

			} catch (Exception e) {
				System.out.println(e);
			}

		}
		System.out.println("Data Activity Graph : " + populateActivityGraphDataListUnique);

		return populateActivityGraphDataListUnique;
	}

	public static java.util.LinkedList searchBetweenDates(java.util.Date startDate, java.util.Date endDate)

	{

		java.util.Date begin = new Date(startDate.getTime());

		java.util.LinkedList list = new java.util.LinkedList();

		list.add(new Date(begin.getTime()));

		while (begin.compareTo(endDate) < 0)

		{

			begin = new Date(begin.getTime() + 86400000);

			list.add(new Date(begin.getTime()));

		}

		return list;

	}
}
