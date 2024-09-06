package com.manuonda.domain.entity;

import com.manuonda.domain.models.Address;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
@Builder
public class Order {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    @NotEmpty(message = "Not Empty qty")
    private int qty;

    private double amount;

    @Embedded
    @AttributeOverrides(
        value = {
            @AttributeOverride(name = "addressLine1", column = @Column(name = "delivery_address_line1")),
            @AttributeOverride(name = "addressLine2", column = @Column(name = "delivery_address_line2")),
            @AttributeOverride(name = "city", column = @Column(name = "delivery_address_city")),
            @AttributeOverride(name = "state", column = @Column(name = "delivery_address_state")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "delivery_address_zip_code")),
            @AttributeOverride(name = "country", column = @Column(name = "delivery_address_country")),
        }
    )
    private Address deliveryAddress;


    
}
