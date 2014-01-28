package com.cm.n2o.web;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

@Controller
public class SimpleController {

    private static final Logger logger = LoggerFactory.getLogger(SimpleController.class);

    @Value("${keyString}")
    private String keyString;


    @Autowired
    private SimpleService simpleService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/crypt", method = RequestMethod.GET)
    @ResponseBody
    public String crypt(@RequestParam("data") final String data,
                        @RequestParam("n") final String n,
                        @RequestParam("sessionId") final String sessionId,
                        HttpServletResponse response) {
        String respStr = "";
        try {
            String keyStr = simpleService.getMD5(n + sessionId + keyString);
            respStr = simpleService.encrypt(data, keyStr);
        } catch (Exception e) {
            logger.error("Crypt error", e);
        }
        return respStr;
    }

    @RequestMapping(value = "/decrypt", method = RequestMethod.GET)
    @ResponseBody
    public String decrypt(@RequestParam("data") final String data,
                          @RequestParam("n") final String n,
                          @RequestParam("sessionId") final String sessionId,
                          HttpServletResponse response) {

        String respStr = "";
        try {
            String keyStr = simpleService.getMD5(n + sessionId + keyString);
            respStr = simpleService.decrypt(data, keyStr);
        } catch (Exception e) {
            logger.error("Decrypt error", e);
        }
        return respStr;
    }
}
