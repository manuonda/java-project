package com.springboot.blog.controller;

import org.aspectj.weaver.ast.CallExpr;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.CategoryDTO;
import com.springboot.blog.service.CategoryService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService =categoryService;
    }

    //Build Add Category Rest Api 
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> postAddCategory(@Valid @RequestBody CategoryDTO dto){
        CategoryDTO categoryDTO = this.categoryService.createCategory(dto);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> putMethodName(@PathVariable("id")Long id, @Valid @RequestBody CategoryDTO dto) {
        CategoryDTO updateDTO = this.categoryService.updateCategory(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(updateDTO);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(@PathVariable("id") Long id){
        this.categoryService.deleteById(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.getById(id));
    }

    

}
