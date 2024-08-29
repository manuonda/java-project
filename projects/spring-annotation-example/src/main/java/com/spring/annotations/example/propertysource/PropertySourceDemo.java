package com.spring.annotations.example.propertysource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertySourceDemo {


    @Value("${gmail.host}")
    private String host;

    @Value("${gmail.email}")
    private String email;

    @Value("${gmail.password}")
    private String password;


    public String getHost(){
        return this.host;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return password;
    }

}
