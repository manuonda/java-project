package com.manuonda.service;

import com.manuonda.domain.entity.Order;

public interface OrderProcessingState {
    public Order process(Order order);

}
