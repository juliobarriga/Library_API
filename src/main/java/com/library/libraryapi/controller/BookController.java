package com.library.libraryapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
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
}
