package dashBoard;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bamLogin.EncryptDecryptStringWithDES;

import com.google.gson.Gson;

@WebServlet("/populateTotalActivity")
public class PopulateDetailsActivityServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String inSessionAuthtoken = (String) session
				.getAttribute("authtokennormal");
		System.out.println("inSessionAuthtoken : " + inSessionAuthtoken);
		String authTokenHtml = request.getParameter("tokenValue").trim();
		System.out.println("authTokenHtml : " + authTokenHtml);

		String htmlAuthDecrpted = EncryptDecryptStringWithDES
				.decrypt(authTokenHtml);
		try {

			if (authTokenHtml != null
					&& htmlAuthDecrpted.equalsIgnoreCase(inSessionAuthtoken)) {

				System.out.println("Valid Session : ");

				Map<String, String> options = new LinkedHashMap<>();
				options.put("totalActivities", "20");
				options.put("underProcessing", "30");
				options.put("successfulActivities", "14");
				options.put("failedActivities", "8");
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

		} catch (Exception e) {
			System.out.println("Exception : " + e);
			response.sendError(402, "error");
			response.setContentType("text/plain");
			response.getWriter().write("Exception Occured");
		}
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String inSessionAuthtoken = (String) session
				.getAttribute("authtokennormal");
		System.out.println("inSessionAuthtoken : " + inSessionAuthtoken);
		String authTokenHtml = request.getParameter("tokenValue").trim();
		System.out.println("authTokenHtml : " + authTokenHtml);

		String htmlAuthDecrpted = EncryptDecryptStringWithDES
				.decrypt(authTokenHtml);
		try {

			if (authTokenHtml != null
					&& htmlAuthDecrpted.equalsIgnoreCase(inSessionAuthtoken)) {

				System.out.println("Valid Session : ");

				Map<String, String> options = new LinkedHashMap<>();
				options.put("totalActivities", "20");
				options.put("underProcessing", "30");
				options.put("successfulActivities", "14");
				options.put("failedActivities", "8");
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

		} catch (Exception e) {
			System.out.println("Exception : " + e);
			response.sendError(402, "error");
			response.setContentType("text/plain");
			response.getWriter().write("Exception Occured");
		}
	}

}
