package com.springboot.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;


@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Spring Boot Blog App Rest Apis",
		description = "Spring Boot Blog App Rest Apis Documentation",
		version="v1.0",
		contact = @Contact(
			name = "David",
			email = "manuonda@gmail.com",
			url = "https://github.com/manuonda/java-project/tree/main/projects/springboot-blog-rest-api"
		),
		license = @License(
			name = "Apache 2 .0",
			url="https://github.com/manuonda/java-project/tree/main/projects/springboot-blog-rest-api"
		)
	),
	externalDocs = @ExternalDocumentation(
		description = "Spring Boot Blog App Documentation",
		url="https://github.com/manuonda/java-project/tree/main/projects/springboot-blog-rest-api"
	)
)
public class SpringbootBlogRestApiApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		var informacion  =" Prueba";
		System.out.println("Informacion numeo 23");
	}

}
