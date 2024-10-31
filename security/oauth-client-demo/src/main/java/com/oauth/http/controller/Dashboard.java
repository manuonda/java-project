package com.oauth.http.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/dashboard")
public class Dashboard {

    @GetMapping
    public String getDashboardAuthenticated() {
        return new String("Hello Authenticated");
    }
    
}
