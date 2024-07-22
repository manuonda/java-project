package com.dgarcia.bookstore.notifications.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.dgarcia.bookstore.notifications.domain.entity.OrderEventEntity;
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
    void handleOrderCreatedEvent(OrderCreatedEvent event){
        logger.info("handleevent : {}", event.toString());
        if(orderEventRepository.existsByEventId(event.eventId())){
            logger.warn("Receive duplicated OrderCreatedEvent with eventId : "+event.eventId());
            return;
        }
        this.notificationService.sendOrderCreatedNotification(event);
        OrderEventEntity orderEvent = new OrderEventEntity(event.eventId());
        orderEventRepository.save(orderEvent);
    }

    @RabbitListener(queues = "${notifications.delivered-orders-queue}")
    void handleOrderDeliveredEvent(OrderDeliveredEvent event){
        logger.info("handleOrderDeliveredEvent : {}", event.toString());
        if(orderEventRepository.existsByEventId(event.eventId())){
            logger.warn("Receive duplicated OrderDeliveredEvent with eventId : "+event.eventId());
            return;
        }
        this.notificationService.sendOrderDeliveredNotification(event);
        OrderEventEntity orderEvent = new OrderEventEntity(event.eventId());
        orderEventRepository.save(orderEvent);
    }


    @RabbitListener(queues = "${notifications.cancelled-orders-queue}")
    void handleOrderCancelledEvent(OrderCancelledEvent event){
        logger.info("handleOrderCancelledEvent : {}", event.toString());
        if(orderEventRepository.existsByEventId(event.eventId())){
            logger.warn("Receive duplicated OrderCancelledEvent with eventId : "+event.eventId());
            return;
        }
        this.notificationService.sendOrderCancelledNotification(event);
        OrderEventEntity orderEvent = new OrderEventEntity(event.eventId());
        orderEventRepository.save(orderEvent);
    }

    @RabbitListener(queues = "${notifications.error-orders-queue}")
    void handleOrderErrorEvent(OrderErrorEvent event){
        logger.info("handle order Error event : {}", event.toString());
        if(orderEventRepository.existsByEventId(event.eventId())){
            logger.warn("Receive duplicated OrderErrorEvent with eventId : "+event.eventId());
            return;
        }
        this.notificationService.sendOrderErrorEventNotification(event);
        OrderEventEntity orderEvent = new OrderEventEntity(event.eventId());
        orderEventRepository.save(orderEvent);
    }
}
