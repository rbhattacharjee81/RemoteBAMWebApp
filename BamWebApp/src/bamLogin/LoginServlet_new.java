package bamLogin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login_new")
public class LoginServlet_new extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String dept = "";
	private String firstname = "";
	private String lastname = "";
	private String networkId = "";
	private String emailID = "";
	private String title = "";
	private String WWID = "";
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//HttpSession session = request.getSession();

		String networkUser = request.getRemoteUser();

		// To be commented during load testing

		//String user = networkUser.substring(3);

		// Added during load testing

		// String user = "mho10";	

		// List lstUserList = new ArrayList();
		String loginResponse = "";
		int prevLoginTack = 0;
		String checkWebserviceOrDB = "";

		Properties prop = new Properties();
		InputStream input = null;
		Connection connection = null;
		try

		{
			input = new FileInputStream("D:\\config.properties");

			prop.load(input);

			checkWebserviceOrDB = prop.getProperty("webservicecall");

	

		if (checkWebserviceOrDB.equalsIgnoreCase("Y")) {
			try {
				prevLoginTack = CheckPrevLogin.prevLoginDtls(networkUser);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String companyCode = "*";

			String criteria = "USERNAME";

			String values = networkUser;		

			Hashtable<String, String> env = new Hashtable<String, String>();

			String query = "(&(mjBusinessUnit=" + companyCode + ")";

			query += "(employeeType=" + "ACTIVE" + ")";

			if (criteria != null) {

				if (criteria.equalsIgnoreCase("USERNAME")) {
					query += "(mjUserName=" + values + ")";
				} else if (criteria.equalsIgnoreCase("LASTNAME")) {
					query += "(sn=" + values + ")";
				}

				else if (criteria.equalsIgnoreCase("FIRSTNAME")) {
					query += "(givenName=" + values + ")";
				}

			}

			query += ")";		

			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.ldap.LdapCtxFactory");

			env.put(Context.PROVIDER_URL,
					"ldap://10.4.27.59:389/o=mj,dc=jjedsed,dc=mj,dc=com");

			javax.naming.NamingEnumeration enumPeople;

			javax.naming.ldap.LdapContext ctx = new InitialLdapContext(env,
					null);

			javax.naming.directory.SearchControls ctls = new SearchControls();

			ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			enumPeople = ctx.search("", query, ctls);

			if (enumPeople != null && enumPeople.hasMore()) {
				while (enumPeople.hasMore()) {
					javax.naming.directory.SearchResult si = (SearchResult) enumPeople
							.next();

					Attributes attrs = si.getAttributes();

					Attribute attr = null;

					NamingEnumeration allIDs = attrs.getAll(); // getIDs shows
					// lesser info.

					if (attrs != null)

					{

						javax.naming.NamingEnumeration as = attrs.getIDs();

						// Values that are SUPPOSED to be NOT NULL.

						if (attrs.get("givenName") == null) {		
							firstname = "";
						} else {			
							firstname = (String) attrs.get("givenName").get();
						}

						if (attrs.get("sn") == null) {					
						lastname = "";
						} else {							
							lastname = (String) attrs.get("sn").get();

						}

						if (attrs.get("uid") == null) {							
							WWID = "";
						} else {				

							WWID = (String) (attrs.get("uid")).get();

							StringTokenizer token = new StringTokenizer(WWID,
									":");

						}

						// Values that MAY BE NULL.

						if (attrs.get("jnjMSUserName") != null)

							networkId = (String) (attrs.get("jnjMSUserName"))
									.get();

						System.out.println("Username:: "
								+ (attrs.get("jnjMSUserName")).get());

						if (attrs.get("mail") != null) {

							emailID = (String) (attrs.get("mail")).get();

							System.out.println
									("MailID:: "
											+ (attrs.get("mail")).get());

						} else {

							emailID = "N/A";

						}

						if (attrs.get("jnjMSDomain") != null)

							System.out.println("Domain Name:: "
									+ (attrs.get("jnjMSDomain")).get());

						if (attrs.get("title") != null) {

							title = (String) (attrs.get("title")).get();

							System.out.println("Title Name :: "
									+ (attrs.get("title")).get());

						} else {
							title = "N/A";
						}
					}
				}
			} else {
				
			}
		  } 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
			
		
		/*

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

	*/}

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

	
}
