package bamLogin;

public class UpdateCurrentLogin {

	public static String updateLogin(String userid, String token) {

		String respose = "";
		
		int noOfUsers = UpdateLoginDao.getLoginUserDtls(userid);
		
		if (noOfUsers == 0) {
			respose=UpdateLoginDao.firstLoginInsert(userid, token);
			
		}

		else {
			respose=UpdateLoginDao.updateLogin(userid,token);
			
		}
		

		return respose;

	}

}
