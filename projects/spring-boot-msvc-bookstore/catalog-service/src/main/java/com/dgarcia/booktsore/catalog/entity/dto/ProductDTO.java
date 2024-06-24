package com.dgarcia.booktsore.catalog.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    Long id;
    String code;
    String name;
    String description;
    String imageUrl;
    Double price;

}
