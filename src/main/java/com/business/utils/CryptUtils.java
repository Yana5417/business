package com.business.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.hazelcast.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: lxliuxuan
 * Date: 2018/09/20
 */

public class CryptUtils {
    protected static Logger LOG = LoggerFactory.getLogger(CryptUtils.class.getName());
    private static final String CHARSET_UTF8 = "UTF-8";

    /**
     *
     * @return String
     */
    public static String createMD5(String passwordSource) {
        String password;
        byte[] passwordByte;
        password = passwordSource;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes("UTF-8"));
            passwordByte = md.digest();
        } catch (Exception e) {
            return passwordSource;
        }

        return transByteArrayToHexString(passwordByte);
    }

    /**
     *
     * @return String
     */
    public static String encryptDES(String encryptdata, String encryptWord) {
        return encryptDES(encryptdata, CHARSET_UTF8, encryptWord);
    }

    /**
     *
     * @return String
     */
    public static String decryptDES(String decryptdata, String encryptWord) {
        return decryptDES(decryptdata, CHARSET_UTF8, encryptWord);
    }

    /**
     *
     * @param encryptdata
     * @param charset
     * @param encryptWord
     * @return
     */
    public static String encryptDES(String encryptdata, String charset, String encryptWord) {
        SecureRandom sr = new SecureRandom();
        try {
            byte[] rawKeyData = encryptWord.getBytes();
            DESKeySpec dks = new DESKeySpec(rawKeyData);

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key, sr);
            byte[] data = encryptdata.getBytes(charset);
            byte[] encryptedData = cipher.doFinal(data);

            return new String(Base64.encode(encryptedData),charset);

        } catch (Exception e) {
            LOG.error("SRC:(" + encryptdata + "), KEY:(" + encryptWord + ")", e);
        }
        return null;
    }

    /**
     *
     *
     * @param decryptdata
     * @param charset
     * @param encryptWord
     * @return
     */
    public static String decryptDES(String decryptdata, String charset, String encryptWord) {
        SecureRandom sr = new SecureRandom();
        try {
            byte[] rawKeyData = encryptWord.getBytes(charset);
            DESKeySpec dks = new DESKeySpec(rawKeyData);

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(dks);

            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key, sr);

            byte[] encryptedData = Base64.decode(decryptdata.getBytes());
            byte[] decryptedData = cipher.doFinal(encryptedData);
            return new String(decryptedData, charset);
        } catch (Exception e) {
            LOG.error("SRC:(" + decryptdata + "), KEY:(" + encryptWord + ")", e);
        }
        return null;
    }

    private static String transByteArrayToHexString(byte[] byteArray) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            int b = byteArray[i];
            String s = Integer.toHexString(b);

            if (s.length() > 2) {
                s = s.substring(s.length() - 2, s.length());
            }

            if (s.length() == 1) {
                s = "0" + s;
            }

            sb.append(s);
        }

        return sb.toString();
    }

}
