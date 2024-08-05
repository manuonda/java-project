package com.example.k8;

import org.springframework.boot.SpringApplication;

public class TestSpringK8ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringK8ExampleApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
