package com.tutorial.subprocess.service.impl;

import org.springframework.stereotype.Service;

import com.tutorial.subprocess.entity.Product;
import com.tutorial.subprocess.repository.ProductRepository;
import com.tutorial.subprocess.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Long id) {
        log.info("Service request to fetch product by id: {}", id);
        addDelay();
        return productRepository.findById(id).orElse(null);
    }

    private void addDelay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}