package com.dgarcia.client_demo;

import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties.Restclient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ClientDemoController {


    private final RestClient restClient;

    public ClientDemoController(RestClient.Builder builder){
        this.restClient = builder
        .baseUrl("http://localhost:8081/")
        .build();
    }

    @GetMapping("client")
    public String getMethodName(@RequestParam String param) throws InterruptedException {
      log.info("Request thread {} - {} ", Thread.currentThread().threadId(), Thread.currentThread().getName());
      this.restClient.get().uri("/demo-service").retrieve().toBodilessEntity();
      log.info("Request thread {} - {} ", Thread.currentThread().threadId(), Thread.currentThread().getName());
      return Thread.currentThread().toString();
    }
    
}