package com.tutorial.subprocess.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.subprocess.entity.Price;

public interface PriceRepository extends JpaRepository<Price, Long>{

    Price findByProductId(Long productId);

}
