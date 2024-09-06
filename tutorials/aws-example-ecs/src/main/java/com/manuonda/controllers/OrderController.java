package com.manuonda.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manuonda.domain.dto.OrderDTO;
import com.manuonda.service.OrderService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
public class OrderController {

    private static final Logger  logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService service;

    public OrderController(OrderService orderService){
        service = orderService; 
    }


    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll(){
        logger.info("Get All orders");
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findAll());
    }

    @PostMapping("path")
    public ResponseEntity<OrderDTO> add(@RequestBody OrderDTO dto) {
       logger.info("Add Order DTO");
       OrderDTO orderDTOSave = this.service.add(dto);
       return ResponseEntity.status(HttpStatus.CREATED).body(orderDTOSave);  
    }
    

    @PutMapping("{id}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        return null;
    }

