package dashBoard;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

import utility.JsonResponse;
import webService.BamService;
import bamLogin.EncryptDecryptStringWithDES;
import bamLogin.LoginServlet_new;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@WebServlet("/populateParticularFailedActivity")
public class PopulateFailedParticularActivityServlet extends HttpServlet {

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
			String json = bam.populateFailedActivityService(authTokenHtml,
					request, response);

			System.out.println("ABC called by service:" + json);
			out.print(json);
		} else if (checkWebserviceOrDB.equalsIgnoreCase("N")) {
			if (authTokenHtml != null
					&& htmlAuthDecrpted.equalsIgnoreCase(inSessionAuthtoken)) {
				response.setContentType("application/json");
				List<Client> listOfClient = ClientFailedDataService
						.getClientFailedList();

				DataTableObject dataTableObject = new DataTableObject();
				dataTableObject.setAaData(listOfClient);

				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(dataTableObject);
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
