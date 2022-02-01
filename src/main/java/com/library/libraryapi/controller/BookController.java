package com.library.libraryapi.controller;

import com.library.libraryapi.exceptions.InformationExistException;
import com.library.libraryapi.exceptions.InformationNotFoundException;
import com.library.libraryapi.model.Book;
import com.library.libraryapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Book book = bookRepository.findByTitleAndAuthor(bookObject.getTitle(), bookObject.getAuthor());
        if(book != null){
            throw new InformationExistException("Book '" + bookObject.getTitle() + "' by " + bookObject.getAuthor() + " already exists");
        } else {
            return bookRepository.save(bookObject);
        }
    }

    @GetMapping("/books/{bookId}")
    public Book getOneBook(@PathVariable(value = "bookId") Long bookId){
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty()){
            throw new InformationNotFoundException("Book with id: " + bookId + " doesn't exist.");
        } else {
            return book.get();
        }
    }

    @PutMapping("/books/{bookId}")
    public Book updateBook(@PathVariable(value = "bookId") Long bookId, @RequestBody Book bookObject){
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            throw new InformationNotFoundException("Book with id: " + bookId + " doesn't exist.");
        } else {
            book.get().setTitle(bookObject.getTitle());
            book.get().setSummary(bookObject.getSummary());
            book.get().setGenre(bookObject.getGenre());
            book.get().setPages(bookObject.getPages());
            book.get().setLanguage(bookObject.getLanguage());
            book.get().setRating(bookObject.getRating());
            book.get().setAvailable(bookObject.getAvailable());
            book.get().setAuthor(bookObject.getAuthor());
            return bookRepository.save(book.get());
        }
    }

    @DeleteMapping("/books/{bookId}")
    public String deleteBook(@PathVariable(value = "bookId") Long bookId){
        return "calling deleteBook";
    }
}
