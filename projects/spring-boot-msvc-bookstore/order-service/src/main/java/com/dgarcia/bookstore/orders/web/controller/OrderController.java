package com.dgarcia.bookstore.orders.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dgarcia.bookstore.orders.domain.dto.OrderDTO;
import com.dgarcia.bookstore.orders.domain.models.CreateOrderRequest;
import com.dgarcia.bookstore.orders.domain.models.CreateOrderResponse;
import com.dgarcia.bookstore.orders.domain.models.OrderSummary;
import com.dgarcia.bookstore.orders.exception.ResourceNotFound;
import com.dgarcia.bookstore.orders.service.OrderService;
import com.dgarcia.bookstore.orders.service.SecurityService;

@RestController
@RequestMapping("/api/v1/orders")
//@SecurityRequirement(name = "security_auth")
@Tag(name = "Order Controller Rest" , description = "Order Controller to allow create orders and get items")
class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final SecurityService securityService;

    OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation( summary = "Create Order" , description = "Create order for user determinated")
    @ApiResponse(responseCode = "201", description = "Response status 201 Created")
    public CreateOrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request) {
        String userName = securityService.getLoginUserName();
        log.info("Creating order for user: {}", userName);
        return orderService.createOrder(userName, request);
    }

    

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Orders" , description = "Get Order by Username")
    @ApiResponse(responseCode = "200", description = "Response Code 200 OK")
    List<OrderSummary> getOrders() {
        String userName = securityService.getLoginUserName();
        log.info("Fetching orders for user: {}", userName);
        return orderService.findOrders(userName);
    }

    @GetMapping(value = "/{orderNumber}")
    @Operation(summary = "Get Order", description = "Get By Order by number")
    @ApiResponse(responseCode = "200", description = "Response Code Status 200")
    public OrderDTO getOrder(@PathVariable(value = "orderNumber") String orderNumber) {
        log.info("Fetching order by id: {}", orderNumber);
        String userName = securityService.getLoginUserName();
        return orderService
                .findUserOrder(userName, orderNumber)
                .orElseThrow(() -> new ResourceNotFound("Not Found order Number :" + orderNumber));
    }
}