package bamLogin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utility.JsonResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String userName = request.getParameter("userName").trim();
		String passWord = request.getParameter("password").trim();
		String userRole = "";
		String userId = "";
		String loginResponse = "";
		int prevLoginTack = 0;
		String checkWebserviceOrDB = "";

		Properties prop = new Properties();
		InputStream input = null;
		Connection connection = null;
		try {

			input = new FileInputStream("D:\\config.properties");

			prop.load(input);

			checkWebserviceOrDB = prop.getProperty("webservicecall");

		} catch (Exception e) {
			System.out.println("Exception in reading properties file : " + e);
		}

		if (checkWebserviceOrDB.equalsIgnoreCase("Y")) {
			try {
				prevLoginTack = CheckPrevLogin.prevLoginDtls(userName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (prevLoginTack == 0) {
				List<LoginResponse> responseDb = LoginDao.loginAuth(userName,
						passWord);

				for (LoginResponse myPoint : responseDb) {

					if (myPoint.getResponse().equalsIgnoreCase("success")) {

						loginResponse = myPoint.getResponse();
						userRole = myPoint.getUserRole();
						userId = myPoint.getUserId();
					}
				}
				if (loginResponse.equalsIgnoreCase("success")) {
					SecureRandom random = new SecureRandom();
					byte bytes[] = new byte[8];
					random.nextBytes(bytes);
					String tokenGeneartion = bytes.toString();

					String encryptedToken = EncryptDecryptStringWithDES
							.encrypt(tokenGeneartion);
					String decryptedToken = EncryptDecryptStringWithDES
							.decrypt(encryptedToken);
					response.setContentType("text/plain");

					PrintWriter out = response.getWriter();
					out.println(encryptedToken);
					HttpSession session = request.getSession();

					session.setAttribute("username", userName);
					session.setAttribute("authtokenhash", encryptedToken);
					session.setAttribute("authtokennormal", decryptedToken);
					session.setAttribute("userrole", userRole);
					session.setAttribute("userid", userId);
					session.setAttribute("checkWebserviceOrDB",
							checkWebserviceOrDB);

					String updateDb = UpdateCurrentLogin.updateLogin(userId,
							encryptedToken);
					System.out.println(updateDb);

				}

				else {
					response.sendError(402, "error");
					response.setContentType("text/plain");

				}

			}

			else {
				response.sendError(403, "error");
				response.setContentType("text/plain");
			}
		}

		else if (checkWebserviceOrDB.equalsIgnoreCase("N")) {
			try {
				
				prevLoginTack = CheckPrevLogin.prevLoginDtls(userName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (prevLoginTack == 0) {
				List<LoginResponse> responseDb = LoginDao.loginAuth(userName,
						passWord);

				for (LoginResponse myPoint : responseDb) {

					if (myPoint.getResponse().equalsIgnoreCase("success")) {

						loginResponse = myPoint.getResponse();
						userRole = myPoint.getUserRole();
						userId = myPoint.getUserId();
					}
				}
				if (loginResponse.equalsIgnoreCase("success")) {
					SecureRandom random = new SecureRandom();
					byte bytes[] = new byte[8];
					random.nextBytes(bytes);
					String tokenGeneartion = bytes.toString();

					String encryptedToken = EncryptDecryptStringWithDES
							.encrypt(tokenGeneartion);
					String decryptedToken = EncryptDecryptStringWithDES
							.decrypt(encryptedToken);
					response.setContentType("text/plain");

					PrintWriter out = response.getWriter();
					out.println(encryptedToken);
					HttpSession session = request.getSession();

					session.setAttribute("username", userName);
					session.setAttribute("authtokenhash", encryptedToken);
					session.setAttribute("authtokennormal", decryptedToken);
					session.setAttribute("userrole", userRole);
					session.setAttribute("userid", userId);
					session.setAttribute("checkWebserviceOrDB",
							checkWebserviceOrDB);

					String updateDb = UpdateCurrentLogin.updateLogin(userId,
							encryptedToken);
					System.out.println(updateDb);

				}

				else {
					response.sendError(402, "error");
					response.setContentType("text/plain");

				}

			}

			else {
				response.sendError(403, "error");
				response.setContentType("text/plain");
			}

		}

	}

	// protected void doGet(HttpServletRequest request,
	// HttpServletResponse response) throws ServletException, IOException {
	//
	// String userName = request.getParameter("userName").trim();
	// String passWord = request.getParameter("password").trim();
	//
	//
	//
	//
	// SecureRandom random = new SecureRandom();
	// byte bytes[] = new byte[8];
	// random.nextBytes(bytes);
	// String tokenGeneartion = bytes.toString();
	//
	// String encryptedToken=
	// EncryptDecryptStringWithDES.encrypt(tokenGeneartion);
	// String decryptedToken =
	// EncryptDecryptStringWithDES.decrypt(encryptedToken);
	// if (userName != null && userName.equalsIgnoreCase("ari")) {
	//
	//
	//
	//
	//
	// System.out.println("TOKEN : " + encryptedToken);
	// response.setContentType("text/plain");
	//
	// PrintWriter out = response.getWriter();
	// out.println(encryptedToken);
	// HttpSession session = request.getSession();
	//
	// session.setAttribute("authtokenhash", encryptedToken);
	// session.setAttribute("authtokennormal", decryptedToken);
	// session.setAttribute("userrole", "admin");
	// }
	// else if (userName.equals("man")|| passWord.equals("")) {
	//
	//
	// System.out.println("TOKEN : " + encryptedToken);
	// response.setContentType("text/plain");
	//
	// PrintWriter out = response.getWriter();
	// out.println(encryptedToken);
	// HttpSession session = request.getSession();
	// session.setAttribute("authtokennormal", decryptedToken);
	// session.setAttribute("authtokenhash", encryptedToken);
	// session.setAttribute("userrole", "user");
	//
	// }
	// else if (userName.equals("")|| passWord.equals("")) {
	// response.sendError(401, "error");
	// response.setContentType("text/plain");
	//
	// }
	//
	// else {
	// response.sendError(402, "error");
	// response.setContentType("text/plain");
	//
	// }
	//
	// }

	public JsonResponse login(String userName, String password)
			throws IOException {

		String userRole = "";
		String userId = "";
		String loginResponse = "";
		int prevLoginTack = 0;
		String encryptedToken = null;
		JsonResponse jresponse = new JsonResponse();
		try {
			prevLoginTack = CheckPrevLogin.prevLoginDtls(userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (prevLoginTack == 0) {
			List<LoginResponse> responseDb = LoginDao.loginAuth(userName,
					password);

			for (LoginResponse myPoint : responseDb) {

				if (myPoint.getResponse().equalsIgnoreCase("success")) {

					loginResponse = myPoint.getResponse();
					userRole = myPoint.getUserRole();
					userId = myPoint.getUserId();
					encryptedToken = myPoint.getEncryptedToken();
				}
			}
			if (loginResponse.equalsIgnoreCase("success")) {

				String updateDb = UpdateCurrentLogin.updateLogin(userId,
						encryptedToken);
				System.out.println(updateDb);

				jresponse.setStatus("success");
				jresponse.setError("");
				jresponse.setResponse(responseDb);

			}

			else {
				jresponse.setStatus("fail");
				jresponse.setError("Wrong User Id or Password");
				jresponse.setResponse("");

			}

		}

		else {
			jresponse.setStatus("fail");
			jresponse.setError("User Already Logged In");
			jresponse.setResponse("");
		}
		// System.out.println("Json Response Sending: " +
		// jresponse.getResponse().toString());
		return jresponse;

	}

}
