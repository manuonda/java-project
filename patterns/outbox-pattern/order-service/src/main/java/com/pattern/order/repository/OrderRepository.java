package com.pattern.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pattern.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

}
