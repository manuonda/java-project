package com.pattern.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.pattern.config.ApplicationProperties;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OutboxMessageConsumer {

    private ApplicationProperties applicationProperties;

    public OutboxMessageConsumer(ApplicationProperties applicationProperties){
        this.applicationProperties = applicationProperties;
    }


    @KafkaListener(topics = "unprocessed-order-events",groupId = "")
    public void consume(String payload){
        log.info("Event consumed {}", payload);
    }
    
}
