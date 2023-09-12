package VNGroupBy.com.vn.Utils.Encrytion;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class TokenEncryption {
   /* public static String encryptToken(String token) throws Exception {
        byte[] privateKeyBytes = Base64.getDecoder().decode(EncryptionPrivateKey.TOKEN_PRIVATE_KEY);
        PrivateKey rsaPrivateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey);
        byte[] encryptedBytes = cipher.doFinal(token.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }*/
   public static String encrypt(String data) throws Exception {
       SecretKeySpec secretKey = new SecretKeySpec(EncryptionPrivateKey.TOKEN_PRIVATE_KEY.getBytes(StandardCharsets.UTF_8), "AES");

       Cipher cipher = Cipher.getInstance("AES");
       cipher.init(Cipher.ENCRYPT_MODE, secretKey);

       byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
       return Base64.getEncoder().encodeToString(encryptedBytes);
   }
}
