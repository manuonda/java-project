package com.example.k8.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.k8.entity.dto.OrderDto;
import com.example.k8.service.OrderService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {



    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("")
    public List<OrderDto> getAll() {
        return this.orderService.getAll();
    }


    @PostMapping("")
    public OrderDto saveOrder(@RequestBody OrderDto dto) {
       return this.orderService.addOrder(dto);
    }


    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable("id") Long id){
        return this.orderService.getById(id);
    }
    

}
