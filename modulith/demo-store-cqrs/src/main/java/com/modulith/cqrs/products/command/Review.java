package com.modulith.cqrs.products.command;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review implements Entity<Product, ReviewIdentifier>{
}
