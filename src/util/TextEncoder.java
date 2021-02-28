package util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class TextEncoder {
    private static Cipher cipher;
    // needed to encode unwanted characters (", \, ...)
    private static Base64.Encoder b64Enc = Base64.getEncoder();

    // example keyString: bPeShVmYq3t6w9y$ (retrieved from online generator)
    public static void init(String keyString) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        if (cipher == null) {
            synchronized (TextEncoder.class) {
                if (cipher == null) {
                    // get crypt algorithm instance and initialze it
                    cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                    SecretKey secretKey = new SecretKeySpec(keyString.getBytes(), "AES");
                    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                }
            }
        }
    }

    public static String encode(String keyString, String password)
            throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException {
        if (cipher == null) {
            init(keyString);
        }
        return b64Enc.encodeToString(cipher.doFinal(password.getBytes()));
    }
}
