package com.example.k8.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.k8.entity.domain.Order;
import com.example.k8.entity.dto.OrderDto;
import com.example.k8.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto addOrder(OrderDto dto){
        Order order = this.converToEntity(dto);
        this.orderRepository.save(order);
        return this.toDTO(order);
    }


    public List<OrderDto> getAll(){
        List<Order> list = this.orderRepository.findAll();
        return list.stream().map(OrderService::toDTO).toList();
    }
    

    public OrderDto getById(Long id) {
        Order order = this.orderRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Find by id not exists : " + id));

        OrderDto orderDto = this.toDTO(order);
        return orderDto;
        
    }

    static  Order converToEntity(com.example.k8.entity.dto.OrderDto dto){
        Order order = new Order();
        order.setName(dto.name());
        order.setPrice(dto.price());
        order.setQty(dto.qty());
        return order;
    }

    static  OrderDto toDTO(Order order){
        return new OrderDto(order.getId(),  order.getName() , order.getQty(), order.getPrice());
    }
}
