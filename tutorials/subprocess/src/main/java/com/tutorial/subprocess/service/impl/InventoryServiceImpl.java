package com.tutorial.subprocess.service.impl;

import org.springframework.stereotype.Service;

import com.tutorial.subprocess.entity.Inventory;
import com.tutorial.subprocess.repository.InventoryRepository;
import com.tutorial.subprocess.service.InventoryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{
    
    
    private final InventoryRepository inventoryRepository;

    @Override
    public Inventory getInventoryByProductId(Long productId) {
        log.info("Getting inventory for the productId {}", productId);
        addDelay();
        return inventoryRepository.findByProductId(productId);
    }

    private void addDelay() {
        try {
            // adding 2s delay
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
}
