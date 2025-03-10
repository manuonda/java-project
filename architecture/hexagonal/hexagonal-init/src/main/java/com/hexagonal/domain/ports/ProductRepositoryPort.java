package com.hexagonal.domain.ports;

import com.hexagonal.domain.model.Product;

/*
 * Interfa Port Repository
 */
public interface ProductRepositoryPort {

    Product findProductByProductId(String productId);

}
