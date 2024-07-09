package com.dgarcia.bookstore.orders.jobs;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dgarcia.bookstore.orders.service.OrderEventService;

/**
 * Job que permite leer 
 */
@Component
public class OrderEventsPublishingJob {

    private static final Logger logger = LoggerFactory.getLogger(OrderEventsPublishingJob.class);

    private final OrderEventService orderEventService;

    public OrderEventsPublishingJob(OrderEventService orderEventService){
        this.orderEventService = orderEventService;
    }

    /**
     * Job que permite leer la informacion 
     * de la base de datos cada 5 segundos
     */
    @Scheduled(cron = "${orders.publish-order-events-job-cron}")
    public void publishOrderEvents(){
        logger.info("Publishing Order Events at {}", Instant.now());
        this.orderEventService.publishOrderEvents();
    }
}
