package com.library.libraryapi.controller;

import com.library.libraryapi.exceptions.InformationNotFoundException;
import com.library.libraryapi.model.Review;
import com.library.libraryapi.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {

    private ReviewRepository reviewRepository;

    @Autowired
    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/reviews/")
    public List<Review> getAllReviews(){
        List<Review> review = reviewRepository.findAll();
        if(review.isEmpty()){
            throw new InformationNotFoundException("No reviews found on the database.");
        } else {
            return review;
        }
    }

    @GetMapping("/reviews/books/{bookId}")
    public String getAllReviewsByBook(@PathVariable(value = "bookId") Long bookId){
        return "calling getAllReviewsByBook()" + bookId;
    }

    @PostMapping("/reviews/books/{bookId}")
    public String addBookReview(@PathVariable(value = "bookId") Long bookId){
        return "calling addBookReview()" + bookId;
    }

    @GetMapping("/reviews/{reviewId}")
    public String getReviewById(@PathVariable(value = "reviewId") Long reviewId){
        return "calling getReviewById()" + reviewId;
    }

    @GetMapping("/reviews/users/{userId}")
    public String getAllReviewsByUser(@PathVariable(value = "userId") Long userId){
        return "calling getAllReviewsByUser" + userId;
    }

    @PutMapping("/reviews/{reviewId}")
    public String updateReview(@PathVariable(value = "reviewId") Long reviewId){
        return "calling updateReview()" + reviewId;
    }

    @DeleteMapping("/reviews/{reviewId}")
    public String deleteReview(@PathVariable(value = "reviewId") Long reviewId){
        return "calling deleteReview" + reviewId;
    }

}
