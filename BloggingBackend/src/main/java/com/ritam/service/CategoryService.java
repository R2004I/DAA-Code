package com.ritam.service;

import com.ritam.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    void deleteCategory(Integer categoryId);

    List<CategoryDto> getAllCategory();

    CategoryDto getSingleCategory(Integer categoryId);
}
