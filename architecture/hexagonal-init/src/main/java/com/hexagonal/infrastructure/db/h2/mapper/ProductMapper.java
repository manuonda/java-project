/**
 * This class is responsible for mapping between ProductEntity and Product objects
 * in a hexagonal architecture. It provides methods to convert from entity to domain model.
 */
package com.hexagonal.infrastructure.db.h2.mapper;

import com.hexagonal.common.ProductDto;
import com.hexagonal.domain.model.Product;
import com.hexagonal.infrastructure.db.h2.entity.ProductEntity;

public class ProductMapper {

    private ProductMapper() {
        // private constructor to hide the implicit public one
    }

    public static Product fromEntityToProduct(ProductEntity entity){
        return new Product(entity.getProductId(), entity.getName(), 
        entity.getDescription() , entity.getPrice(), entity.getCurrency());
    }

    public static ProductDto fromProducToProductDto(Product product){
        return new ProductDto(product.getProductId(), product.getName(), product.getDescription(), product.getPrice(), product.getCurrency());
    }
}
