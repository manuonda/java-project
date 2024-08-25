package com.pattern.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pattern.order.entity.Outbox;

public interface OrderOutboxRepository 
extends JpaRepository<Outbox,Long>{

}
