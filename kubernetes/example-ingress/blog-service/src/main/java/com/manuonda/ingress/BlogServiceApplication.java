package com.manuonda.ingress;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@SpringBootApplication
@RestController
public class BlogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogServiceApplication.class, args);
	}

	@GetMapping("/allBlogs")
	public List<Blog> getMethodName(@RequestParam String param) {
		return Stream.of(
			new Blog("1", "title1", "content1", "author1"),
			new Blog("2", "title2", "content2", "author2")
		).collect(Collectors.toList());
	}
	

}
