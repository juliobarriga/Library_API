package com.library.libraryapi.controller;

import com.library.libraryapi.model.Review;
import com.library.libraryapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews/")
    public List<Review> getAllReviews(){
        return reviewService.getAllReviews();
    }

    @GetMapping("/reviews/books/{bookId}")
    public List<Review> getAllReviewsByBook(@PathVariable(value = "bookId") Long bookId){
        return reviewService.getAllReviewsByBook(bookId);
    }

    @PostMapping("/reviews/books/{bookId}")
    public Review addBookReview(@PathVariable(value = "bookId") Long bookId, @RequestBody Review reviewObject){
        return reviewService.addBookReview(bookId, reviewObject);
    }

    @GetMapping("/reviews/{reviewId}")
    public Review getReviewById(@PathVariable(value = "reviewId") Long reviewId){
        return reviewService.getReviewById(reviewId);
    }

    @GetMapping("/reviews/users/{userId}")
    public List<Review> getAllReviewsByUser(@PathVariable(value = "userId") Long userId){
        return reviewService.getAllReviewsByUser(userId);
    }

    @PutMapping("/reviews/{reviewId}")
    public Review updateReview(@PathVariable(value = "reviewId") Long reviewId, @RequestBody Review reviewObject){
        return reviewService.updateReview(reviewId, reviewObject);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public Review deleteReview(@PathVariable(value = "reviewId") Long reviewId){
        return reviewService.deleteReview(reviewId);
    }

}
