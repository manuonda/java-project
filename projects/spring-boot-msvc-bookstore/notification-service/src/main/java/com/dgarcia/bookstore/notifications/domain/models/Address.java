package com.dgarcia.bookstore.notifications.domain.models;

import jakarta.validation.constraints.NotBlank;

public record Address(
    @NotBlank(message = "AdddressLine1 is required") String addressLine1,
    String addressLine2,
    @NotBlank(message = "City is required")String city,
    @NotBlank(message = "State is required")String state,
    @NotBlank(message = "ZipCod is required")String zipCode,
    @NotBlank(message = "Country is required")String country 
) {}
