package com.mckm.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by @author km2
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "MLEKO";
    }

    @RequestMapping(value = "/mleko", method = RequestMethod.GET)
    public String mleko(){
        return "MLEKO";
    }
}
