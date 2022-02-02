package com.library.libraryapi.service;

import com.library.libraryapi.exceptions.InformationNotFoundException;
import com.library.libraryapi.exceptions.NotAvailableException;
import com.library.libraryapi.model.Book;
import com.library.libraryapi.model.Loan;
import com.library.libraryapi.repository.BookRepository;
import com.library.libraryapi.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private LoanRepository loanRepository;

    @Autowired
    public void setLoanRepository(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Loan> getAllLoans(){
        List<Loan> loan = loanRepository.findAll();
        if(loan.isEmpty()){
            throw new InformationNotFoundException("No loans found on the database.");
        } else {
            return loan;
        }
    }

    public Loan loanBook(Long bookId){
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty()){
            throw new InformationNotFoundException("Book with id " + bookId + " not found.");
        } else if (book.get().getIsAvailable() == false) {
            throw new NotAvailableException("Book with id " + bookId + " is not available.");
        } else {
            Loan loanObject = new Loan();
            loanObject.setBook(book.get());
//            loanObject.setUser(); Sets user
            loanObject.setBorrowDate(LocalDate.now());
            loanObject.setExpirationDate(loanObject.getBorrowDate().plus(3, ChronoUnit.WEEKS));
            book.get().setIsAvailable(false);
            return loanRepository.save(loanObject);
        }
    }

    public Loan getLoanById(@PathVariable(value = "loanId") Long loanId){
        Optional<Loan> loan = loanRepository.findById(loanId);
        if(loan.isEmpty()){
            throw new InformationNotFoundException("Loan with id " + loanId + " not found.");
        } else {
            return loan.get();
        }
    }

    @GetMapping("/loans/users/{userId}")
    public List<Loan> getLoansByUserId(@PathVariable(value = "userId") Long userId){
        List<Loan> loan = loanRepository.findByUserId(userId);
        if(loan.isEmpty()){
            throw new InformationNotFoundException("No loans found for user id: " + userId);
        } else {
            return loan;
        }
    }

    @GetMapping("/loans/books/{bookId}")
    public List<Loan> getLoansByBookId(@PathVariable(value = "bookId") Long bookId){
        List<Loan> loan = loanRepository.findByBookId(bookId);
        if(loan.isEmpty()){
            throw new InformationNotFoundException("No loans found for user id: " + bookId);
        } else {
            return loan;
        }
    }

    @PutMapping("/loans/{loanId}")
    public Loan updateLoan(@PathVariable(value = "loanId") Long loanId, @RequestBody Loan loanObject){
        Optional<Loan> loan = loanRepository.findById(loanId);
        if(loan.isEmpty()){
            throw new InformationNotFoundException("Loan with id: " + loanId + " not found.");
        } else {
            loan.get().setBorrowDate(loanObject.getBorrowDate());
            loan.get().setExpirationDate(loanObject.getExpirationDate());
            loan.get().setReturnDate(loanObject.getReturnDate());
            return loanRepository.save(loan.get());
        }
    }

    @DeleteMapping("/loans/{loanId}")
    public Loan deleteLoan(@PathVariable(value = "loanId") Long loanId){
        Optional<Loan> loan = loanRepository.findById(loanId);
        if(loan.isEmpty()){
            throw new InformationNotFoundException("Book with id: " + loanId + "not found.");
        } else {
            loanRepository.deleteById(loanId);
            return loan.get();
        }
    }
}
