package com.spring.annotations.example.services;

import org.springframework.stereotype.Component;

@Component
public class NonVegPizza implements Pizza{

    @Override
    public String getPizza() {
        return "Non-veg Pizza";
    }

}
