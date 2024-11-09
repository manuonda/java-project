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

 #### **Links**
 [@Component Annotation](#component-annotation)  
 [@Autowired](#autowired)  
 [@Qualifier](#qualifier)  
 [@Primary](#primary)  
 [@Bean and @Configuration](#bean-and-configuration)  
 [@Repository, @Controller and @Service](#repository-controller-and-service)  
 [@Lazy](#lazy)  
 [@Scope](#scope)  
 [@Value](#value)  
 [@PropertySource and @PropertySources Annotations](#propertysource-and-propertysources-annotations)  
 [Use of **Environment**](#use-of-environment)  
 [@ConfigurationProperties in Spring Boot](#configurationproperties-in-spring-boot)  
 [Steps to Use @ConfigurationProperties](#steps-to-use-configurationproperties)  
 [@Controller and @ResponseBody in Spring Boot](#controller-and-responsebody-in-spring-boot)  
 [@RestController](#restcontroller)  
 [@RequestMapping](#requestmapping)  
 [@GetMapping](#getmapping)  
 [@PostMapping and @RequestBody](#postmapping-and-requestbody)  
 [@PutMapping](#putmapping)  
 [@DeleteMapping](#deletemapping)  
 [@PathVariable and @RequestParam](#pathvariable-and-requestparam)  
 [Links](#links)  


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

**@Bean** and **@Configuration** are annotations in Spring used to define and configure beans in an application. These annotations are primarily used in configuration classes to create and manage beans in an explicit and programmatic way. Here is a detailed explanation of each:
**@Configuration**

**@Configuration** is an annotation used to mark a class as a source of bean definitions. Classes annotated with @Configuration are treated as configuration classes that can contain methods annotated with @Bean.

Purpose: Indicate that the class contains bean definitions.
Usage: Placed at the top of the class.

**@Bean** is an annotation used to indicate that a method produces a bean to be managed by the Spring container. Each method annotated with **@Bean** returns an object that is registered as a bean in the Spring context.

Purpose: Define a bean that Spring will manage.
Usage: Placed above a method within a class annotated with @Configuration.

Create class VegPizzaBean
```java
public class VegPizzaBean implements Pizza {

    public String getPizza(){
        return "Vegana Pizza Bean!";
    }
}
```


Create class AppConfig.java and Inject the Bean Pizza


```java 
@Configuration
public class AppConfig {
    
     @Bean
    public Pizza vegPizzaBean(){
        return new VegPizzaBean();
    }
}

// in the application main java 
public static void main(String[] args) {
	var context = SpringApplication.run(DescriptionSpringBootAnnotationApplication.class, args);
	 
	VegPizzaBean vegPizza =  context.getBean(VegPizzaBean.class);
	System.out.println(vegPizza.getPizza());
}
The Output : Vegana Pizza Bean!

```
Also with name for example 

```java
@Configuration
public class AppConfig {
    
    @Bean(name="vegPizzaBean")
    public Pizza vegNameBeanPizza(){
        return new VegPizzaBean();
    }
}


public static void main(String[] args) {
	var context = SpringApplication.run(DescriptionSpringBootAnnotationApplication.class, args);
	VegPizzaBean vegPizza2 = (VegPizzaBean) context.getBean("vegPizzaBean");
	System.out.println(vegPizza2.getPizza());
}


The Output : Vegana Pizza Bean!

```

### @Repository, @Controller and @Service

In Spring, the @Repository, @Service, and @Controller annotations are used to mark classes with specific roles within the layered architecture of an application. These annotations are specializations of **@Component** and help improve code readability and management by clearly indicating the purpose of each class.

* **@Repository**: For the data access layer, interacting with the database.
* **@Service**: For the business logic layer, processing data and applying business rules.
* **@Controller**: For the presentation layer, handling HTTP requests and returning responses.

Example ***@Controller***  

```java 
@Controller
public class MyController {
    
    public String hello(){
        return "Hello Controller";
    }
}

```
The annotation **@Controller** have annotation **@Component** 


```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Controller {

	/**
	 * Alias for {@link Component#value}.
	 */
	@AliasFor(annotation = Component.class)
	String value() default "";

}
```

Example **@Service**
```java
@Service
public class MyService {
    
    public String myService(){
        return "My Service";
    }
}

```
The annotation @Service have annotation @Component 

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Service {

	/**
	 * Alias for {@link Component#value}.
	 */
	@AliasFor(annotation = Component.class)
	String value() default "";

}
```
Example ***@Repository***:
```java 
@Repository
public class MyRepository {
    
  public String myRepository(){
        return "My Repository";
  }
}
```
The annotation @Repository have annotation @Component
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Repository {

	/**
	 * Alias for {@link Component#value}.
	 */
	@AliasFor(annotation = Component.class)
	String value() default "";

}
```


Example output:
```java
@SpringBootApplication
public class DescriptionSpringBootAnnotationApplication {

  public static void main(String[] args) {
  	 var context = SpringApplication.run(DescriptionSpringBootAnnotationApplication.class, args);
     MyController myController= context.getBean(MyController.class);
  	 System.out.println(myController.hello());  
  	 MyService myService = context.getBean(MyService.class);
  	 System.out.println(myService.myService());  
  	 MyRepository myRepository = context.getBean(MyRepository.class);
  	 System.out.println(myRepository.myRepository());  
  
  }

}

Output: 
Hello Controller
My Service
My Repository
```
### @Lazy 
Lazy Initialization in Spring

Lazy initialization means that a bean will only be created when it is needed. By default, Spring initializes all singleton beans at startup, but you can configure it to create beans only on demand, improving startup time and resource efficiency.

 Esto significa que todos los beans singleton se crean y se inicializan al inicio del contexto de la aplicación. Esto ocurre al arrancar la aplicación, lo que puede incrementar el tiempo de inicio si hay muchos beans o si algunos de ellos son costosos de crear.


```java

package com.spring.annotations.example.lazy;

/**
 * Al inicializar Spring Carga Este
 * bean en el contexto de Spring
 * inicializandolo
 * */ 
@Component
public class EagerLoader {
    
   public EagerLoader(){
    System.out.println("EagerLoader...");
   }
}
```
Carga LazyLoader
```java
/**
 * Bean bajo demanda , no se carga en el contexto 
 * de Spring.
 */

@Component
@Lazy
public class LazyLoader {
    
    public LazyLoader(){
        System.out.println("LazyLoader...");
    }
}
```
Inicializamos el bean correspondiente:
```java 
@SpringBootApplication
public class DescriptionSpringBootAnnotationApplication {

	public static void main(String[] args) {
		 var context = SpringApplication.run(DescriptionSpringBootAnnotationApplication.class, args);
	  
		LazyLoader lazyLoader = context.getBean(LazyLoader.class);
	
	}

}
The output: LazyLoader...
```


### @Scope 

The **@Scope** annotation in Spring is a powerful tool for managing the lifecycle of beans in your application. By choosing the appropriate scope, you can optimize resource usage and state management in your application.


Here is a summary of the different types of scopes in Spring:

* Singleton:
        Description: Default scope. Only one instance per Spring container.
        Usage: Ideal for stateless beans or shared state.
* Prototype:
        Description: A new instance is created each time the bean is requested.
        Usage: Suitable for stateful beans that should not be shared.

* Request (Web applications only):
        Description: A new instance is created for each HTTP request.
        Usage: Useful for beans specific to a single HTTP request.

* Session (Web applications only):
        Description: A new instance is created for each HTTP session.
        Usage: Useful for beans that should last for an entire user session.

* Application (Web applications only):
        Description: A single instance per servlet context.
        Usage: Suitable for beans shared across the entire application.

* WebSocket (Web applications only):
        Description: A new instance for each WebSocket session.
        Usage: Useful for beans that should last for the duration of a WebSocket session.

Example SingletonBean.java
```java

@Component
@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SingletonBean {
    
    public SingletonBean(){
        System.out.println("SingletonBean...");
    }
}
```
Example Scope PrototypeBean
```java
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeBean {
    
    public PrototypeBean(){
        System.out.println("PrototypeBean...");
    }
}
```

```java

	public static void main(String[] args) {
		 var context = SpringApplication.run(DescriptionSpringBootAnnotationApplication.class, args);

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

	}

The output: SingletonBean is only one time is executed
SingletonBean...
1108106766
1108106766
1108106766
PrototypeBean...
1568061605
PrototypeBean...
2099933483
PrototypeBean...
1578770446

```

### @Value 
The **@Value** annotation in Spring is used to inject values into fields, methods, and constructors in your Spring-managed beans. These values can come from various sources, such as properties files, system properties, environment variables, or even directly specified strings.


Example 
application.properties add:
```java
email.host=manuonda@gmail.com
```
Bean ValueAnnotationDemo
```java 
@Component
public class ValueAnnotationDemo {
    

    @Value("Default Name")
    private String defaultName;

    //default value
    @Value("${email.host:andres@gmail.com}")
    private String emailHost;

    //variable environment
    @Value("${java.home}")
    private String javaHome;


    public String getEmailHost() {
        return emailHost;
    }

    public String getDefaultName(){
        return defaultName;
    }

    public String getJavaHome() {
        return javaHome;
    }
}


   // Execute Bean
	public static void main(String[] args) {
		 var context = SpringApplication.run(DescriptionSpringBootAnnotationApplication.class, args);
	
		ValueAnnotationDemo valueAnnotationDemo = context.getBean(ValueAnnotationDemo.class);
		System.out.println(valueAnnotationDemo.getDefaultName());
		System.out.println(valueAnnotationDemo.getEmailHost());
		System.out.println(valueAnnotationDemo.getJavaHome());
		
	}

    The Output: 
    Default Name
    manuonda@gmail.com
    /usr/lib/jvm/java-17-openjdk-amd64

```


#### @PropertySource and @PropertySources Annotations


* Spring **@PropertySource** annotation is used to provide properties file to Spring Environment.

* Spring **@PropertySources** annotation is used to provide multiple properties to Spring Environment.

File **resources/mail.properties**
```
gmail.host=gmail.com
gmail.email=manuonda@gmail.com
gmail.password=123456
```
Is created the class ```propertysource/AppConfigProperty.java```  that use the file **mail.properties**
```
package com.spring.annotations.example.propertysource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:mail.properties")
public class AppConfigProperty {

}


```
Is created the class ```propertysource/PropertySourceDemo.java```

```
package com.spring.annotations.example.propertysource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertySourceDemo {


    @Value("${gmail.host}")
    private String host;

    @Value("${gmail.email}")
    private String email;

    @Value("${gmail.password}")
    private String password;


    public String getHost(){
        return this.host;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return password;
    }

}

```

For use this class in the Application , inject code next: 
```
@SpringBootApplication
public class DescriptionSpringBootAnnotationApplication {

	public static void main(String[] args) {
		 var context = SpringApplication.run(DescriptionSpringBootAnnotationApplication.class, args);
	
		/********* @Property Source ****** */
		PropertySourceDemo propertySourceDemo = context.getBean(PropertySourceDemo.class);
		System.out.println("Host: " + propertySourceDemo.getHost());
		System.out.println("Email : "+ propertySourceDemo.getEmail());
		System.out.println("Password : "+ propertySourceDemo.getPassword());

	}

}

The output is next: 
Host: gmail.com
Email : manuonda@gmail.com
Password : 123456
```

In the case of using multiple properties files we can use :
Create file  ```src/main/resources/messages.properties```
```
app.name=Spring Boot App
app.description=Spring Boot App Description

```

In the class file ```AppConfigProperty.java``` add reference to ```messages.properties```:

```
package com.spring.annotations.example.propertysource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:mail.properties")
@PropertySource("classpath:messages.properties")
public class AppConfigProperty {

}
```
In the class ```PropertySourceDemo.java``` add fields :
```

@Component
public class PropertySourceDemo {


    @Value("${gmail.host}")
    private String host;

    @Value("${gmail.email}")
    private String email;

    @Value("${gmail.password}")
    private String password;

    @Value("${app.name}")
    private String appName;

    @Value("${app.description}")
    private String description;


    public String getHost(){
        return this.host;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return password;
    }
    
    public String getAppName(){
        return this.appName;
    }

    public String getDescription(){
        return this.description;
    }
}

```
Run application java :
```

@SpringBootApplication
public class DescriptionSpringBootAnnotationApplication {

	public static void main(String[] args) {
	/********* @Property Source ****** */
		PropertySourceDemo propertySourceDemo = context.getBean(PropertySourceDemo.class);
		System.out.println("Host: " + propertySourceDemo.getHost());
		System.out.println("Email : "+ propertySourceDemo.getEmail());
		System.out.println("Password : "+ propertySourceDemo.getPassword());
		System.out.println("App Name : "+ propertySourceDemo.getAppName());
		System.out.println("Description : "+ propertySourceDemo.getDescription());
        	}

}
The output :
Host: gmail.com
Email : manuonda@gmail.com
Password : 123456
App Name : Spring Boot App
Description : Spring Boot App Description

```

#### Use of **Environment**:

In the clase ```PropertySourceDemo.java``` we can inject annotation Environment with @Autowired

```
@Component
public class PropertySourceDemo {


    @Autowired
    private Environment environment;


    @Value("${gmail.host}")
    private String host;

    @Value("${gmail.email}")
    private String email;

    @Value("${gmail.password}")
    private String password;

    @Value("${app.name}")
    private String appName;

    @Value("${app.description}")
    private String description;


    public String getHost(){
        return this.host;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return password;
    }
    
    public String getAppName(){
        return this.appName;
    }

    public String getDescription(){
        return this.description;
    }


    public String getEnvironmentPropertyHost(){
        return environment.getProperty("gmail.host");
    }

    public String getEnvironmentPropertyServer(){
        return environment.getProperty("gmail.email");
    }   
}


```

In the Application Run:


```

@SpringBootApplication
public class DescriptionSpringBootAnnotationApplication {

	public static void main(String[] args) {
	
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

The output:

Host: gmail.com
Email : manuonda@gmail.com
Password : 123456
App Name : Spring Boot App
Description : Spring Boot App Description
Environment Property Host : gmail.com
Environment Property Server : manuonda@gmail.com
```

Another way to load properties from files is the following code in the  ``` PropertySourceDemo.java``` class

```java
@Configuration
// @PropertySource("classpath:mail.properties")
// @PropertySource("classpath:messages.properties")

@PropertySources({
    @PropertySource("classpath:mail.properties"),
    @PropertySource("classpath:messages.properties")
})
public class AppConfigProperty {

}

```

###  `@ConfigurationProperties` in Spring Boot

In Spring Boot, the `@ConfigurationProperties` annotation maps external configuration properties (from files like `application.properties` or `application.yml`) to a Java class. This approach organizes configurations in an object, making them easier to manage.

### Steps to Use `@ConfigurationProperties`

1. **Define a Configuration Class**: Create a Java class with fields matching the properties you need.
2. **Set Properties in `application.properties` or `application.yml`**: Use a common prefix for related properties.
3. **Annotate with `@ConfigurationProperties`**: Specify the prefix.
4. **Enable with `@EnableConfigurationProperties`**: Do this in a config class or the main application class.

***application.properties***
```java
app.name=ConfigurationProperties
app.description=ConfigurationPropertiesDemo Description
app.uploadDir=/uploads


#Nested objects
app.security.username=manuonda
app.security.password=secret
app.security.roles=USER,ADMIN,GUEST
app.security.enabled=true


## Map properties
app.security.permissions.ADD=true
app.security.permissions.UPDATE=true
app.security.permissions.DELETE=true
app.security.permissions.GET=true



```

Create class **configurationproperties/ApplicationProperties.java**
```java

package com.spring.annotations.example.configurationproperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix ="app")
public class AppProperties {

    private String name;
    private String description;
    private String uploadDir;
    private Security security = new Security();
    

    public static class Security{
        private String username;
        private String password;
        List<String> roles = new ArrayList<>();
        private boolean enabled;
        private Map<String, String> permissions = new HashMap<>();


        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public List<String> getRoles() {
            return roles;
        }
        public void setRoles(List<String> roles) {
            this.roles = roles;
        }
        public boolean isEnabled() {
            return enabled;
        }
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
        public Map<String, String> getPermissions() {
            return permissions;
        }
        public void setPermissions(Map<String, String> permissions) {
            this.permissions = permissions;
        }

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUploadDir() {
        return uploadDir;
    }
    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
    public Security getSecurity() {
        return security;
    }
    public void setSecurity(Security security) {
        this.security = security;
    }


    

}


```

Using class **configurationproperties/AppDemo.java**

```java 
package com.spring.annotations.example.configurationproperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppDemo {
    @Autowired
    private AppProperties appProperties;

    public void display(){
        System.out.println(appProperties.getName());
        System.out.println(appProperties.getDescription());
        System.out.println(appProperties.getUploadDir());
        System.out.println(appProperties.getSecurity().getUsername());
        System.out.println(appProperties.getSecurity().getPassword());
        System.out.println(appProperties.getSecurity().getRoles());
        System.out.println(appProperties.getSecurity().isEnabled());
        System.out.println(appProperties.getSecurity().getPermissions());
    }
    
}
```

Execute class in the main principal: 
```java

	public static void main(String[] args) {
		 var context = SpringApplication.run(DescriptionSpringBootAnnotationApplication.class, args);
	 	/*** ConfigurationProperties */
		System.out.println("Configuration Properties");
		AppDemo appDemo = context.getBean(AppDemo.class);
		appDemo.display();
	}

    
  Output:
    Description : ConfigurationPropertiesDemo Description
    Environment Property Host : gmail.com
    Environment Property Server : manuonda@gmail.com
    Configuration Properties
    ConfigurationProperties
    ConfigurationPropertiesDemo Description
    /uploads
    manuonda
    secret
    [USER, ADMIN, GUEST]
    true
    {ADD=true, UPDATE=true, DELETE=true, GET=true}
```


###  ```@Controller``` and ```@ResponseBody``` in Spring Boot

In Spring Boot, `@Controller` is used to define a class that handles HTTP requests, often serving HTML pages. When combined with `@ResponseBody`, methods can return data directly as the HTTP response, typically JSON. For RESTful APIs, the `@RestController` annotation is a more concise option that combines both `@Controller` and `@ResponseBody`.


That use I need ```spring-boot-starter-web```
Create class Controller ```BookController.java```

```java
package com.spring.annotations.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class BookController {

    @RequestMapping("/hello-world")
    @ResponseBody
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/book")
    @ResponseBody
    public Book getBook() {
        return new Book(1, "Title1", "Description one");
    }
    

    public record Book(Integer id, String name, String description){}
    
}
```


#### @RestController 

```@RestController ```when all methods in a controller should return data directly, as it combines **@Controller** and **@ResponseBody**

Modificate class ```BookController.java```

```java
package com.spring.annotations.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


//@Controller
//@ResponseBody
@RestController
public class BookController {

    @RequestMapping("/hello-world")
    //@ResponseBody
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/book")
    //@ResponseBody
    public Book getBook() {
        return new Book(1, "Title1", "Description one");
    }
    

    public record Book(Integer id, String name, String description){}
    
}
```

### @RequestMapping
```@RequestMapping``` annotation is used to map web requests to Spring Controller methods

```@RequestMapping``` can be applied to the controller class as well as handler methods.

```java
//@Controller
//@ResponseBody
@RestController
@RequestMapping("/api")
public class BookController {

    @RequestMapping("/hello-world")
    //@ResponseBody
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/book")
    //@ResponseBody
    public Book getBook() {
        return new Book(1, "Title1", "Description one");
    }
    

    public record Book(Integer id, String name, String description){}
    
}
```

### @GetMapping
1 -  The GET HTTP request is used to get a single or multiple resources and @GetMapping annotation for mapping HTTP GET requests onto specific handler methods.

2 - Specifically, @GetMapping is a composed annotation that acts as a shortcut for @RequestaMapping(method=RequestMethod.GET)

```java
//@Controller
//@ResponseBody
@RestController
@RequestMapping("/api")
public class BookController {

    @RequestMapping("/hello-world")
    //@ResponseBody
    public String hello() {
        return "Hello World";
    }

    //@RequestMapping("/book")
    //@ResponseBody
    @GetMapping(value = {"/books","/java"})
    public Book getBook() {
        return new Book(1, "Title1", "Description one");
    }
    

    public record Book(Integer id, String name, String description){}
    
}
```

### @PostMapping and @RequestBody
1 - The post Http methos is used to create a resource and ```@PostMapping``` annotation for mapping HTTP POST request onto specific handler method.

2 - Specifically, ```@PostMapping``` is a composed annotation that acts as a shortcurt for ```@RequestMapping(method=RequestMethod.POST)```

3 - The ```@RequestBody``` annotation is responsible for retrieving the HTTP request body and automatically converting it to the Java object.

```java
//@Controller
//@ResponseBody
@RestController
@RequestMapping("/api")
public class BookController {

    @RequestMapping("/hello-world")
    //@ResponseBody
    public String hello() {
        return "Hello World";
    }

    //@RequestMapping("/book")
    //@ResponseBody
    @GetMapping(value = {"/books","/java"})
    public Book getBook() {
        return new Book(1, "Title1", "Description one");
    }
    
    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping(value = "/books/create" ,
    consumes= MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        System.out.println(book.id());
        System.out.println(book.name());
        System.out.println(book.description());
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
       // return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
    

    public record Book(Integer id, String name, String description){}
    
}

```
### @PutMapping 

1 - The PUT HTTP methos is used to update the resource and ```@PutMapping```annotation for mapping HTTP PUT request onto specific handler method.

2 - Specifically, ```@PutMapping``` is a composed annotation that acts as shortcurt for ```@RequestMapping(method=RequestMethod.PUT)```

```java 
//@Controller
//@ResponseBody
@RestController
@RequestMapping("/api")
public class BookController {

    @RequestMapping("/hello-world")
    //@ResponseBody
    public String hello() {
        return "Hello World";
    }

    //@RequestMapping("/book")
    //@ResponseBody
    @GetMapping(value = {"/books","/java"})
    public Book getBook() {
        return new Book(1, "Title1", "Description one");
    }
    

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping(value = "/books/create" ,
    consumes= MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        System.out.println(book.id());
        System.out.println(book.name());
        System.out.println(book.description());
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
       // return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
    

    // @RequestMapping(value = "/books/update/{id}", 
    //  method=RequestMethod.PUT,
    //  produces = MediaType.APPLICATION_JSON_VALUE)
    
    @PutMapping("/api/books/update/{id}")
    public ResponseEntity<Book> updateBookEntity(@RequestBody Book book, @PathVariable("id") Integer id) {
        System.out.println(book.id());
        System.out.println(book.name());
        System.out.println(book.description());
        
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }
    

    public record Book(Integer id, String name, String description){}
    
}
```


### @DeleteMapping 

1 - The DELETE HTTP methos is used to delete the resource and ```@DeleteMapping```annotation for mapping HTTP DELETE request onto specific handler method.

2 - Specifically, ```@DeleteMapping``` is a composed annotation that acts as shortcurt for ```@RequestMapping(method=RequestMethod.DELETE)```


```java
    // @RequestMapping(value = "/books/update/{id}", 
    //  method=RequestMethod.DELETE)
    @DeleteMapping("/api/books/update/{id}")
    public ResponseEntity<String> deleteBookEntity(@PathVariable("id") Integer id) {
        System.out.println("id : {}".formatted(id));
       return ResponseEntity.status(HttpStatus.OK).body("Book delete successfully")
    }
    

    public record Book(Integer id, String name, String description){}
    
```


### @PathVariable and @RequestParam

1 - ```@PathVariable``` annotation used on a method argument to bind the value of a URI template variable to a method argument

```java 


    @GetMapping("/books/{id}/{title}/{description}")
    public void pathVariableDemo(@PathVariable("id")  Integer id,
                                 @PathVariable("title") String title,
                                 @PathVariable("description") String description) {
        System.out.println("id : {}".formatted(id));
        System.out.println("title : {}".formatted(title));
        System.out.println("description : {}".formatted(description));

    }
```


2 - ```@RequestParam```Annotation
We can use @RequestParam to extract query parameters from the request

```java 

    //http://localhost:8080/api/books/query?id=1&title=Core Java
    @GetMapping("/books/query")
    public void requestParam(@RequestParam("id") Integer id, 
                             @RequestParam("title") String title) {
        System.out.println("id : {}".formatted(id));
        System.out.println("title : {}".formatted(title));
    }
```


### Links 
[Course  - Build REST APIs using Spring Boot, Spring Security 6, JWT, Spring Data JPA, Hibernate, MySQL, Docker &amp; Deploy on AWS](https://www.udemy.com/course/building-real-time-rest-apis-with-spring-boot/)

