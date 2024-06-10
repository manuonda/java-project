package com.spring.annotations.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.annotations.example.services.Pizza;
import com.spring.annotations.example.services.VegPizza;

@Configuration
public class AppConfig {
    
    @Bean
    public Pizza vegPizza(){
        return new VegPizza();
    }

    // @Bean(name="vegPizzaBean")
    // public Pizza vegNameBeanPizza(){
    //     return new VegPizza();
    // }
}
