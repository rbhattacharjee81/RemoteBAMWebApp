package dashBoard;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dashBoard.Client;
import utility.DBConnection;

public class ClientFailedDataService {

	public static List<Client> getClientFailedList() {

		List<Client> listOfclientsFailed = new ArrayList<Client>();

		Client client = new Client();

		try {

			DBConnection dbConnection = new utility.DBConnection();
			Statement st = null;
			ResultSet rs = null;
			Connection connection = (Connection) dbConnection.dbConnection();

			String client_name = "";
			String type = "";
			String activity = "";
			String timestamp = "";
			String Final_status = "";
			String imgUrlSuccess="img/correct.png";
			String imgUrlFail="img/wrong2.png";
			String imgUrlUnder="img/loading.png";
			String originalImgPop="";

			st = connection.createStatement();
			String query = "SELECT p.client_name,p.type,p.activity,p.timestamp,p.Final_status FROM process_state_table p where p.Final_status='Failure' UNION SELECT m.client_name,m.type,m.Activity,m.DateTimeStamp,m.Final_status FROM mj_report m where m.Final_status='Failure'";
			rs = st.executeQuery(query);

			while (rs.next()) {
				client = new Client();

				client_name = rs.getString(1);
				type = rs.getString(2);
				activity = rs.getString(3);
				timestamp = rs.getString(4);
				Final_status = rs.getString(5);
				if(Final_status.equalsIgnoreCase("Processed")){
					originalImgPop=imgUrlSuccess;
				}
				else if(Final_status.equalsIgnoreCase("Failure")){
					originalImgPop=imgUrlFail;
				}
				else {
					originalImgPop=imgUrlUnder;
				}

				client = new Client();

				client.setActivity(type+"~"+activity);

				client.setClient(client_name);

				client.setRecordDate(timestamp);

				client.setStatus(originalImgPop);

				listOfclientsFailed.add(client);

			
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

			
		

		return listOfclientsFailed;

	}
}
