package com.spring.annotations.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.annotations.example.controllers.MyController;
import com.spring.annotations.example.controllers.PizzaController;
import com.spring.annotations.example.lazy.LazyLoader;
import com.spring.annotations.example.propertysource.PropertySourceDemo;
import com.spring.annotations.example.repository.MyRepository;
import com.spring.annotations.example.scope.PrototypeBean;
import com.spring.annotations.example.scope.SingletonBean;
import com.spring.annotations.example.services.MyService;
import com.spring.annotations.example.services.VegPizza;
import com.spring.annotations.example.services.VegPizzaBean;
import com.spring.annotations.example.value.ValueAnnotationDemo;

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

	  
		LazyLoader lazyLoader = context.getBean(LazyLoader.class);

		SingletonBean singletonBean = context.getBean(SingletonBean.class);
		System.out.println(singletonBean.hashCode());
		SingletonBean singletonBean2 = context.getBean(SingletonBean.class);
		System.out.println(singletonBean2.hashCode());
		SingletonBean singletonBean3 = context.getBean(SingletonBean.class);
		System.out.println(singletonBean3.hashCode());
		PrototypeBean prototypeBean = context.getBean(PrototypeBean.class);
		System.out.println(prototypeBean.hashCode());
		PrototypeBean prototypeBean2 = context.getBean(PrototypeBean.class);
		System.out.println(prototypeBean2.hashCode());
		PrototypeBean prototypeBean3 = context.getBean(PrototypeBean.class);
        System.out.println(prototypeBean3.hashCode());     


		ValueAnnotationDemo valueAnnotationDemo = context.getBean(ValueAnnotationDemo.class);
		System.out.println(valueAnnotationDemo.getDefaultName());
		System.out.println(valueAnnotationDemo.getEmailHost());
		System.out.println(valueAnnotationDemo.getJavaHome());
	
		/********* @Property Source ****** */
		PropertySourceDemo propertySourceDemo = context.getBean(PropertySourceDemo.class);
		System.out.println("Host: " + propertySourceDemo.getHost());
		System.out.println("Email : "+ propertySourceDemo.getEmail());
		System.out.println("Password : "+ propertySourceDemo.getPassword());
		System.out.println("App Name : "+ propertySourceDemo.getAppName());
		System.out.println("Description : "+ propertySourceDemo.getDescription());
  
		System.out.println("Environment Property Host : "+ propertySourceDemo.getEnvironmentPropertyHost());
		System.out.println("Environment Property Server : "+ propertySourceDemo.getEnvironmentPropertyServer());

	}

}
