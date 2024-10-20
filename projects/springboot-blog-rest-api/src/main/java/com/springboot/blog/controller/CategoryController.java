package com.springboot.blog.controller;

import org.aspectj.weaver.ast.CallExpr;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.CategoryDTO;
import com.springboot.blog.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/v1/categories")
@Tag(
    name="Category Controller Rest",
    description = "Category Controller Rest API"
)
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService =categoryService;
    }


    @Operation(
        summary = "Create Category",
        description = "Create Category for Post with Role Admin"
    )
    @ApiResponse(
       responseCode="201",
       description = "Response status 201 Created"
    )
     @SecurityRequirement(
        name = "Bear Authentication"
    )
    //Build Add Category Rest Api 
    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> postAddCategory(@Valid @RequestBody CategoryDTO dto){
        CategoryDTO categoryDTO = this.categoryService.createCategory(dto);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }


    @Operation(
        summary = "Update Category",
        description = "Update Category validated data role ADMIN"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Response Status 200"
    )
    @SecurityRequirement(
        name = "Bear Authentication"
    )
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> putMethodName(@PathVariable("id")Long id, @Valid @RequestBody CategoryDTO dto) {
        CategoryDTO updateDTO = this.categoryService.updateCategory(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(updateDTO);
    }


    @Operation(
        summary = "Delete Category",
        description = "Delete Category with ROLE Admin"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Response Status 200 OK"
    )
    @SecurityRequirement(
        name = "Bear Authentication"
    )
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id){
        this.categoryService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }


    @Operation(
        summary = "Get Category",
        description = "Get Category by Id"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Response Status 200 Delete Category"
    )
    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.getById(id));
    }

    

}
