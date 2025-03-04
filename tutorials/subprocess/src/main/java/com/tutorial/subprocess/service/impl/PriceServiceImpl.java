package com.tutorial.subprocess.service.impl;

import org.springframework.stereotype.Service;

import com.tutorial.subprocess.entity.Price;
import com.tutorial.subprocess.repository.PriceRepository;
import com.tutorial.subprocess.service.PriceService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class PriceServiceImpl implements PriceService{

     private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getPriceByProductId(Long productId) {
        log.info("Getting price for the productId {}", productId);
        addDelay();
        return priceRepository.findByProductId(productId);
    }

    private void addDelay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
