package com.library.libraryapi.controller;

import com.library.libraryapi.exceptions.InformationExistException;
import com.library.libraryapi.exceptions.InformationNotFoundException;
import com.library.libraryapi.model.Book;
import com.library.libraryapi.repository.BookRepository;
import com.library.libraryapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class BookController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books/")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
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
        if(book.isEmpty()){
            throw new InformationNotFoundException("Book with id: " + bookId + " doesn't exist.");
        } else {
            book.get().setTitle(bookObject.getTitle());
            book.get().setSummary(bookObject.getSummary());
            book.get().setGenre(bookObject.getGenre());
            book.get().setPages(bookObject.getPages());
            book.get().setLanguage(bookObject.getLanguage());
            book.get().setRating(bookObject.getRating());
            book.get().setIsAvailable(bookObject.getIsAvailable());
            book.get().setAuthor(bookObject.getAuthor());
            return bookRepository.save(book.get());
        }
    }

    @DeleteMapping("/books/{bookId}")
    public Book deleteBook(@PathVariable(value = "bookId") Long bookId){
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty()){
            throw new InformationNotFoundException("Book with id: " + bookId + " doesn't exist.");
        } else {
            bookRepository.deleteById(bookId);
            return book.get();
        }
    }
}
