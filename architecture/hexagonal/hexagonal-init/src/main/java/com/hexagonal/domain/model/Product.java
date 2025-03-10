package com.hexagonal.domain.model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
@AllArgsConstructor
public class Product {
    private String productId;
    private String name;
    private String description;
    // NOTE: these validations are not triggered on this example,
    // for using them you should have a method with a parameter
    // annotated with @Valid like: saveProduct(@Valid Product product)
    // They are left here for example reference (if we had a save() method, that would be a good place to put the @Valid)
    @NotNull
    @Min(value = 0, message = "Price must be positive")
    private BigDecimal price;
    @NotNull
    @Pattern(regexp = "[$€]", message = "Currency must be either '$' or '€'")
    private String currency;
}
