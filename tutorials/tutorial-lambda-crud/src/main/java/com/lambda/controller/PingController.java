package com.lambda.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@EnableWebMvc
public class PingController {

    @RequestMapping(path = "/ping", method=RequestMethod.GET)
    public Map<String, String> requestMethodName(@RequestParam String param) {
        Map<String,String> pong = new HashMap<>();
        pong.put("pong", "Hello, World");
        return pong;
    }
    
}