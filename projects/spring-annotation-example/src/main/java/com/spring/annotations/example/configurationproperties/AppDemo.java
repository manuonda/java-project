package com.spring.annotations.example.configurationproperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppDemo {


    @Autowired
    private AppProperties appProperties;

    public void display(){
        System.out.println(appProperties.getName());
        System.out.println(appProperties.getDescription());
        System.out.println(appProperties.getUploadDir());
        System.out.println(appProperties.getSecurity().getUsername());
        System.out.println(appProperties.getSecurity().getPassword());
        System.out.println(appProperties.getSecurity().getRoles());
        System.out.println(appProperties.getSecurity().isEnabled());
        System.out.println(appProperties.getSecurity().getPermissions());
    
    }
    
}
