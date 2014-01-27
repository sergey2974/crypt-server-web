package com.cm.n2o.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

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
        return "1";
    }

    @RequestMapping(value = "/decrypt", method = RequestMethod.GET)
    @ResponseBody
    public String decrypt(@RequestParam("data") final String data,
                          @RequestParam("n") final String n,
                          @RequestParam("sessionId") final String sessionId,
                          HttpServletResponse response) {
        //response.setStatus(403);
        return "1";
    }
}
