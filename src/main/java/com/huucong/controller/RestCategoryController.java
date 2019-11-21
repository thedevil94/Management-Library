package com.huucong.controller;

import com.huucong.model.Category;
import com.huucong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class RestCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories/")
    public ResponseEntity<Iterable<Category>> listAllCategory(){
        Iterable<Category> categories = categoryService.findAll();
        if (categories == null){
            return new ResponseEntity<Iterable<Category>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Iterable<Category>>(categories, HttpStatus.OK);
    }

    @GetMapping(value = "/categories/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> getCategory(@PathVariable("id") int id){
        Category category = categoryService.findById(id);
        if (category == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/categories/")
    public ResponseEntity<Void> createCategory(@RequestBody Category category, UriComponentsBuilder ucBuider){
        categoryService.save(category);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuider.path("/categories/{id}").buildAndExpand(category.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") int id, @RequestBody Category category){
        Category currentCategory = categoryService.findById(id);
        if (currentCategory == null){
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        currentCategory.setCategoryName(category.getCategoryName());
        currentCategory.setDescription(category.getDescription());
        currentCategory.setId(category.getId());

        categoryService.save(currentCategory);
        return new ResponseEntity<Category>(currentCategory, HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") int id){
        Category category = categoryService.findById(id);

        if (category == null){
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        categoryService.remove(id);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }
}
