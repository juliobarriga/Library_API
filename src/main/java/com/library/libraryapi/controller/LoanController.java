package com.library.libraryapi.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class LoanController {

    @GetMapping("/loans/")
    public String getAllLoans(){
        return "calling getAllLoans";
    }

    @PostMapping("/loans/")
    public String loanBook(){ return "calling loanBook"; }

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
