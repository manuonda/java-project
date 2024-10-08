package com.example.k8.ingress.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course{

    private Long courseId;
    private String name;
    private double price;
}
