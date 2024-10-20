package com.springboot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
