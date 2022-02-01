package com.library.libraryapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class BookController {

//    @GetMapping("/hello-world/")
//    public String getHelloWorld(){
//        return "Hello World";
//    }
    @GetMapping("/books/")
    public String getAllBooks(){
        return "calling getAllBooks";
    }

    @PostMapping("/books/")
    public String addNewBook(){
        return "calling addNewBook";
    }
}
