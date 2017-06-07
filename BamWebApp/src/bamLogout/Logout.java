package bamLogout;

public class Logout {

	public static String updateLogout(String userid) {
		
		
		String respose = LogoutDao.updateLogut(userid);
				
		

		return respose;

	}

}
