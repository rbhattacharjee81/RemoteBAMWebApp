package bamLogin;

import java.sql.SQLException;

public class CheckPrevLogin {

	public static int prevLoginDtls(String username) throws SQLException {

		int loginPrevdtls = CheckPrevLoginDao.prevLoginUserDtls(username);

		return loginPrevdtls;

	}

}
