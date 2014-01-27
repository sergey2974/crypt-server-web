package com.cm.n2o.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
