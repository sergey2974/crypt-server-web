package com.cm.n2o.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;

@Service
public class SimpleService {

    private static final Logger logger = LoggerFactory.getLogger(SimpleService.class);


    public boolean verify(final Long userId, final String hmac) {
        return true;
    }

    public String getMD5(String string) {
        String hash = "";
        try {
            MessageDigest md5;
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(string.toString().getBytes());
            byte[] digest = md5.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            hash = String.format("%1$032x", bigInt);
        } catch (Exception e) {
            logger.error("hash calculation error", e);
        }
        return hash;
    }

    public String encrypt(String data, String keyStr) {
        String respStr = "";
        try {
            RC4 rc4 = new RC4(keyStr);
            byte[] encrypted = rc4.rc4(data.getBytes("UTF-8"));
            respStr = DatatypeConverter.printHexBinary(encrypted);
        } catch (Exception e) {
            logger.error("Encryption error", e);
        }
        return respStr;
    }

    public String decrypt(String data, String keyStr) {
        String respStr = "";
        try {
            byte[] encryptedData = DatatypeConverter.parseHexBinary(data);
            RC4 rc4 = new RC4(keyStr);
            byte[] decrypted = rc4.rc4(encryptedData);
            respStr = new String(decrypted, "UTF-8");
        } catch (Exception e) {
            logger.error("Decryption error", e);
        }
        return respStr;
    }
}
