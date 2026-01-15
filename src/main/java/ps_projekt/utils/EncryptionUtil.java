package ps_projekt.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionUtil {
    private static final String ENC_ALGORITHM = "AES";
    private static final SecretKey SECRET_KEY = generateKey();

    private static SecretKey generateKey() {
        try {
            return new SecretKeySpec("b7b866708107ab46f6444b425ca225d0".getBytes(StandardCharsets.UTF_8), "AES");
        } catch (Exception e) {
            throw new RuntimeException("Error generating secret key", e);
        }
    }

    public static String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance(ENC_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }

    public static String decrypt(String encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance(ENC_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data", e);
        }
    }
}
