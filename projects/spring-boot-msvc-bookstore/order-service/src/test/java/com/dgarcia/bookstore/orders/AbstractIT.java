package com.dgarcia.bookstore.orders;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.wiremock.integrations.testcontainers.WireMockContainer;

import com.github.tomakehurst.wiremock.client.WireMock;

import io.restassured.RestAssured;



@Import(ContainerConfiguration.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AbstractIT {


    @LocalServerPort
    int port;

    static WireMockContainer wiremockServer = new WireMockContainer("wiremock/wiremock:3.5.2-alpine");
    
    @BeforeAll
    static void beforeAll(){
        wiremockServer.start();
        System.out.println("host =>  : " + wiremockServer.getHost());
        System.out.println("posrt : => " + wiremockServer.getPort());
        configureFor(wiremockServer.getHost(),wiremockServer.getPort());
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){
        //en este caso que el registro apunte a wiremock base url 
        //quieres que esta URL apunte a un servidor WireMock en lugar del servicio real.
        registry.add("orders.catalog-service-url", wiremockServer::getBaseUrl);
    }

    @BeforeEach
    void setUp(){
        RestAssured.port = port;
    }


    protected static void mockGetProductByCode(String code, String name, BigDecimal price) {
        System.out.println("wiremo ckser vget url : " + wiremockServer.getUrl("/"));
        
        stubFor(WireMock.get(urlMatching("/api/v1/products/code/" + code))
                .willReturn(
                         aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)
                        .withBody(
                                """
                    {
                        "code": "%s",
                        "name": "%s",
                        "price": %f
                    }
                """
                .formatted(code, name, price.doubleValue()))));
    }


}
