package com.modulith.cqrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulith;

@SpringBootApplication
@Modulith
public class DemoStoreCqrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoStoreCqrsApplication.class, args);
	}

}
