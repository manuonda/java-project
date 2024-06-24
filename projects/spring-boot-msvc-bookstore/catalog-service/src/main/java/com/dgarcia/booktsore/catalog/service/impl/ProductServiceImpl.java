package com.dgarcia.booktsore.catalog.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgarcia.booktsore.catalog.entity.domain.Product;
import com.dgarcia.booktsore.catalog.entity.dto.PagedResult;
import com.dgarcia.booktsore.catalog.entity.dto.ProductDTO;
import com.dgarcia.booktsore.catalog.entity.dto.ProductRecord;
import com.dgarcia.booktsore.catalog.repository.ProductRepository;
import com.dgarcia.booktsore.catalog.service.ProductService;
import com.dgarcia.booktsore.catalog.service.mapper.ProductMapper;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    
   
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void delete(Long id) {
        
    }

    @Override
    public List<ProductDTO> findAll(int PageNo) {
        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(PageNo, 10,sort);
        final Page<ProductRecord> productsPage = this.productRepository.findAll(pageable)
        .map(ProductMapper::toProductRecord);

        PagedResult<ProductDT> pagedResult = new PagedResult<>
        (productsPage.getContent(), 
        productsPage.getTotalElements(), 
        productsPage.getNumber(), 
        productsPage.getTotalPages(), 
        productsPage.isFirst(), 
        productsPage.isLast(), 
        productsPage.hasNext(),
        productsPage.hasPrevious()); 

        return null;
    }


    @Override
    public ProductDTO save(ProductDTO dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProductDTO update(Long id, ProductDTO dto) {
        // TODO Auto-generated method stub
        return null;
    }

}
