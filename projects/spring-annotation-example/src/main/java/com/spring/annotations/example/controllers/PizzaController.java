package com.spring.annotations.example.controllers;

import org.springframework.stereotype.Component;

@Component("pizzaController")
public class PizzaController {

    public String getPizza(){
        return "Pizza anana";
    }
}



