package com.spring.annotations.example.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class MyController {
    
    public String hello(){
        return "Hello Controller";
    }
}
