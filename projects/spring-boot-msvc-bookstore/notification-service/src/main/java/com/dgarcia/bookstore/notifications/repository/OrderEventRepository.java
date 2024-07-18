package com.dgarcia.bookstore.notifications.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgarcia.bookstore.notifications.domain.entity.OrderEventEntity;

public interface OrderEventRepository extends JpaRepository<OrderEventEntity , Long> {
  
    boolean existsByEventId(String eventId);
}
