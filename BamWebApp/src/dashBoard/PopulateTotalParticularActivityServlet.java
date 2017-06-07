package dashBoard;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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

import org.codehaus.jettison.json.JSONArray;

import webService.BamService;
import bamLogin.EncryptDecryptStringWithDES;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/populateParticularTotalActivity")
public class PopulateTotalParticularActivityServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String inSessionAuthtoken = (String) session.getAttribute("authtokennormal");
		String checkWebserviceOrDB = (String) session.getAttribute("checkWebserviceOrDB");

		System.out.println("inSessionAuthtoken : " + inSessionAuthtoken);
		String authTokenHtml = request.getParameter("tokenValue");

		System.out.println("authTokenHtml : " + authTokenHtml);
		String htmlAuthDecrpted = EncryptDecryptStringWithDES
				.decrypt(authTokenHtml);
		PrintWriter out = response.getWriter();
		
		if (checkWebserviceOrDB.equalsIgnoreCase("Y")) {

			response.setContentType("application/json");

			BamService bam = new BamService();
			String json = null;
			try {
				json = bam.populateTotalParticularActivityService(authTokenHtml,
						request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("ABC called by service:" + json);
			out.print(json);
		}
		
		else if (checkWebserviceOrDB.equalsIgnoreCase("N")) {
			if (authTokenHtml != null
					&& htmlAuthDecrpted.equalsIgnoreCase(inSessionAuthtoken)) {
				response.setContentType("application/json");
				List<Client> listOfClient = null;
				try {
					listOfClient = ClientTotalDataService.getClientList();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
