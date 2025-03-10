package com.hexagonal.application.usecase;

import org.springframework.stereotype.Component;

import com.hexagonal.domain.model.Product;
import com.hexagonal.domain.ports.ProductRepositoryPort;

import lombok.RequiredArgsConstructor;

/*
 * Interactor/UseCase
 * Interactor is sinonymo de use case
 */

@RequiredArgsConstructor
@Component
public class ProductFindInteractor  implements IProductFindInteractor{

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Product findProductByProductId(String productId) {
         return this.productRepositoryPort.findProductByProductId(productId);
        
    }

  
}
