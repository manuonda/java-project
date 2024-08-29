package com.spring.annotations.example.propertysource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropertySourceDemo {


    @Autowired
    private Environment environment;


    @Value("${gmail.host}")
    private String host;

    @Value("${gmail.email}")
    private String email;

    @Value("${gmail.password}")
    private String password;

    @Value("${app.name}")
    private String appName;

    @Value("${app.description}")
    private String description;


    public String getHost(){
        return this.host;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return password;
    }
    
    public String getAppName(){
        return this.appName;
    }

    public String getDescription(){
        return this.description;
    }


    public String getEnvironmentPropertyHost(){
        return environment.getProperty("gmail.host");
    }

    public String getEnvironmentPropertyServer(){
        return environment.getProperty("gmail.email");
    }   
}
