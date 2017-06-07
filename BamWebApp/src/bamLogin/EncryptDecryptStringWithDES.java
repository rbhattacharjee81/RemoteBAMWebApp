package bamLogin;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

public class EncryptDecryptStringWithDES {

	private static Cipher ecipher;
	private static Cipher dcipher;

	private static SecretKey key;

	public static void main(String[] args) {

		try {

			// generate secret key using DES algorithm
			key = KeyGenerator.getInstance("DES").generateKey();

			ecipher = Cipher.getInstance("DES");
			dcipher = Cipher.getInstance("DES");

			// initialize the ciphers with the given key

			ecipher.init(Cipher.ENCRYPT_MODE, key);

			dcipher.init(Cipher.DECRYPT_MODE, key);

			String encrypted = encrypt(tokenGeneration());

			String decrypted = decrypt(encrypted);

			System.out.println("Encrypted: " + encrypted);
			System.out.println("Decrypted: " + decrypted);

		} catch (NoSuchAlgorithmException e) {
			System.out.println("No Such Algorithm:" + e.getMessage());
			return;
		} catch (NoSuchPaddingException e) {
			System.out.println("No Such Padding:" + e.getMessage());
			return;
		} catch (InvalidKeyException e) {
			System.out.println("Invalid Key:" + e.getMessage());
			return;
		}

	}

	public static String tokenGeneration() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[8];
		random.nextBytes(bytes);
		String token = bytes.toString();
		return token;
	}

	public static String encrypt(String str) {

		try {


			key = KeyGenerator.getInstance("DES").generateKey();

			ecipher = Cipher.getInstance("DES");
			
			ecipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] utf8 = str.getBytes("UTF8");

			byte[] enc = ecipher.doFinal(utf8);

			// encode to base64

			enc = BASE64EncoderStream.encode(enc);

			return new String(enc);

		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	public static String decrypt(String str) {

		try {

			// decode with base64 to get bytes
			
			
			dcipher = Cipher.getInstance("DES");
			dcipher.init(Cipher.DECRYPT_MODE, key);

			byte[] dec = BASE64DecoderStream.decode(str.getBytes());

			byte[] utf8 = dcipher.doFinal(dec);

			// create new string based on the specified charset

			return new String(utf8, "UTF8");

		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

}