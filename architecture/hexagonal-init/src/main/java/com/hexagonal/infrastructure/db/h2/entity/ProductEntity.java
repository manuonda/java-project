package com.hexagonal.infrastructure.db.h2.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String productId;
    private String name;
    private String description;
    private BigDecimal price;
    private String currency;

    public ProductEntity(String productId, String name, String description, BigDecimal price, String currency) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.currency = currency;
    }

}
