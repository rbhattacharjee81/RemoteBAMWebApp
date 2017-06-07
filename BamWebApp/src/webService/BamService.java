package webService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONTokener;

import utility.AuthenticateService;
import utility.JsonResponse;
import bamLogin.LoginServlet_new;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dashBoard.Client;
import dashBoard.ClientFailedDataService;
import dashBoard.ClientOverAllDataService;
import dashBoard.ClientSuccessfulDataService;
import dashBoard.ClientTotalDataService;
import dashBoard.ClientUnderDataService;
import dashBoard.DataTableObject;
import dashBoard.PopulateFailedParticularActivityServlet;

@Path("/WebService")
public class BamService {

//	@POST
//	@Path("/loginService")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public String login(@QueryParam("username") String userName,
//			@QueryParam("password") String password) throws IOException,
//			JSONException {
//
//		LoginServlet loginservlet = new LoginServlet();
//		JsonResponse response = loginservlet.login(userName, password);
//		Gson gson = new Gson();
//		String json = gson.toJson(response);
//		System.out.println("New Json :" + json);
//		return json;
//
//	}
	
//	@POST
//	@Path("/loginService")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public String login(@QueryParam("username") String userName,
//			@QueryParam("password") String password) throws IOException {
//		String json = "";
//			
//			
//			Gson gson = new GsonBuilder().setPrettyPrinting().create();
//			//json = gson.toJson();
//			return json;
//
//	}

	@POST
	@Path("/populateFailedActivity")
	@Produces(MediaType.APPLICATION_JSON)
	public String populateFailedActivityService(
			@QueryParam("authtoken") String authToken,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws IOException {

		boolean authenticateService = AuthenticateService
				.authenticateServiceToken(authToken, request, response);
		String json = "";
		if (authenticateService == true) {
			List<Client> listOfClient = ClientFailedDataService
					.getClientFailedList();
			DataTableObject dataTableObject = new DataTableObject();
			dataTableObject.setAaData(listOfClient);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json = gson.toJson(dataTableObject);
			return json;
		} else {
			System.out.println("ADDING NEW MESSAGE :" + json);
			json = "Not Authenticated";
		}

		return json;

	}
	
	@POST
	@Path("/populateSuccesfullActivity")
	@Produces(MediaType.APPLICATION_JSON)
	public String populateSuccessfulActivityService(
			@QueryParam("authtoken") String authToken,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws IOException {

		boolean authenticateService = AuthenticateService
				.authenticateServiceToken(authToken, request, response);
		String json = "";
		if (authenticateService == true) {
			List<Client> listOfClient = ClientSuccessfulDataService
					.getClientListSuccessful();

			DataTableObject dataTableObject = new DataTableObject();
			dataTableObject.setAaData(listOfClient);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json = gson.toJson(dataTableObject);
			System.out.println("ABC :" + json);
		} else {
			json = "Not Authenticated";
		}

		return json;

	}
	
	@POST
	@Path("/populateOverallActivity")
	@Produces(MediaType.APPLICATION_JSON)
	public String populateOverAllActivityService(
			@QueryParam("authtoken") String authToken,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws IOException {

		boolean authenticateService = AuthenticateService
				.authenticateServiceToken(authToken, request, response);
		String json = "";
		if (authenticateService == true) {
			List<Client> listOfClient = ClientOverAllDataService
					.getClientListOverAll();

			DataTableObject dataTableObject = new DataTableObject();
			dataTableObject.setAaData(listOfClient);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			 json = gson.toJson(dataTableObject);
		} else {
			json = "Not Authenticated";
		}

		return json;

	}
	
	@POST
	@Path("/populateUnderProcessingActivity")
	@Produces(MediaType.APPLICATION_JSON)
	public String populateUnderProcessingActivityService(
			@QueryParam("authtoken") String authToken,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws IOException {

		boolean authenticateService = AuthenticateService
				.authenticateServiceToken(authToken, request, response);
		String json = "";
		if (authenticateService == true) {
			List<Client> listOfClient = ClientUnderDataService
					.getClientListUnder();

			DataTableObject dataTableObject = new DataTableObject();
			dataTableObject.setAaData(listOfClient);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json = gson.toJson(dataTableObject);
			System.out.println("ABC :" + json);
		} else {
			json = "Not Authenticated";
		}

		return json;

	}
	
	@POST
	@Path("/populateTotalParticularActivity")
	@Produces(MediaType.APPLICATION_JSON)
	public String populateTotalParticularActivityService(
			@QueryParam("authtoken") String authToken,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws IOException, SQLException {

		boolean authenticateService = AuthenticateService
				.authenticateServiceToken(authToken, request, response);
		String json = "";
		if (authenticateService == true) {
			List<Client> listOfClient = ClientTotalDataService.getClientList();

			DataTableObject dataTableObject = new DataTableObject();
			dataTableObject.setAaData(listOfClient);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json = gson.toJson(dataTableObject);
			System.out.println("ABC :" + json);
		} else {
			json = "Not Authenticated";
		}

		return json;

	}

	@POST
	@Path("/populateNoOfActivity")
	@Produces(MediaType.APPLICATION_JSON)
	public String populateNoOfActivitService(
			@QueryParam("authtoken") String authToken,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws IOException {

		boolean authenticateService = AuthenticateService
				.authenticateServiceToken(authToken, request, response);
		String json = "";
		if (authenticateService == true) {
			List<Client> listOfClient = ClientFailedDataService
					.getClientFailedList();
			DataTableObject dataTableObject = new DataTableObject();
			dataTableObject.setAaData(listOfClient);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json = gson.toJson(dataTableObject);
			return json;
		} else {
			json = "Not Authenticated";
		}

		return json;

	}
}
