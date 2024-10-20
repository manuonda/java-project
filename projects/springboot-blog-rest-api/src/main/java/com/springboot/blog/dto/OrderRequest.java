package com.springboot.blog.dto;

import com.springboot.blog.entity.Payment;

import lombok.Getter;
import lombok.Setter;

import com.springboot.blog.entity.Order;


@Getter
@Setter
public class OrderRequest{
    private Order Order;
    private Payment payment;

}
