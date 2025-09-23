package com.modulith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.modulith.Modulith;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ModulithDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModulithDemoApplication.class, args);
	}

}
