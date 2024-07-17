package com.dgarcia.bookstore.notifications.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import com.dgarcia.bookstore.notifications.ApplicationProperties;
import com.dgarcia.bookstore.notifications.domain.models.OrderCancelledEvent;
import com.dgarcia.bookstore.notifications.domain.models.OrderCreatedEvent;
import com.dgarcia.bookstore.notifications.domain.models.OrderDeliveredEvent;
import com.dgarcia.bookstore.notifications.domain.models.OrderErrorEvent;



@Component
public class OrderEventPublisher {

    private final AmqpTemplate amqpTemplate;
    private final ApplicationProperties properties;

    public OrderEventPublisher(
        AmqpTemplate amqpTemplate,
        ApplicationProperties properties
    ){
      this.amqpTemplate = amqpTemplate;
      this.properties= properties;
    }

    public void publish(OrderCreatedEvent event) {
        this.send(properties.newOrdersQueue(), event);
    }

    public void publish(OrderDeliveredEvent event) {
        this.send(properties.deliveredOrdersQueue(), event);
    }

    public void publish(OrderCancelledEvent event) {
        this.send(properties.cancelledOrdersQueue(), event);
    }

    public void publish(OrderErrorEvent event) {
        this.send(properties.errorOrdersQueue(), event);
    }

    private void send(String routingKey, Object payload) {
        this.amqpTemplate.convertAndSend(properties.orderEventsExchange(), routingKey, payload);
    }

}
