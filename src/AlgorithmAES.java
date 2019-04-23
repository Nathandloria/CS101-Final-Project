import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AlgorithmAES {
	private Cipher cipher;
	private SecretKey secretKey;
	private String encodedKey;

	public void setKey() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			// keyGenerator.generateKey creates a secret key
			secretKey = keyGenerator.generateKey();
			cipher = Cipher.getInstance("AES");
		} catch (Exception x) {
			System.out.println(x);
		}
	}

	public String getKey() {
		encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
		return encodedKey;
	}

	public String encrypt(String plainText)
	throws Exception {
		byte[] plainTextByte = plainText.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		// dofinal used for final block of code
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		// base64 is an encoder used to encode byte data
		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedText = encoder.encodeToString(encryptedByte);
		return encryptedText;
	}

	public String decrypt(String encryptedText, String secretkey)
	throws Exception {
		byte[] decodedKey = Base64.getDecoder().decode(secretkey);
		secretKey = new SecretKeySpec(decodedKey, 128, decodedKey.length, "AES");
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}
}
