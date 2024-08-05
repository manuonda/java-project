package com.example.k8;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class SpringK8ExampleApplicationTests {

	@Test
	void contextLoads() {
	}

}
