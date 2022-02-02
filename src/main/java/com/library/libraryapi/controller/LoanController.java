package com.library.libraryapi.controller;

import com.library.libraryapi.model.Loan;
import com.library.libraryapi.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return loanService.deleteLoan(loanId);
    }
}
