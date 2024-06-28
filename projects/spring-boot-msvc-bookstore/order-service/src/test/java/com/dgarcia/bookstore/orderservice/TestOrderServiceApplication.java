package com.dgarcia.bookstore.orderservice;

import org.springframework.boot.SpringApplication;

import com.dgarcia.bookstore.orders.OrderServiceApplication;

public class TestOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(OrderServiceApplication::main).with(ContainerConfiguration.class).run(args);
	}

}
