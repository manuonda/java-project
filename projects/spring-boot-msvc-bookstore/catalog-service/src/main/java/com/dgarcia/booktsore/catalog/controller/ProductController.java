package com.dgarcia.booktsore.catalog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgarcia.booktsore.catalog.entity.dto.PagedResult;
import com.dgarcia.booktsore.catalog.entity.dto.ProductRecord;
import com.dgarcia.booktsore.catalog.service.ProductService;

import io.micrometer.core.ipc.http.HttpSender.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<PagedResult> getMethodName(@RequestParam("page") int pageNo ) {
        PagedResult result = this.service.findAll(0);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    

    @GetMapping("/code/{code}")
    public ResponseEntity<ProductRecord> getByCode(@PathVariable("code") String param) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findByCode(param));
    }
    

}
