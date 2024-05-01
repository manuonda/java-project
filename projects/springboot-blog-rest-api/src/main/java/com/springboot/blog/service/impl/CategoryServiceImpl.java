package com.springboot.blog.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Category;
import com.springboot.blog.exception.EntityFoundException;
import com.springboot.blog.exception.GlobalExceptionHandler;
import com.springboot.blog.exception.ResourceNotFound;
import com.springboot.blog.payload.CategoryDTO;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository , ModelMapper modelMapper){
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;

    }


    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
       
        Optional<Category> findByName = this.categoryRepository.findByName(categoryDTO.getName());
        if(findByName.isPresent()){
            throw new EntityFoundException("Title exists in other category : " + categoryDTO.getName());
        }  
        Category category = this.modelMapper.map(categoryDTO, Category.class);
        Category categorySaved = this.categoryRepository.save(category);
        return this.modelMapper.map(categorySaved, CategoryDTO.class);
    }

    @Override
    public CategoryDTO getById(Long id) {
         Category category = this.categoryRepository.findById(id)
         .orElseThrow(()-> new EntityFoundException("Not Found Category by id :"+ id));

         return this.modelMapper.map(category , CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO dto, Long id) {
         Category category = this.categoryRepository.findById(id).orElseThrow(() ->  new ResourceNotFound("Not exist category by id : " + id));
         
         this.categoryRepository.findByName(dto.getName())
         .ifPresent(existCategory -> {
            if(!(existCategory instanceof Category && 
                 existCategory.getId().equals(id))) {
                   throw new EntityFoundException("Name exist in other category : " +dto.getName());
                 }
         });

         category.setName(dto.getName());
         category.setDescription(dto.getDescription());
         Category categorySaved = this.categoryRepository.save(category);

         return this.modelMapper.map(categorySaved, CategoryDTO.class);

    }

    @Override
    public void deleteById(Long id) {
         Category category = this.categoryRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFound("Category not exists : "+ id));
         this.categoryRepository.delete(category);
         
    }

    
}
