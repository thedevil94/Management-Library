package com.huucong.controller;

import com.huucong.model.Book;
import com.huucong.model.Category;
import com.huucong.service.BookService;
import com.huucong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories () {
        return categoryService.findAll();
    }

    @GetMapping("/book")
    public ModelAndView listBooks(@PageableDefault(size = 4) Pageable pageable){
        Page<Book> books = bookService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/book/list");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @GetMapping("/create-book")
    public ModelAndView createFormBook(){
        ModelAndView modelAndView = new ModelAndView("/book/create");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    @PostMapping("/create-book")
    public ModelAndView crateNewBook(@ModelAttribute("book") Book book){
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("redirect:/book");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    @GetMapping("/edit-book/{id}")
    public ModelAndView editFormBook(@PathVariable int id){
        Book book = bookService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/book/edit");
        modelAndView.addObject("book", book);
        return modelAndView;
    }
    @PostMapping("/edit-book")
    public ModelAndView editEmployee(@ModelAttribute("book") Book book){
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("redirect:/book");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @GetMapping("/delete-book/{id}")
    public String deleteEmployee(@PathVariable int id){
        bookService.remove(id);
        return "redirect:/book";
    }

    @GetMapping("/searchByCategory")
    public ModelAndView getBookByCategory(@RequestParam("srch") String search,@PageableDefault(size = 4) Pageable pageable){
        Category category = categoryService.findById(Integer.parseInt(search));
        Page<Book> books = bookService.findAllByCategory(category,pageable);
        ModelAndView modelAndView = new ModelAndView("/book/list");
        modelAndView.addObject("books",books);
        modelAndView.addObject("srch",search);
        return modelAndView;
    }

    @GetMapping("/sortByPriceAsc")
    public ModelAndView getBookSortByPriceAsc(@PageableDefault(size = 4) Pageable pageable){
        Page<Book> books = bookService.findAllByOrderByPriceAsc(pageable);
        ModelAndView modelAndView = new ModelAndView("/book/list");
        modelAndView.addObject("books",books);
        return modelAndView;
    }

    @GetMapping("/descByPriceDesc")
    public ModelAndView getBookSortByPriceDesc(@PageableDefault(size = 4) Pageable pageable){
        Page<Book> books = bookService.findAllByOrderByPriceDesc(pageable);
        ModelAndView modelAndView = new ModelAndView("/book/list");
        modelAndView.addObject("books",books);
        return modelAndView;
    }
}
