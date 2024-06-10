# Project With Annotations of Spring Boot .
The documentation belongs to Siva from course of Spring Boot

En el contexto de Spring Boot, un "bean" es un objeto que es administrado por el contenedor de Inversión de Control (IoC) de Spring. Estos beans son componentes centrales en la arquitectura de aplicaciones desarrolladas con Spring, ya que representan la unidad básica de trabajo y configuración de la aplicación.

Aquí hay algunos puntos clave sobre los beans en Spring Boot:

Definición y Gestión: Los beans se definen y gestionan en el contenedor IoC. Spring se encarga de crear y administrar el ciclo de vida de estos beans, incluyendo su creación, configuración y destrucción.

Anotaciones: En Spring Boot, los beans generalmente se declaran utilizando anotaciones. Las anotaciones más comunes son:
**@Component**: Marca una clase como un bean genérico.
**@Service**: Es una especialización de @Component que indica que la clase contiene la lógica de negocio.
**@Repository**: También una especialización de @Component, se utiliza para la capa de acceso a datos.
**@Controller**: Se usa en la capa de presentación para manejar solicitudes web.
**@Configuration** y **@Bean**: Se utilizan para definir beans de manera explícita dentro de una clase de configuración.

Inyección de Dependencias: Spring Boot utiliza la inyección de dependencias para proporcionar automáticamente las instancias de beans necesarios en otros beans. Esto puede hacerse a través de constructor, setter o directamente en campos usando la anotación @Autowired.

Ciclo de Vida: El contenedor IoC de Spring se encarga de todo el ciclo de vida de un bean, desde su creación hasta su destrucción, aplicando configuraciones como la inicialización y destrucción personalizada si se han definido.


## Annotations 
### @Component Annotation

The **@Component** annotation indicates than an annotated class is  "spring bean/component"

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

### @Autowired
@Autowired is an annotation in Spring used to enable automatic dependency injection. This means that Spring automatically provides the necessary instances of beans at the points where this annotation is declared, without the developer needing to manually create those instances.

Examples:
Create Class VegPizza 

```java 
package com.spring.annotations.example.services;

import org.springframework.stereotype.Component;

@Component
public class VegPizza {
    public String getPizza(){
        return "Vegana Pizza";
    }

}
```
1 - Injection for setter

```java
@Component("pizzaController")
public class PizzaController{
	private VegPizza vegPizza;

	@Autowired
	private void setVegPizza(VegPizza vegPizza){
		this.vegPizza = vegPizza;
	}
}
```

2 - Injection for constructor
```java
@Component("pizzaController")
public class PizzaController{
	private VegPizza vegPizza;

	@Autowired
	private void PizzaController(VegPizza vegPizza){
		this.vegPizza = vegPizza;
	}
}
```
3 - Injection direct in field

```java
@Component("pizzaController")
public class PizzaController {

    @Autowired
    private VegPizza vegPizza;

    public String getPizza(){
      return vegPizza.getPizza();
    }
}
```
The output :
```
Vegana Pizza
```

### @Qualifier
Qualifier annotation is used in conjunction with Autowired to avoid confusion when we have two or more beas configured for same type.

Example create interface Pizza
```java
package com.spring.annotations.example.services;

public interface Pizza {
   String getPizza();
}
```
Class VegPizza, NonVegPizza implements interface Pizza 
```java 
@Component
public class VegPizza implements Pizza{ 

    @Override
    public String getPizza(){
        return "Vegana Pizza";
    }

}
```
```java 
@Component
public class NonVegPizza implements Pizza{

    @Override
    public String getPizza() {
        return "Non-veg Pizza";
    }

}
```
Inject Pizza use @Qualifier("vegPizza")
```java
@Component("pizzaController")
public class PizzaController {

    Pizza pizza;

    @Autowired
    public PizzaController(@Qualifier("vegPizza") Pizza pizza){
        this.pizza = pizza;
    }

    public String getPizza(){
      return pizza.getPizza();
    }
}

The Outuput:
Vegana Pizza
```



Inject Pizza use @Qualifier("nonVegPizza")

```java
@Component("pizzaController")
public class PizzaController {

    Pizza pizza;

    @Autowired
    public PizzaController(@Qualifier("vegPizza") Pizza pizza){
        this.pizza = pizza;
    }

    public String getPizza(){
      return pizza.getPizza();
    }
}

The Outuput:
Non-veg Pizza
```

### @Primary 
@Primary annotation , to give higher preference to a bean when there are multiple beans of the same type.


Example create interface Pizza
```java
package com.spring.annotations.example.services;

public interface Pizza {
   String getPizza();
}
```
Class VegPizza, NonVegPizza implements interface Pizza . But VegPizza class add the **@primary** annotation

```java 
@Component
@Primary
public class VegPizza implements Pizza{ 

    @Override
    public String getPizza(){
        return "Vegana Pizza";
    }

}
```
```java 
@Component
public class NonVegPizza implements Pizza{

    @Override
    public String getPizza() {
        return "Non-veg Pizza";
    }

}
```
Inject using Pizza, when running the application. The controller takes the VegPizza class because it is primary

```java
@Component("pizzaController")
public class PizzaController {

    Pizza pizza;

    @Autowired
    public PizzaController(Pizza pizza){
        this.pizza = pizza;
    }

    public String getPizza(){
      return pizza.getPizza();
    }
}

The Outuput:
Vegana Pizza
```

### @Bean and @Configuration

