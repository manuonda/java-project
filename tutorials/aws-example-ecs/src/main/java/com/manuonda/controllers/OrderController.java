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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Order Controller
 */

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
@Tag(name="Order Controller Rest" , description = "Order Controller to allow creat orders and get items")
public class OrderController {

    private static final Logger  logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService service;

    public OrderController(OrderService orderService){
        service = orderService; 
    }


    @GetMapping
    @Operation(summary = "List all Orders")
    @ApiResponse(responseCode = "200", description = "Response status 200 OK")
    public ResponseEntity<List<OrderDTO>> findAll(){
        logger.info("Get All orders");
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findAll());
    }

    @PostMapping
    @Operation(summary = "Create Order")
    @ApiResponse(responseCode ="201", description = "Repsonse stauts 201 Created")
    public ResponseEntity<OrderDTO> add(@RequestBody OrderDTO dto) {
       logger.info("Add Order DTO");
       OrderDTO orderDTOSave = this.service.add(dto);
       return ResponseEntity.status(HttpStatus.CREATED).body(orderDTOSave);  
    }
    

    @PutMapping("{id}")
    @Operation(summary = "Update Order")
    @ApiResponse(responseCode="200", description = "Response status 200 OK")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        System.out.println("hola munod232323");
        return null;
    }

}