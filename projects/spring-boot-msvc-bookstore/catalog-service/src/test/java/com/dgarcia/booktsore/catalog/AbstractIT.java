package com.dgarcia.booktsore.catalog;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

import io.restassured.RestAssured;

@Import(ContainerConfig.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractIT {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp(){
        RestAssured.port = port;
    }
}
