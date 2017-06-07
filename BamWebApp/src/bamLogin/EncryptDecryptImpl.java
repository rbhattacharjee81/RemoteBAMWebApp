package bamLogin;

import java.security.SecureRandom;

public class EncryptDecryptImpl {

	public static String createEcryptToken() {
		String encryptedToken = null;

		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[8];
		random.nextBytes(bytes);
		String tokenGeneartion = bytes.toString();

		encryptedToken = EncryptDecryptStringWithDES.encrypt(tokenGeneartion);

		return encryptedToken;
	}

	

}
