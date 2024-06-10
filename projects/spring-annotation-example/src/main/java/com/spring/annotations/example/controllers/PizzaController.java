package com.spring.annotations.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.spring.annotations.example.services.Pizza;

@Component("pizzaController")
public class PizzaController {

    Pizza pizza;

    @Autowired
    public PizzaController(@Qualifier("nonVegPizza") Pizza pizza){
        this.pizza = pizza;
    }

    public String getPizza(){
      return pizza.getPizza();
    }
}



