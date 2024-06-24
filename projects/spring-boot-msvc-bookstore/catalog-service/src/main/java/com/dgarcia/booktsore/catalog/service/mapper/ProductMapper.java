package com.dgarcia.booktsore.catalog.service.mapper;

import com.dgarcia.booktsore.catalog.entity.domain.Product;
import com.dgarcia.booktsore.catalog.entity.dto.ProductRecord;

public class ProductMapper {

    public static ProductRecord toProductRecord(Product product) {
        return new ProductRecord(
                product.getId(),
                product.getCode(),
                product.getName(),
                product.getDescription(),
                product.getImageUrl(), product.getPrice());
    }

  
}
