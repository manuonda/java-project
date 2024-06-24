package com.dgarcia.booktsore.catalog.service;

import java.util.List;

import com.dgarcia.booktsore.catalog.entity.domain.Product;
import com.dgarcia.booktsore.catalog.entity.dto.ProductDTO;

public interface ProductService {

    /*
     * Save Product
     */
    ProductDTO save(ProductDTO dto);

    /**
     * Update Product
     */
    ProductDTO update(Long id, ProductDTO dto);

    /**
     * Find All
     */
    List<ProductDTO> findAll(int pageNo);


    /**
     * Delete Product
     * @param id
     */
    void delete(Long id);
}
