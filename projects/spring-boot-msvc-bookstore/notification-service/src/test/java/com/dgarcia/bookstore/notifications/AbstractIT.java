package com.dgarcia.bookstore.notifications;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import com.github.dockerjava.api.model.ContainerConfig;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(ContainerConfig.class)
public abstract class AbstractIT {
 @LocalServerPort
    int port;

    
    @BeforeAll
    static void beforeAll(){
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){
    }

    @BeforeEach
    void setUp(){
        RestAssured.port = port;
    }


}
