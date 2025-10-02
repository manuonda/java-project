package com.events.events_demo.listeners;


import com.events.events_demo.events.OrderCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationListener {


    private static final Logger log = LoggerFactory.getLogger(EmailNotificationListener.class);

    @EventListener
    @Async
    @Order(1)
    public void handleOrderCreateAndSendEmail(OrderCreateEvent orderCreateEvent){
         log.info("handleOrderCreateAndSendEmail " + orderCreateEvent.toString());
    }

}
