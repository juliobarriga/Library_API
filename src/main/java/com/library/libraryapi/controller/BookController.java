package com.library.libraryapi.controller;

import com.library.libraryapi.exceptions.InformationExistException;
import com.library.libraryapi.exceptions.InformationNotFoundException;
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
    public List<Book> getAllBooks(){
        List<Book> book = bookRepository.findAll();
        if(book.isEmpty()){
            throw new InformationNotFoundException("No books found on the database.");
        } else {
            return book;
        }
    }

    @PostMapping("/books/")
    public Book addNewBook(@RequestBody Book bookObject){
        Book book = bookRepository.findByTitle(bookObject.getTitle());
        if(book != null){
            throw new InformationExistException("Book '" + bookObject.getTitle() + "' by " + bookObject.getAuthor() + " already exists");
        } else {
            return bookRepository.save(bookObject);
        }
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
