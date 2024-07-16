package com.dgarcia.bookstore.notifications.domain.models;

public record OrderSummary(String orderNumber, OrderStatus status) {}