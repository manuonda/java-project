package com.dgarcia.bookstore.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgarcia.bookstore.orders.domain.entity.OrderEventEntity;

public interface OrderEventRepository extends JpaRepository<OrderEventEntity, Long> {}