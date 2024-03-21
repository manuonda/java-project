package com.testing.webflux.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="empleados")
public class Empleado {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;


}
