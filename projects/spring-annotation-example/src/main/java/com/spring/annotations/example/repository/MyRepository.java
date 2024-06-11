package com.spring.annotations.example.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MyRepository {
    
    public String myRepository(){
        return "My Repository";
    }
}
