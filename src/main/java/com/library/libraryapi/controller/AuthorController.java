package com.library.libraryapi.controller;

import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/authors/{authorId}")
    public String getOneAuthor(@PathVariable(value = "authorId") Long authorId){
        return "calling getOneAuthor";
    }
}
