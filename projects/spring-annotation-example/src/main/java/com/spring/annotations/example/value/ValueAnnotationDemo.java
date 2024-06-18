package com.spring.annotations.example.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValueAnnotationDemo {
    

    @Value("Default Name")
    private String defaultName;

    //default value
    @Value("${email.host:andres@gmail.com}")
    private String emailHost;

    //variable environment
    @Value("${java.home}")
    private String javaHome;


    public String getEmailHost() {
        return emailHost;
    }

    public String getDefaultName(){
        return defaultName;
    }

    public String getJavaHome() {
        return javaHome;
    }
    

}
