package com.hexagonal.infrastructure.db.h2.adapter;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

import com.hexagonal.domain.model.Product;
import com.hexagonal.domain.ports.ProductRepositoryPort;
import com.hexagonal.infrastructure.db.h2.mapper.ProductMapper;
import com.hexagonal.infrastructure.db.h2.repository.ProductJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductRepositoryAdapter  implements ProductRepositoryPort{

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Product findProductByProductId(String productId) {
       return productJpaRepository.findProductByProductId(productId)
        .map(ProductMapper::fromEntityToProduct)
       .orElseThrow(() -> new NoSuchElementException("Not Found by id :"+ productId));
    }

}
