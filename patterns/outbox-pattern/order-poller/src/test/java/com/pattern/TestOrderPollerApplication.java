package com.pattern;

import org.springframework.boot.SpringApplication;

public class TestOrderPollerApplication {

	public static void main(String[] args) {
		SpringApplication.from(OrderPollerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
