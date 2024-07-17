package com.dgarcia.bookstore.orders.jobs;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dgarcia.bookstore.orders.service.OrderService;

@Component
public class OrderProcessingJob {

    private static final Logger logger = LoggerFactory.getLogger(OrderProcessingJob.class);


    private final OrderService orderService;

    public OrderProcessingJob(OrderService orderService){
        this.orderService = orderService;
    }

    /**
     * Job que permite correr cada 10 segundos 
     * para obtener la informacion y procesarla guardando aquellos 
     * ordens que estan esta estado nuevo 
     */
    @Scheduled(cron ="${orders.new-orders-job-cron}")
    public void processNewOrders(){
        logger.info("Processing new orders at {}", Instant.now());
        this.orderService.processNewOrders();
    }

}
