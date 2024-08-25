package com.pattern.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pattern.order.common.dto.OrderRequestDTO;
import com.pattern.order.entity.Order;
import com.pattern.order.entity.Outbox;
import com.pattern.order.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;



@RequestMapping("/api/v1/orders")
@RestController
@Tag(name="Order Controller", description = "Order Controller to allow operations cruds and other actions")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }


    @PostMapping
    @Operation( summary = "Order create", description ="Create order ")
    @ApiResponse( responseCode = "201", description = "Response status 201 Created")
    public ResponseEntity<?> postMethodName(@RequestBody OrderRequestDTO orderRequestDTO) {
        logger.info("Create order request dto {}", orderRequestDTO.toString());
        Order order = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/orders")
    @Operation(summary = "Get Orders", description = "Get all orders")
    @ApiResponse( responseCode = "200", description = "Response status 200")
    public List<Order> getAllOrders() {
        List<Order> orders = this.orderService.getAllOrder();
        return orders;
    }


    
    @GetMapping("/outbox")
    @Operation(summary = "Get Outbox", description = "Get all outbox")
    @ApiResponse( responseCode = "200", description = "Response status 200")
    public List<Outbox> getAllOutbox() {
        List<Outbox> outbox = this.orderService.getAllOutbox();
        return outbox;
    }

}
