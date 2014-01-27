package com.cm.n2o.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.security.MessageDigest;

@Service
public class SimpleService {

    private static final Logger logger = LoggerFactory.getLogger(SimpleService.class);


    public boolean verify(final Long userId, final String hmac) {
        return true;
    }


    static byte[] hash(final String algorithm, String input) {
        return hash(algorithm, input.getBytes(Charset.forName("UTF-8")));
    }

    static byte[] hash(final String algorithm, final byte[] input) {
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            return messageDigest.digest(input);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
