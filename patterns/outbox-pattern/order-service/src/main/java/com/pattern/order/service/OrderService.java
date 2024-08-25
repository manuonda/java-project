package com.pattern.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pattern.order.common.dto.OrderRequestDTO;
import com.pattern.order.common.mapper.OrderEntityToOutboxEntityMapper;
import com.pattern.order.common.mapper.OrderToEntityMapper;
import com.pattern.order.entity.Order;
import com.pattern.order.entity.Outbox;
import com.pattern.order.repository.OrderOutboxRepository;
import com.pattern.order.repository.OrderRepository;
import java.util.List;
import jakarta.transaction.Transactional;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderToEntityMapper toEntityMapper;
    
    private final OrderRepository orderRepository;

    private final OrderEntityToOutboxEntityMapper toOutboxMapper;
    private final OrderOutboxRepository orderOutboxRepository;


    public OrderService(OrderToEntityMapper toEntityMapper,
    OrderRepository orderRepository,
    OrderEntityToOutboxEntityMapper toOutboxMapper,
    OrderOutboxRepository orderOutboxRepository
    ){
        this.toEntityMapper = toEntityMapper;
        this.orderRepository = orderRepository;
        this.toOutboxMapper = toOutboxMapper;
        this.orderOutboxRepository = orderOutboxRepository;
    }


    @Transactional
    public Order createOrder(OrderRequestDTO orderRequestDTO){
        logger.info("Create Order request {}", orderRequestDTO.toString());
        Order order = new Order();
        order = toEntityMapper.map(orderRequestDTO);
        this.orderRepository.save(order);

        Outbox outbox = toOutboxMapper.map(order);
        this.orderOutboxRepository.save(outbox);         
        return order;
    }

    public List<Order> getAllOrder(){
        return this.orderRepository.findAll();
    }

    public List<Outbox> getAllOutbox(){
        return this.orderOutboxRepository.findAll();
    }
}
