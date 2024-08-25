package com.pattern.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pattern.entity.Outbox;

public interface OutboxRepository extends JpaRepository<Outbox, Long>{
    
    List<Outbox> findByProcessed(Boolean processed);
}
