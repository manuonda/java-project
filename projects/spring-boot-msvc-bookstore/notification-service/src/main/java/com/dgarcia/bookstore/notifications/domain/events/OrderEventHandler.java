package com.dgarcia.bookstore.notifications.domain.events;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.dgarcia.bookstore.notifications.domain.models.OrderCreatedEvent;

@Component
public class OrderEventHandler {


    @RabbitListener(queues = "${notifications.new-orders-queue}")
    void handleOrderCreatedEvent(OrderCreatedEvent orderCreatedEvent){
        System.out.println("Order created Event : " +  orderCreatedEvent.toString());
    }
}
