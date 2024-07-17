package com.dgarcia.server.demo;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@Slf4j
public class DemoController {


    @GetMapping("demo-service")
    public void getMethodName(@RequestParam String param) throws InterruptedException {
        Thread.sleep(3000); //3 seconds
        log.info("Blocking for 3 seconds");
    }
    
}
