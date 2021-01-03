package com.gelecex.ds.encryption.symmetric.util;

import com.gelecex.ds.encryption.exception.ExceptionX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Created by obetron on 13.10.2018
 */
public class UtilsX {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilsX.class);

    /**
     * byte array to base64 string converter.
     * @param bytes byte array value.
     * @return converted base64 string.
     */
    public static String bytesToBase64Str(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Base64 String to byte array converter.
     * @param base64Val base64 string.
     * @return converted byte array.
     * @throws ExceptionX IO Exception for decodeBuffer.
     */
    public static byte[] base64StrToBytes(String base64Val) {
        return Base64.getDecoder().decode(base64Val);
    }

    /**
     * byte array to hex converter.
     * @param bytes byte array value.
     * @return converted hex string.
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * hex value to byte array converter.
     * @param s hex string value.
     * @return converted btye array.
     */
    public static byte[] hexToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    /**
     * Get algorithm value from cipher text.
     * @param cipherStr cipher text value.
     * @return Algorithm value.
     */
    public static String getAlgFromCipher(String cipherStr) {
        String[] cipherVals = cipherStr.split("/");
        return cipherVals[0];
    }

    /**
     *
     * @param value
     * @param algorithm
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getHashBase64(byte[] value, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] hashedBytes = md.digest(value);
        return Base64.getEncoder().encodeToString(hashedBytes);
    }

    /**
     * Generate secure random byte array for initial vector.
     * @return secure random byte array.
     */
    public static byte[] generateRandomInitialVectorBytes() {
        byte[] bytes = new byte[16];
        try {
            SecureRandom.getInstanceStrong().nextBytes(bytes);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Exception occured while creating random initial vector (iv) bytes!!!");
        }
        return bytes;
    }

}