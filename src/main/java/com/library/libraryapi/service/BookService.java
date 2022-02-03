package com.library.libraryapi.service;

import com.library.libraryapi.exceptions.ForbiddenException;
import com.library.libraryapi.exceptions.InformationExistException;
import com.library.libraryapi.exceptions.InformationNotFoundException;
import com.library.libraryapi.model.Book;
import com.library.libraryapi.repository.BookRepository;
import com.library.libraryapi.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getUser().getLibrarian()){
            Book book = bookRepository.findByTitleAndAuthor(bookObject.getTitle(), bookObject.getAuthor());
            if(book != null){
                throw new InformationExistException("Book '" + bookObject.getTitle() + "' by " + bookObject.getAuthor() + " already exists");
            } else {
                return bookRepository.save(bookObject);
            }
        } else {
            throw new ForbiddenException("Librarian account needed to add a new book.");
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
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getUser().getLibrarian()){
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
        } else {
            throw new ForbiddenException("Librarian account needed to update a book.");
        }

    }

    public Book deleteBook(Long bookId){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getUser().getLibrarian()){
            Optional<Book> book = bookRepository.findById(bookId);
            if(book.isEmpty()){
                throw new InformationNotFoundException("Book with id: " + bookId + " doesn't exist.");
            } else {
                bookRepository.deleteById(bookId);
                return book.get();
            }
        } else {
            throw new ForbiddenException("Librarian account needed to delete a book.");
        }

    }
}
