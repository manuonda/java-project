package com.events.events_demo.listeners;

import com.events.events_demo.events.OrderCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class LogOrderDetailListener {

    private static final Logger log = LoggerFactory.getLogger(LogOrderDetailListener.class);

    @EventListener
    @Async
    @Order(2)
    public void logOrderCreated(OrderCreateEvent event){
        log.info("logOrderCreated id : " +event.toString());
    }
}
