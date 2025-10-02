package com.events.events_demo.controller;


import com.events.events_demo.services.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

      private final OrderService  orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public void placeOrder(@PathVariable("orderId") String orderId){
        this.orderService.createOrder(orderId);
    }
}
