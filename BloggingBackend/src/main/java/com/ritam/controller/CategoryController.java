package com.ritam.controller;

import com.ritam.payload.ApiResponse;
import com.ritam.payload.CategoryDto;
import com.ritam.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //CREATE category
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid@RequestBody CategoryDto categoryDto)
    {
       CategoryDto createDTO = this.categoryService.createCategory(categoryDto);
       return new  ResponseEntity<CategoryDto>(createDTO, HttpStatus.CREATED);
    }
    //UPDATE Category
    @PutMapping("/{category_id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable(value = "category_id") Integer Cid)
    {
       CategoryDto update = this.categoryService.updateCategory(categoryDto,Cid);
       return ResponseEntity.ok(update);
    }
    //DELETE Category
    @DeleteMapping("/{category_id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("category_id") Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);
    }

    //READ all Category
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory()
    {
        return ResponseEntity.ok(this.categoryService.getAllCategory());
    }
    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable(value = "category_id") Integer categoryId)
    {
        return ResponseEntity.ok(this.categoryService.getSingleCategory(categoryId));
    }

}
