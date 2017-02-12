/*
 * Copyright (c) 2003 US Hayden, Inc. All rights reserved.
 */
package com.mage.platform.utils;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.mail.internet.MimeUtility;

import sun.misc.BASE64Decoder;

/**
 * 关于加解密的工具类 需要额外包：jce.zip
 * @author Jerry Li
 * @version $Revision: 7611 $
 */
public class EncryptUtils {
    /** MD5加密，不可还原 */
    public static final String MD5 = "MD5";
    /** SHA加密，不可还原 */
    public static final String SHA = "SHA";
    /** DES加密，可以还原 */
    public static final String DES = "DES";

    /**
     * DES加密密钥，解密时也要用到 该变量不可以改变，否则将无法对密码进行解密，现在是"encrypt"
     */
    private static final byte[] KEY = {(byte) 'e', (byte) 'n', (byte) 'c', (byte) 'r', (byte) 'y',
                                       (byte) 'p', (byte) 't', (byte) '1'};

    /**
     * 按照加密类型加密字串
     * @param orgStr 要加密的字串
     * @param encType 加密类型
    
     * @return 加密后的字串，如果没有匹配的加密方式，则返回原字串 */
    public static String encrypt(String orgStr, String encType) {
        try {
            if ((encType.compareToIgnoreCase("SHA") == 0)) {
                // return orgStr;
                // We need to use unicode here, to be independent of platform's
                // default encoding.
                MessageDigest md = MessageDigest.getInstance(encType);
                byte[] digest = md.digest(orgStr.getBytes("UTF-8"));
                ByteArrayOutputStream bas = new ByteArrayOutputStream(digest.length + (digest.length / 3)
                    + 1);
                OutputStream encodedStream = MimeUtility.encode(bas, "base64");
                encodedStream.write(digest);
                return bas.toString();
            } else if (encType.compareToIgnoreCase("MD5") == 0) {
                MessageDigest alga = MessageDigest.getInstance("MD5");
                alga.update(orgStr.getBytes());
                byte[] digesta = alga.digest();
                String encodePass = byte2str(digesta);
                return encodePass;
            } else if (encType.compareToIgnoreCase("DES") == 0) {
//                Provider provider = new au.net.aba.crypto.provider.ABAProvider();
//                Security.addProvider(provider);
//                DESKeySpec keyspec = new DESKeySpec(KEY);
//
//                // System.out.println(SecretKeyFactory.getInstance(DES).getProvider().getName());
//                SecretKey key = SecretKeyFactory.getInstance(DES).generateSecret(keyspec);
//
//                Cipher ecipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
//                ecipher.init(Cipher.ENCRYPT_MODE, key);
//                byte[] enc = ecipher.doFinal(orgStr.getBytes("UTF8"));
//                String encryptedStr = new BASE64Encoder().encode(enc);
//                return encryptedStr;
            }

            return orgStr;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return orgStr;
        }
    }

    /**
     * 按照解密密类型解密字串
     * @param encryptedStr 要解密的字串
     * @param encType 解密类型
    
     * @return 解密后的字串，如果不是DES方式，则返回原字串 */
    public static String decrypt(String encryptedStr, String encType) {
        if ("DES".equals(encType)) {
            try {
                DESKeySpec keyspec = new DESKeySpec(KEY);
                SecretKey key = SecretKeyFactory.getInstance(DES).generateSecret(keyspec);
                Cipher dcipher = Cipher.getInstance("DES");
                dcipher.init(Cipher.DECRYPT_MODE, key);
                byte[] dec = new BASE64Decoder().decodeBuffer(encryptedStr);
                byte[] utf8 = dcipher.doFinal(dec);
                return new String(utf8, "UTF8");
            } catch (Exception ex) {
                return encryptedStr;
            }
        }

        return encryptedStr;
    }

    /**
     * 测试得到123456的DES加密串
     * @param args 参数
     */
    public static void main(String[] args) {
        String encryptedStr = EncryptUtils.encrypt("123456", "DES");
        String orgStr = EncryptUtils.decrypt(encryptedStr, "DES");
        System.out.println(encryptedStr);
        System.out.println(orgStr);
    }

    /**
     * the following code is copied form other system example
     * @param b byte info
    
     * @return string info */
    private static String byte2str(byte[] b) {
        String output = new String("");
        String stmp = "";

        try {
            for (int i = 0; i < b.length; i++) {
                stmp = Integer.toString(b[i] & 0XFF, 16);

                if (stmp.length() == 1) {
                    output += ("0" + stmp);
                } else {
                    output += stmp;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
}
