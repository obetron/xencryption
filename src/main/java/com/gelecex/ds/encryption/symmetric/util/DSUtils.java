package com.gelecex.ds.encryption.symmetric.util;

import com.gelecex.ds.encryption.exception.DSException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by obetron on 13.10.2018
 */
public class DSUtils {

    /**
     * byte array to base64 string converter.
     * @param bytes byte array value.
     * @return converted base64 string.
     */
    public static String bytesToBase64Str(byte[] bytes) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String result = base64Encoder.encode(bytes);
        return result;
    }

    /**
     * Base64 String to byte array converter.
     * @param base64Val base64 string.
     * @return converted byte array.
     * @throws DSException IO Exception for decodeBuffer.
     */
    public static byte[] base64StrToBytes(String base64Val) throws DSException {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            return base64Decoder.decodeBuffer(base64Val);
        } catch (IOException e) {
            throw new DSException("DS Decoding Error, check DSUtils class!!!", e);
        }
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

    public static String getHashBase64(byte[] value, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] hashedBytes = md.digest(value);
        BASE64Encoder encoder = new BASE64Encoder();
        String hashedStr = encoder.encode(hashedBytes);
        return hashedStr;
    }

    public static byte[] generateAESKey(String keyWord) {
        return null;
    }

}
