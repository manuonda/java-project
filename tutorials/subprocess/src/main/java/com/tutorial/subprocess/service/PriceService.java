package com.tutorial.subprocess.service;

import com.tutorial.subprocess.entity.Price;

public interface PriceService {
    Price getPriceByProductId(Long productId);
}
