package com.spring.annotations.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.annotations.example.controllers.PizzaController;
import com.spring.annotations.example.services.VegPizza;

@SpringBootApplication
public class DescriptionSpringBootAnnotationApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(DescriptionSpringBootAnnotationApplication.class, args);
	    var pizzaController =(PizzaController) context.getBean("pizzaController");
		System.out.println(pizzaController.getPizza());

		VegPizza vegPizza =  context.getBean(VegPizza.class);
		System.out.println(vegPizza.getPizza());

		VegPizza vegPizza2 = (VegPizza) context.getBean("vegPizzaBean");
		System.out.println(vegPizza2.getPizza());
	}

}
