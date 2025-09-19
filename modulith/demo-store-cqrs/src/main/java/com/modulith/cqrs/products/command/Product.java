package com.modulith.cqrs.products.command;


import lombok.Getter;
import lombok.Setter;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class Product implements AggregateRoot<Product, Product.ProductIdentifier>{

        private ProductIdentifier id;
        private String name;
        private String description;
        private String category;
        private BigDecimal price;
        private List<Review> productReviews = new ArrayList<>(  );

       public record ProductIdentifier(UUID id) implements Identifier {
           public ProductIdentifier {
               if (id == null) {
                   throw new IllegalArgumentException("Product identifier cannot be null");
               }
           }
       }


}

