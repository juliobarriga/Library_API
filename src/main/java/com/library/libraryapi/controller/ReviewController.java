package com.library.libraryapi.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class ReviewController {

    @GetMapping("/reviews/")
    public String getAllReviews(){
        return "calling getAllReviews";
    }

    @GetMapping("/reviews/books/{bookId}")
    public String getAllReviewsByBook(@PathVariable(value = "bookId") Long bookId){
        return "calling getAllReviewsByBook()" + bookId;
    }

    @PostMapping("/reviews/books/{bookId}")
    public String addBookReview(@PathVariable(value = "bookId") Long bookId){
        return "calling addBookReview()" + bookId;
    }

}
