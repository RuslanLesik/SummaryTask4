package ua.nure.lesik.SummaryTask4.util;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The class designed to encrypt password
 *
 * @author Ruslan Lesik
 */
public class UtilHash {

    private static final Logger LOG = Logger.getLogger(UtilHash.class);

    /**
     * Hashes the input string in md5
     *
     * @param password String
     * @return String encrypted password
     */
    public static String encryptPassword(String password) {
        StringBuilder hexPassword = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes("UTF-8"));
            byte[] hash = digest.digest();
            for (int i = 0; i < hash.length; i++) {
                hexPassword.append(String.format("%02x", hash[i]));
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LOG.error(e.getMessage());
        }
        return hexPassword.toString();
    }
}
