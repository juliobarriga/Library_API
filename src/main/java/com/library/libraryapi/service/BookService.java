package com.library.libraryapi.service;

import com.library.libraryapi.exceptions.InformationExistException;
import com.library.libraryapi.exceptions.InformationNotFoundException;
import com.library.libraryapi.model.Book;
import com.library.libraryapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        List<Book> book = bookRepository.findAll();
        if(book.isEmpty()){
            throw new InformationNotFoundException("No books found on the database.");
        } else {
            return book;
        }
    }

    public Book addNewBook(Book bookObject){
        Book book = bookRepository.findByTitleAndAuthor(bookObject.getTitle(), bookObject.getAuthor());
        if(book != null){
            throw new InformationExistException("Book '" + bookObject.getTitle() + "' by " + bookObject.getAuthor() + " already exists");
        } else {
            return bookRepository.save(bookObject);
        }
    }

    public Book getOneBook(Long bookId){
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty()){
            throw new InformationNotFoundException("Book with id: " + bookId + " doesn't exist.");
        } else {
            return book.get();
        }
    }

    public Book updateBook(Long bookId, Book bookObject){
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

    public Book deleteBook(Long bookId){
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty()){
            throw new InformationNotFoundException("Book with id: " + bookId + " doesn't exist.");
        } else {
            bookRepository.deleteById(bookId);
            return book.get();
        }
    }
}
