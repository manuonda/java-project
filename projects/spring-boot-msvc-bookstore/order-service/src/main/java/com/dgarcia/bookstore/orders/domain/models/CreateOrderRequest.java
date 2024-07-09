package com.dgarcia.bookstore.orders.domain.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * Permite crear una order indicando los 
 * items , el customer and the direction
 */
public record CreateOrderRequest(
        @Valid @NotEmpty(message = "Items cannot be empty") Set<OrderItem> items,
        @Valid Customer customer,
        @Valid Address deliveryAddress) {}