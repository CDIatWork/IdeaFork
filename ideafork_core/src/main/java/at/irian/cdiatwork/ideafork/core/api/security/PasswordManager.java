package at.irian.cdiatwork.ideafork.core.api.security;

import javax.enterprise.context.ApplicationScoped;
import java.security.MessageDigest;

@ApplicationScoped
public class PasswordManager {
    private static final char[] DIGITS_LOWER =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    //simple implementation without: random salt, multiple iterations,...
    public String createPasswordHash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            return encodeHex(digest.digest(password.getBytes("UTF-8")));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    //based on org.apache.commons.codec.binary.Hex.java
    private String encodeHex(byte[] data) {
        int length = data.length;
        char[] result = new char[length << 1];
        for (int i = 0, j = 0; i < length; i++) {
            result[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
            result[j++] = DIGITS_LOWER[0x0F & data[i]];
        }
        return new String(result);
    }
}
