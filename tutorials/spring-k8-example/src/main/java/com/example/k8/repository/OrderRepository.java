package com.example.k8.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.k8.entity.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
