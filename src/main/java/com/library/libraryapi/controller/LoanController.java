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
}
