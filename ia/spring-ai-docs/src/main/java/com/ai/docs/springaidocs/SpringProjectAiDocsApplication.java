package com.ai.docs.springaidocs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;


@CommandScan
@SpringBootApplication
public class SpringProjectAiDocsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringProjectAiDocsApplication.class, args);
	}

}
