package com.huucong.service;

import com.huucong.model.Book;
import com.huucong.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Page<Book> findAll(Pageable pageable);

    Book findById(int id);

    void save(Book book);

    void remove(int id);

    Page<Book> findAllByCategory(Category category, Pageable pageable);

    Page<Book> findAllByOrderByPriceAsc(Pageable pageable);
    Page<Book> findAllByOrderByPriceDesc(Pageable pageable);
}
