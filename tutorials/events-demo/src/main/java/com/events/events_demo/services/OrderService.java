package com.events.events_demo.services;


import com.events.events_demo.events.OrderCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void createOrder(String orderId) {
        log.info("Creating order with id {}", orderId);
        this.applicationEventPublisher.publishEvent(new OrderCreateEvent(orderId));
    }
}
