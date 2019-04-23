import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AlgorithmAES {

  public String setKey()
  throws Exception {
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(128);
    SecretKey secretKey = keyGenerator.generateKey();
    Cipher cipher = Cipher.getInstance("AES");
    String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
    return encodedKey;
  }

  public String encrypt(String plainText, String encodedKey2)
  throws Exception {
    byte[] decodedKey = Base64.getDecoder().decode(encodedKey2);
    SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    byte[] plainTextByte = plainText.getBytes();
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, originalKey);
    byte[] encryptedByte = cipher.doFinal(plainTextByte);
    Base64.Encoder encoder = Base64.getEncoder();
    String encryptedText = encoder.encodeToString(encryptedByte);
    return encryptedText;
  }

  public String decrypt(String encryptedText, String encodedKey2)
  throws Exception {
    byte[] decodedKey = Base64.getDecoder().decode(encodedKey2);
    SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    Base64.Decoder decoder = Base64.getDecoder();
    byte[] encryptedTextByte = decoder.decode(encryptedText);
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, originalKey);
    byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
    String decryptedText = new String(decryptedByte);
    return decryptedText;
  }
}
