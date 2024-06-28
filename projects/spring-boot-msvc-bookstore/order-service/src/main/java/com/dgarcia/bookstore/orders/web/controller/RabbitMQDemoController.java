package com.dgarcia.bookstore.orders.web.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.dgarcia.bookstore.orders.ApplicationProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class RabbitMQDemoController {

    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties properties;
    private final AmqpTemplate amqpTemplate;

    public RabbitMQDemoController(
       RabbitTemplate rabbitTemplate,
       ApplicationProperties properties,
       AmqpTemplate amqpTemplate
    ){
        this.rabbitTemplate = rabbitTemplate;
        this.properties = properties;
        this.amqpTemplate = amqpTemplate;
    }


    @PostMapping("/send")
    public void postMethodName(@RequestBody MyMessage message) {

        amqpTemplate.convertAndSend(properties.orderEventsExchange(), message.routingKey(), message.payload());
        // rabbitTemplate.convertAndSend(
        //     properties.orderEventsExchange(),
        //     message.routingKey(),
        //     "informacion data"
        // );
    }
    

    record MyMessage(String routingKey, MyPayload payload){}

    record MyPayload(String content){}

}
