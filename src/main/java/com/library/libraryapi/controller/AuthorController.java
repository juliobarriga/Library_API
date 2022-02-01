package com.library.libraryapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class AuthorController {

    @GetMapping("/authors/")
    public String getAllAuthors(){
        return "calling getAllAuthors";
    }

    @PostMapping("/authors/")
    public String addNewAuthor(){
        return "calling addNewAuthor";
    }
}
