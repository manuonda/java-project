package com.hexagonal.domain.dto.request;

public record TaskRequest(
    String name,
    String description,
    int timerRequiredToComplete
) {

}
