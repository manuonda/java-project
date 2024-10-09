package com.example.k8.ingress.course;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.example.k8.ingress.course.dto.Course;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@SpringBootApplication
@RestController
public class CourseServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(CourseServiceApplication.class, args);
	}

	@GetMapping("/allCourses")
	public List<Course> getMethodName() {
		return Stream.of(
			new Course(1L,"course1", 23.5),
			new Course(2L,"course2",25.4)
		).collect(Collectors.toList());
	}
	

}
