package com.library.libraryapi.controller;

import com.library.libraryapi.exceptions.InformationNotFoundException;
import com.library.libraryapi.exceptions.NotAvailableException;
import com.library.libraryapi.model.Book;
import com.library.libraryapi.model.Loan;
import com.library.libraryapi.repository.BookRepository;
import com.library.libraryapi.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class LoanController {

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

    @GetMapping("/loans/")
    public List<Loan> getAllLoans(){
        List<Loan> loan = loanRepository.findAll();
        if(loan.isEmpty()){
            throw new InformationNotFoundException("No loans found on the database.");
        } else {
            return loan;
        }
    }

    @PostMapping("/loans/books/{bookId}")
    public Loan loanBook(@PathVariable(value = "bookId") Long bookId){
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
            return loanRepository.save(loanObject);
        }
    }

    @GetMapping("/loans/{loanId}")
    public String getLoanById(@PathVariable(value = "loanId") Long loanId){
        return "calling getLoanById" + loanId;
    }

    @GetMapping("/loans/users/{userId}")
    public String getLoansByUserId(@PathVariable(value = "userId") Long userId){
        return "calling getLoanByUserId" + userId;
    }

    @GetMapping("/loans/books/{bookId}")
    public String getLoansByBookId(@PathVariable(value = "bookId") Long bookId){
        return "calling getLoanByBookId" + bookId;
    }

    @PutMapping("/loans/{loanId}")
    public String updateLoan(@PathVariable(value = "loanId") Long loanId){
        return "calling updateLoan" + loanId;
    }

    @DeleteMapping("/loans/{loanId}")
    public String deleteLoan(@PathVariable(value = "loanId") Long loanId){
        return "calling deleteLoan" + loanId;
    }
}
