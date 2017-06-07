package search;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webService.BamService;
import bamLogin.EncryptDecryptStringWithDES;
import dashBoard.Client;
import dashBoard.ClientOverAllDataService;
import dashBoard.DataTableObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/populateClientSpcParticularOverAllActivity")
public class PopulateClientSpcOverAllParticularActivityServlet extends
		HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String inSessionAuthtoken = (String) session
				.getAttribute("authtokennormal");
		String checkWebserviceOrDB = (String) session
				.getAttribute("checkWebserviceOrDB");

		System.out.println("inSessionAuthtoken : " + inSessionAuthtoken);
		String authTokenHtml = (String) session.getAttribute("authtokenhash");

		System.out.println("authTokenHtml : " + authTokenHtml);
		String htmlAuthDecrpted = EncryptDecryptStringWithDES
				.decrypt(authTokenHtml);

		String startDate = (String) session.getAttribute("startDate");// 08 sep
																		// 2016
		String endDate = (String) session.getAttribute("endDate");
		String clientName = request.getParameter("clientName");

//		String startDateLoc = null;
//		String endDateLoc = null;
//
//		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
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

		PrintWriter out = response.getWriter();
		if (checkWebserviceOrDB.equalsIgnoreCase("Y")) {

			response.setContentType("application/json");
			BamService bam = new BamService();
			String json = bam.populateOverAllActivityService(authTokenHtml,
					request, response);

			System.out.println("ABC called by service:" + json);
			out.print(json);
			out = response.getWriter();

		}

		else if (checkWebserviceOrDB.equalsIgnoreCase("N")) {
			if (authTokenHtml != null
					&& htmlAuthDecrpted.equalsIgnoreCase(inSessionAuthtoken)) {

				List<Client> listOfClient = ClientSpcOverAllDataService
						.getClientSpcListOverAll(startDate, endDate,
								clientName);

				DataTableObject dataTableObject = new DataTableObject();
				dataTableObject.setAaData(listOfClient);

				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(dataTableObject);
				System.out.println("ABC :" + json);
				out.print(json);
			} else {
				response.sendError(402, "error");
				response.setContentType("text/plain");
				System.out.println("Invalid Session");
			}

		}

		else {
			response.sendError(404, "error");
			response.setContentType("text/plain");
			System.out
					.println("Something went wrong..Please try after some time");
		}

	}

}
