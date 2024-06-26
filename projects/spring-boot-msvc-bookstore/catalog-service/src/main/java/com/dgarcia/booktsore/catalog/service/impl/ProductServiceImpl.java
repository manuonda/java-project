package com.dgarcia.booktsore.catalog.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgarcia.booktsore.catalog.config.ApplicationProperties;
import com.dgarcia.booktsore.catalog.entity.domain.Product;
import com.dgarcia.booktsore.catalog.entity.dto.PagedResult;
import com.dgarcia.booktsore.catalog.entity.dto.ProductDTO;
import com.dgarcia.booktsore.catalog.entity.dto.ProductRecord;
import com.dgarcia.booktsore.catalog.exception.ResourceNotFound;
import com.dgarcia.booktsore.catalog.repository.ProductRepository;
import com.dgarcia.booktsore.catalog.service.ProductService;
import com.dgarcia.booktsore.catalog.service.mapper.ProductMapper;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final ApplicationProperties  properties;

   
    public ProductServiceImpl(ProductRepository productRepository,
       ApplicationProperties    properties ) {
        this.productRepository = productRepository;
        this.properties =    properties;
    }

    @Override
    public void delete(Long id) {
        
    }

    @Override
    public PagedResult<ProductRecord> findAll(int pageNo) {
        final Sort sort = Sort.by("name").ascending();
        pageNo = pageNo <= 1 ? 0: pageNo -1;
        Pageable pageable = PageRequest.of(pageNo, properties.pageSize(),sort);
        Page<ProductRecord> productsPage = this.productRepository
        .findAll(pageable)
        .map(ProductMapper::toProductRecord);

        return new PagedResult<>
        (productsPage.getContent(), 
        productsPage.getTotalElements(), 
        productsPage.getNumber(), 
        productsPage.getTotalPages(), 
        productsPage.isFirst(), 
        productsPage.isLast(), 
        productsPage.hasNext(),
        productsPage.hasPrevious()); 

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


    @Override
    public ProductRecord findByCode(String code) {
       Product  product = this.productRepository.findByCode(code)
       .orElseThrow(() -> new ResourceNotFound("Product Not Found"));
   
       ProductRecord productRecord = null;
       productRecord = ProductMapper.toProductRecord(product);
       return productRecord;
    }

}
