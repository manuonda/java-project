package com.tutorial.subprocess.service;

import com.tutorial.subprocess.entity.Inventory;

public interface InventoryService {
  
     Inventory getInventoryByProductId(Long productId);
}
