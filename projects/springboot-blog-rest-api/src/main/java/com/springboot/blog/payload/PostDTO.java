package com.springboot.blog.payload;


import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;
    @NotEmpty(message = "No Empty Title")
    private String title;

    @NotEmpty(message = "Not Empty Content")
    private String content;

    @NotEmpty(message="Not Empty Description")
    private String description;

    private Set<CommentDTO> comments;

    private CategoryDTO category;
    
}
