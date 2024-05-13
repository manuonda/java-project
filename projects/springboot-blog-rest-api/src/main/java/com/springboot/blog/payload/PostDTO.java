package com.springboot.blog.payload;


import java.util.Set;

import org.modelmapper.internal.bytebuddy.asm.Advice.SelfCallHandle;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    description = "PostDTO Model Information"
)
public class PostDTO {

    @Schema(
        description = "Id Post"
    )
    private Long id;
    
    @Schema(
        description = "Title of Post"
    )
    @NotEmpty(message = "No Empty Title")
    private String title;

    @Schema(
        description = "Content of Post"
    )
    @NotEmpty(message = "Not Empty Content")
    private String content;

    @NotEmpty(message="Not Empty Description")
    private String description;

    private Set<CommentDTO> comments;

    private CategoryDTO category;
    
}
