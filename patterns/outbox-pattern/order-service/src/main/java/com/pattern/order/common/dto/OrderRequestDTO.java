package com.pattern.order.common.dto;

import java.math.BigDecimal;

public record OrderRequestDTO(
     String name,
     String customerId,
     String productType,
     int quantity,
     BigDecimal price
) {

}
