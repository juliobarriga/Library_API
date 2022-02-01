package com.library.libraryapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class LoanController {

    @GetMapping("/loans/")
    public String getAllLoans(){
        return "calling getAllLoans";
    }

    @PostMapping("/loans/")
    public String loanBook(){ return "calling loanBook"; }
}
