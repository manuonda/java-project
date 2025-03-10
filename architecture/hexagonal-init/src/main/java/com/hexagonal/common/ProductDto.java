package com.hexagonal.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@EqualsAndHashCode
@Getter
@Setter
public class ProductDto {

    private String productId;
    private String name;
    private String description;
    private String price;

    public ProductDto(String productId, String name, String description, BigDecimal price, String currency) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = PriceFormatter.formatPrice(price, currency);
    }

}
