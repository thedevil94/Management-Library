package com.huucong.repository;

import com.huucong.model.Book;
import com.huucong.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
    Iterable<Book> findAllByCategory(Category department);

    Page<Book> findAllByCategory(Category department, Pageable pageable);

    Page<Book> findAllByOrderByPriceAsc(Pageable pageable);
    Page<Book> findAllByOrderByPriceDesc(Pageable pageable);
}
