package com.huucong.service.impl;

import com.huucong.model.Category;
import com.huucong.repository.CategoryRepository;
import com.huucong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

public class CategorySeviceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void remove(int id) {
        categoryRepository.delete(id);
    }
}
