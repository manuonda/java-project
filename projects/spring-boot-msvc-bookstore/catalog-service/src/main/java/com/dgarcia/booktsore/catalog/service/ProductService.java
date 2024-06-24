package com.dgarcia.booktsore.catalog.service;

import java.util.List;

import com.dgarcia.booktsore.catalog.entity.domain.Product;
import com.dgarcia.booktsore.catalog.entity.dto.PagedResult;
import com.dgarcia.booktsore.catalog.entity.dto.ProductDTO;
import com.dgarcia.booktsore.catalog.entity.dto.ProductRecord;

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
    PagedResult<ProductRecord> findAll(int pageNo);


    /**
     * Delete Product
     * @param id
     */
    void delete(Long id);
}
