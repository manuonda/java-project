package com.springboot.blog.payload;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;

    private String description; 

    private Set<PostDTO> posts;
}
