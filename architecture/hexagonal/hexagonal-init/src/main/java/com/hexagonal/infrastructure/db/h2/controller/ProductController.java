package com.hexagonal.infrastructure.db.h2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexagonal.application.usecase.IProductFindInteractor;
import com.hexagonal.common.ProductDto;
import com.hexagonal.domain.model.Product;
import com.hexagonal.infrastructure.db.h2.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final IProductFindInteractor iProductFindInteractor;

    @GetMapping("/{productId}")
    public ProductDto findByProductId(@PathVariable("productId") String productId) {
        Product product = this.iProductFindInteractor.findProductByProductId(productId);
        return ProductMapper.fromProducToProductDto(product);
    }
    
}
