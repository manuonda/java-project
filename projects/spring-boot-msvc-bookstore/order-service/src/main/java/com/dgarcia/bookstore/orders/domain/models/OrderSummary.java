package com.dgarcia.bookstore.orders.domain.models;

public record OrderSummary(String orderNumber, OrderStatus status) {}