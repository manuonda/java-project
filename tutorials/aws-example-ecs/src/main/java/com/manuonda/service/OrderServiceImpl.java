package com.manuonda.service;

import java.util.List;

import org.springframework.boot.actuate.autoconfigure.health.HealthProperties.Status;
import org.springframework.stereotype.Service;

import com.manuonda.domain.dto.OrderDTO;
import com.manuonda.domain.entity.Order;
import com.manuonda.repository.OrderRepository;
import com.manuonda.domain.entity.OrderStatus;

@Service
public class OrderServiceImpl implements OrderService{


    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository oRepository){
        this.orderRepository = oRepository;
    }

    @Override
    public Order process(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'process'");
    }

    /*
     * Save
     */
    @Override
    public OrderDTO add(OrderDTO dto){
        Order order = toEntity(dto);
        Order orderSave = this.orderRepository.save(order);
        return this.toDTO(orderSave);
    }


    @Override
    public List<OrderDTO> findAll(){
        List<Order> orders = this.orderRepository.findAll();
        return orders.stream().map(this::toDTO).toList();
    }


    public static final Order toEntity(OrderDTO dto) {
        return new Order(
            dto.id(),
            OrderStatus.PENDING,
            dto.qty(),
            dto.mount(), 
            null
        );
    }

    public final OrderDTO toDTO(Order order){
        return new OrderDTO(
            order.getId(),
            order.getStatus().name(),
            order.getQty(),
            order.getAmount()
        );
    }



}
