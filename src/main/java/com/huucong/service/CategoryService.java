package com.huucong.service;

import com.huucong.model.Category;

public interface CategoryService {
    Iterable<Category> findAll();

    Category findById(int id);

    void save(Category category);

    void remove(int id);
}
