/**
 * The MessagePublisher class is responsible for publishing messages to a Kafka topic.
 * It uses a KafkaTemplate to send messages and handles the completion of the send operation.
 * 
 * This class is part of the Outbox Pattern implementation and is used to publish messages
 * related to orders.
 */
package com.pattern.publisher;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.pattern.config.ApplicationProperties;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class MessagePublisher {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ApplicationProperties applicationProperties;

    public MessagePublisher(ApplicationProperties applicationProperties){
        this.applicationProperties = applicationProperties;
    }

    public void publish(String payload){
        CompletableFuture<SendResult<String,String>> future = this.kafkaTemplate
        .send(this.applicationProperties.orderTopicName(),payload);
 
        future.whenComplete((result,ex) -> {
           if ( ex == null) {
            System.out.println("Send message=["+payload+"] "+
            "with offset = [ "+ result.getRecordMetadata().offset()+"]");
           } else {
            System.out.println("Unable to Send message=["+payload+"] "+
            "due to= [ "+ ex.getMessage()+"]");
           }
        });

    }
}
