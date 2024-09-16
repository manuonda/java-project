package com.manuonda.service;

import java.util.List;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

import com.manuonda.domain.dto.OrderDTO;
import com.manuonda.domain.entity.Order;

public interface OrderService {
    public Order process(Order order);

    public OrderDTO add(OrderDTO dto);

    public List<OrderDTO> findAll();

    public OrderDTO findById(Long id);

}
