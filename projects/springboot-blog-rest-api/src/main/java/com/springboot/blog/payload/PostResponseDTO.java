package com.springboot.blog.payload;

import java.util.List;

public record PostResponseDTO(
    List<PostDTO> content,
    int pageNo,
    int pageSize,
    long totalElements,
    int totalPages,
    boolean last
) {

}
