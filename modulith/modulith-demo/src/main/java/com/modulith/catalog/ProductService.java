package com.modulith.catalog;

import com.modulith.common.models.PagedResult;

import java.util.Optional;

public interface ProductService {
     PagedResult<Product> getProducts(int pageNo);

     Optional<Product> getByCode(String code);
}
