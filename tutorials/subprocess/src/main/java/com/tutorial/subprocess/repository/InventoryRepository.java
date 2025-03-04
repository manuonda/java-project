package com.tutorial.subprocess.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.subprocess.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findByProductId(Long productId);

}
