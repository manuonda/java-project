# Project With Annotations of Spring Boot .
The documentation belongs to Siva from course of Spring Boot

## Annotations 
### @Component Annotation

The @Component annotation indicates than an annotated class is  "spring bean/component"

The @Component annotation thell Spring container to automatially create Spring Bean .

***The Spring Boot container is responsible for creating an instance of the class bean.***

```java
@Component
public class PizzaController {

    public String getPizza(){
        return "Pizza anana";
    }
}


@SpringBootApplication
public class DescriptionSpringBootAnnotationApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(DescriptionSpringBootAnnotationApplication.class, args);
	    var pizzaController = context.getBean(PizzaController.class);
		System.out.println(pizzaController.getPizza());
	}

}

Output: Pizza anana

```
Other form is by name:
```java
Other form is use name 

@Component("pizzaController")
public class PizzaController {

    public String getPizza(){
        return "Pizza anana";
    }
}


@SpringBootApplication
public class DescriptionSpringBootAnnotationApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(DescriptionSpringBootAnnotationApplication.class, args);
	    var pizzaController =(PizzaController) context.getBean("pizzaController");
		System.out.println(pizzaController.getPizza());
	}

}


```