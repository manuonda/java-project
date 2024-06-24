package com.dgarcia.booktsore.catalog.entity.dto;

public record ProductRecord(
    Long id, String code, String name, String description,
    String imageUrl, Double price
) {

}
