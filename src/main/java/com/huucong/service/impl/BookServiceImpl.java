package com.huucong.service.impl;

import com.huucong.model.Book;
import com.huucong.model.Category;
import com.huucong.repository.BookRepository;
import com.huucong.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book findById(int id) {
        return bookRepository.findOne(id);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void remove(int id) {
        bookRepository.delete(id);
    }

    @Override
    public Page<Book> findAllByCategory(Category category, Pageable pageable) {
        return bookRepository.findAllByCategory(category,pageable);
    }

    @Override
    public Page<Book> findAllByOrderByPriceAsc(Pageable pageable) {
        return bookRepository.findAllByOrderByPriceAsc(pageable);
    }

    @Override
    public Page<Book> findAllByOrderByPriceDesc(Pageable pageable) {
        return bookRepository.findAllByOrderByPriceDesc(pageable);
    }

}
