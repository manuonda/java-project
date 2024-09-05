package com.manuonda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manuonda.domain.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
