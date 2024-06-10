package com.spring.annotations.example.services;

import org.springframework.stereotype.Component;

@Component
public class VegPizza implements Pizza{ 

    @Override
    public String getPizza(){
        return "Vegana Pizza";
    }

}
