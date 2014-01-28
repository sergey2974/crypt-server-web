package com.cm.n2o.web;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
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
        //response.setStatus(403);
        String respStr = "";

        try {
            String keyStr = simpleService.getMD5(n + sessionId + "KeyString");
            RC4 rc4 = new RC4(keyStr);
            byte[] encrypted = rc4.rc4(data.getBytes("UTF-8"));
            respStr = DatatypeConverter.printHexBinary(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
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
            byte[] encryptedData = DatatypeConverter.parseHexBinary(data);
            String keyStr = simpleService.getMD5(n + sessionId + "KeyString");
            RC4 rc4 = new RC4(keyStr);
            byte[] decrypted = rc4.rc4(encryptedData);
            respStr = new String(decrypted,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respStr;
    }
}
