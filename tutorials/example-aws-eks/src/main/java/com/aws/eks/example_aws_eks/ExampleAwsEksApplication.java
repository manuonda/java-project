package com.aws.eks.example_aws_eks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
@RestController
public class ExampleAwsEksApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleAwsEksApplication.class, args);
	}


	@GetMapping("/greetings")
	public String getMethodName() {
		return "Welcome to manuonda que onda in AWS EKS";
	}

	

}
