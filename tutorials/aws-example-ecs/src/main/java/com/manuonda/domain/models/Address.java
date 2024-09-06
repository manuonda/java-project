package com.manuonda.domain.models;

import jakarta.validation.constraints.NotBlank;

public record Address(
    @NotBlank(message = "Address Line 1 is required")
    String addresLine1,
    @NotBlank(message = "City is required")
    String city,
    @NotBlank(message = "States is required")
    String state,
    @NotBlank(message = "ZipCode is required")
    String zipCode,
    @NotBlank(message = "Count is required")
    String country
 ){
}
