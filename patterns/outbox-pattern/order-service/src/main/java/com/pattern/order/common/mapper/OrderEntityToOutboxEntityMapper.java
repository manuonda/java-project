package com.pattern.order.common.mapper;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pattern.order.entity.Order;
import com.pattern.order.entity.Outbox;

import lombok.SneakyThrows;

@Component
public class OrderEntityToOutboxEntityMapper {


    @SneakyThrows
    public Outbox map(Order order){
        return 
        Outbox.builder()
         .aggregateId(order.getId().toString(0)) 
         .payload(new ObjectMapper().writeValueAsString(order))
         .createdAt(new Date())
         .processed(false)
         .build();
    }
}
