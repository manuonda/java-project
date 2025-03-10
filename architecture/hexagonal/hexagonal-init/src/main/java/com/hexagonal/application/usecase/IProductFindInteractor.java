package com.hexagonal.application.usecase;

import com.hexagonal.domain.model.Product;

public interface IProductFindInteractor {
  
    Product findProductByProductId(String id);
}
