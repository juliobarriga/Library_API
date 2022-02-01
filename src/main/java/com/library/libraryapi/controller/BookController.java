package com.library.libraryapi.controller;

import com.library.libraryapi.model.Book;
import com.library.libraryapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class BookController {

    private BookRepository bookRepository;
    
    @Autowired
    public void setBookRepository(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books/")
    public String getAllBooks(){
        return "calling getAllBooks";
    }

    @PostMapping("/books/")
    public String addNewBook(){
        return "calling addNewBook";
    }

    @GetMapping("/books/{bookId}")
    public String getOneBook(@PathVariable(value = "bookId") Long bookId){
        return "calling getOneBook";
    }

    @PutMapping("/books/{bookId}")
    public String updateBook(@PathVariable(value = "bookId") Long bookId){
        return "calling updateBook";
    }

    @DeleteMapping("/books/{bookId}")
    public String deleteBook(@PathVariable(value = "bookId") Long bookId){
        return "calling deleteBook";
    }
}
