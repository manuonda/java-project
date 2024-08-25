package com.pattern.order.common.mapper;



import java.util.Date;

import org.springframework.stereotype.Component;

import com.pattern.order.common.dto.OrderRequestDTO;
import com.pattern.order.entity.Order;

@Component
public class OrderToEntityMapper {

   
    public Order map(OrderRequestDTO dto){
        return Order.builder()
                .name(dto.name())
                .customerId(dto.customerId())
                .productType(dto.productType())
                .quantity(dto.quantity())
                .price(dto.price())
                .orderDate(new Date())
                .build();
    }

}
