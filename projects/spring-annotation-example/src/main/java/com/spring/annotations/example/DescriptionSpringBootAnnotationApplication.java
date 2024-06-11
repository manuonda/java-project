package com.spring.annotations.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.annotations.example.controllers.MyController;
import com.spring.annotations.example.controllers.PizzaController;
import com.spring.annotations.example.repository.MyRepository;
import com.spring.annotations.example.services.MyService;
import com.spring.annotations.example.services.VegPizza;
import com.spring.annotations.example.services.VegPizzaBean;

@SpringBootApplication
public class DescriptionSpringBootAnnotationApplication {

	public static void main(String[] args) {
		 var context = SpringApplication.run(DescriptionSpringBootAnnotationApplication.class, args);
	    // var pizzaController =(PizzaController) context.getBean("pizzaController");
		// System.out.println(pizzaController.getPizza());
// 
		// VegPizza vegPizza =  context.getBean(VegPizza.class);
		// System.out.println(vegPizza.getPizza());

		//VegPizzaBean vegPizza2 = (VegPizzaBean) context.getBean("vegPizzaBean");
		//System.out.println(vegPizza2.getPizza());
	    MyController myController= context.getBean(MyController.class);
		System.out.println(myController.hello());

		MyService myService = context.getBean(MyService.class);
		System.out.println(myService.myService());

		MyRepository myRepository = context.getBean(MyRepository.class);
		System.out.println(myRepository.myRepository());

	
	}

}
