package com.dgarcia.booktsore.catalog.entity.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    @SequenceGenerator(name="product_id_generator" , sequenceName = "product_id_seq")
    private Long id;
    

    @Column( nullable = false, unique = true)
    @NotEmpty(message = "Product Code is required")
    private String code;
    
    @Column(name="name", nullable = false)
    @NotEmpty(message = "Required name is required")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="image_url")
    private String imageUrl;

    @Column(nullable = false)
    @NotEmpty(message = "Product price is required")
    @DecimalMin("0.1")
    private Double price;


}
