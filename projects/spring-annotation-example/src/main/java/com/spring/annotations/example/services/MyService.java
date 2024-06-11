package com.spring.annotations.example.services;

import org.springframework.stereotype.Service;

@Service
public class MyService {
    
    public String myService(){
        return "My Service";
    }
}
