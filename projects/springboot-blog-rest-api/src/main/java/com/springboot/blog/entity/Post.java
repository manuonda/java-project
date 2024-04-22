package com.springboot.blog.entity;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "posts" , uniqueConstraints = {@UniqueConstraint(columnNames = "title")}
)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="content" , nullable = false)
    private String content;


    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL, orphanRemoval = true )
    Set<Comment> comments = new HashSet<>();


}
