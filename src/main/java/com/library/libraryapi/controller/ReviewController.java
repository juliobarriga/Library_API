package com.library.libraryapi.controller;

import com.library.libraryapi.exceptions.IncompleteInformationException;
import com.library.libraryapi.exceptions.InformationNotFoundException;
import com.library.libraryapi.model.Review;
import com.library.libraryapi.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<Review> getAllReviewsByBook(@PathVariable(value = "bookId") Long bookId){
        List<Review> review = reviewRepository.findByBookId(bookId);
        if(review.isEmpty()){
            throw new InformationNotFoundException("No reviews found for book with id: " + bookId);
        } else {
            return review;
        }
    }

    @PostMapping("/reviews/books/{bookId}")
    public Review addBookReview(@PathVariable(value = "bookId") Long bookId, @RequestBody Review reviewObject){
//        Review review = reviewRepository.findByBookIdAndUserId();
        if(reviewObject.getRating() == null){
            throw new IncompleteInformationException("Review is missing rating.");
        } else {
            return reviewRepository.save(reviewObject);
        }
    }

    @GetMapping("/reviews/{reviewId}")
    public Review getReviewById(@PathVariable(value = "reviewId") Long reviewId){
        Optional<Review> review = reviewRepository.findById(reviewId);
        if(review.isEmpty()){
            throw new InformationNotFoundException("Review with id " + reviewId + " not found.");
        } else {
            return review.get();
        }
    }

    @GetMapping("/reviews/users/{userId}")
    public List<Review> getAllReviewsByUser(@PathVariable(value = "userId") Long userId){
        List<Review> review = reviewRepository.findByUserId(userId);
        if(review.isEmpty()){
            throw new InformationNotFoundException("No reviews found made by user with id: " + userId);
        } else {
            return review;
        }
    }

    @PutMapping("/reviews/{reviewId}")
    public Review updateReview(@PathVariable(value = "reviewId") Long reviewId, @RequestBody Review reviewObject){
        Optional<Review> review = reviewRepository.findById(reviewId);
        if(review.isEmpty()){
            throw new InformationNotFoundException("Review with Id " + reviewId + " not found.");
        } else {
            review.get().setComment(reviewObject.getComment());
            review.get().setRating(reviewObject.getRating());
            return reviewRepository.save(review.get());
        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public Review deleteReview(@PathVariable(value = "reviewId") Long reviewId){
        Optional<Review> review = reviewRepository.findById(reviewId);
        if(review.isEmpty()){
            throw new InformationNotFoundException("Review with Id " + reviewId + " not found.");
        } else {
            reviewRepository.deleteById(reviewId);
            return review.get();
        }
    }

}
