package com.tutorial.subprocess.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.subprocess.dto.ProductDetailDTO;
import com.tutorial.subprocess.facade.ProductAsyncFacade;
import com.tutorial.subprocess.facade.ProductSyncFacade;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductSyncFacade productSyncFacade;

    private final ProductAsyncFacade productASyncFacade;



    @GetMapping("/{id}/sync")
    public ResponseEntity<ProductDetailDTO> getProductSync(@PathVariable Long id) {
        log.info("Rest request to get product by id sync: {}", id);
        return ResponseEntity.ok(productSyncFacade.getProductDetails(id));
    }

    @GetMapping("/{id}/async")
    public ResponseEntity<ProductDetailDTO> getProductAsync(@PathVariable Long id) {
        log.info("Rest request to get product by id async: {}", id);
        return ResponseEntity.ok(productASyncFacade.getProductDetails(id));
    }


}
