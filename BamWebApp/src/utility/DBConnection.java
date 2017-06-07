package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

	public Connection dbConnection() {
		Properties prop = new Properties();
		InputStream input = null;
		Connection connection = null;
		try {

			input = new FileInputStream("D:\\config.properties");

			prop.load(input);

			String locDbName = prop.getProperty("database");
			String locDbUser = prop.getProperty("dbuser");
			String locdbPassword = prop.getProperty("dbpassword");
			
			try {
				
				String url = locDbName;
				String user = locDbUser;
				String pass = locdbPassword;
			
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				connection = DriverManager.getConnection(url, user, pass);
			} catch (Exception ex) {
				System.out.println("Exception in dbConnection():" + ex);
			}
			

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return connection;

	}
}
