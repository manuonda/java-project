package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.payload.CategoryDTO;

public interface CategoryService {

    /**
     * Create Category
     * @param categoryDTO
     * @return
     */
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    /**
     * Get By ID
     * @param id
     * @return
     */
    CategoryDTO getById(Long id);

    /**
     * List by Page
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return
     */
    List<CategoryDTO> getAll(int pageNo, int pageSize,String sortBy, String sortDir );

    /**
     * Update Category 
     * @param dto
     * @param id
     * @return
     */
    CategoryDTO updateCategory(CategoryDTO dto, Long id);

    /**
     * Delete Category
     * @param id
     */
    void deleteById(Long id);

}
