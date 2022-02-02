package com.library.libraryapi.controller;

import com.library.libraryapi.exceptions.InformationNotFoundException;
import com.library.libraryapi.exceptions.NotAvailableException;
import com.library.libraryapi.model.Book;
import com.library.libraryapi.model.Loan;
import com.library.libraryapi.repository.BookRepository;
import com.library.libraryapi.repository.LoanRepository;
import com.library.libraryapi.service.LoanService;
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

    private LoanService loanService;

    @Autowired
    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/loans/")
    public List<Loan> getAllLoans(){
        return loanService.getAllLoans();
    }

    @PostMapping("/loans/books/{bookId}")
    public Loan loanBook(@PathVariable(value = "bookId") Long bookId){
        return loanService.loanBook(bookId);
    }

    @GetMapping("/loans/{loanId}")
    public Loan getLoanById(@PathVariable(value = "loanId") Long loanId){
        return loanService.getLoanById(loanId);
    }

    @GetMapping("/loans/users/{userId}")
    public List<Loan> getLoansByUserId(@PathVariable(value = "userId") Long userId){
        return loanService.getLoansByUserId(userId);
    }

    @GetMapping("/loans/books/{bookId}")
    public List<Loan> getLoansByBookId(@PathVariable(value = "bookId") Long bookId){
        return loanService.getLoansByBookId(bookId);
    }

    @PutMapping("/loans/{loanId}")
    public Loan updateLoan(@PathVariable(value = "loanId") Long loanId, @RequestBody Loan loanObject){
        return loanService.updateLoan(loanId, loanObject);
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
