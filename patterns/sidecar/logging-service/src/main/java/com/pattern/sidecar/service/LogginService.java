package com.pattern.sidecar.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LogginService {

    private static final Logger log =  LoggerFactory.getLogger(LogginService.class);

    public static final String LOG_FILE_PATH =" /logs/book-service.log";
    
    LogginService(){
        log.info("Logging Service initialized...");
    }

    @Scheduled(fixedRate = 60000)
    public void processLog(){
        log.info("LogginService scheduler triggered {} ", new Date());
        try( BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_PATH))){
        String line;
        while((line = reader.readLine()) != null ) {
            // Simulate  pushing logs to a centralized system
            // -> splunk, ELK, ES
            log.info("Processing log : " ,line);
        }
     } catch (Exception e) {
        log.error("error{}", e.getMessage());
     }
    }
}
