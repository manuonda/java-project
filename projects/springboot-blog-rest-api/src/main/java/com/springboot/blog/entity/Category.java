package com.springboot.blog.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="categorys")
@Entity
public class Category {

    private Long id;
    @NotEmpty(message = "Name is required")
    private String name;

    private String description;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts;
}
