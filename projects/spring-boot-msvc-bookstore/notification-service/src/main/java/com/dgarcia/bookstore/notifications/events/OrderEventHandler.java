package com.dgarcia.bookstore.notifications.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.dgarcia.bookstore.notifications.domain.models.OrderCancelledEvent;
import com.dgarcia.bookstore.notifications.domain.models.OrderCreatedEvent;
import com.dgarcia.bookstore.notifications.domain.models.OrderDeliveredEvent;
import com.dgarcia.bookstore.notifications.domain.models.OrderErrorEvent;
import com.dgarcia.bookstore.notifications.repository.OrderEventRepository;
import com.dgarcia.bookstore.notifications.service.NotificationService;

@Component
public class OrderEventHandler {

    private final static Logger logger= LoggerFactory.getLogger(OrderEventHandler.class);

    private final NotificationService notificationService;
    private final OrderEventRepository orderEventRepository;


    public OrderEventHandler(NotificationService notificationService , OrderEventRepository orderEventRepository){
        this.notificationService = notificationService;
        this.orderEventRepository = orderEventRepository;
    }

    @RabbitListener(queues = "${notifications.new-orders-queue}")
    void handleOrderCreatedEvent(OrderCreatedEvent orderCreatedEvent){
        logger.info("handleOrderCreatedEvent : {}", orderCreatedEvent.toString());
        this.notificationService.sendOrderCreatedNotification(orderCreatedEvent);
    }

    @RabbitListener(queues = "${notifications.delivered-orders-queue}")
    void handleOrderDeliveredEvent(OrderDeliveredEvent event){
        logger.info("handleOrderCreatedEvent : {}", event.toString());
        this.notificationService.sendOrderDeliveredNotification(event);
    }


    @RabbitListener(queues = "${notifications.cancelled-orders-queue}")
    void handleOrderCancelledEvent(OrderCancelledEvent event){
        logger.info("handleOrderCancelledEvent : {}", event.toString());
        this.notificationService.sendOrderCancelledNotification(event);
    }

    @RabbitListener(queues = "${notifications.error-orders-queue}")
    void handleOrderErrorEvent(OrderErrorEvent event){
        logger.info("handleOrderCancelledEvent : {}", event.toString());
        this.notificationService.sendOrderErrorEventNotification(event);
    }
}
