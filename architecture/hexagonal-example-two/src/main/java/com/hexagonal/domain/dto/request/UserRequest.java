package com.hexagonal.domain.dto.request;

public record UserRequest(
    String name,
    byte age,
    String country
) {

}
