package com.spring.annotations.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.annotations.example.controllers.PizzaController;

@SpringBootApplication
public class DescriptionSpringBootAnnotationApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(DescriptionSpringBootAnnotationApplication.class, args);
	    var pizzaController =(PizzaController) context.getBean("pizzaController");
		System.out.println(pizzaController.getPizza());
	}

}
