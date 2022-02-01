package com.library.libraryapi.controller;

import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/books/{bookId}")
    public String getOneBook(@PathVariable(value = "bookId") Long bookId){
        return "calling getOneBook";
    }

    @PutMapping("/books/{bookId}")
    public String updateBook(@PathVariable(value = "bookId") Long bookId){
        return "calling updateBook";
    }
}
