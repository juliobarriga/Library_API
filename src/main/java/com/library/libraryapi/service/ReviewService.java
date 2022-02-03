package com.library.libraryapi.service;

import com.library.libraryapi.exceptions.IncompleteInformationException;
import com.library.libraryapi.exceptions.InformationExistException;
import com.library.libraryapi.exceptions.InformationNotFoundException;
import com.library.libraryapi.model.Book;
import com.library.libraryapi.model.Review;
import com.library.libraryapi.repository.BookRepository;
import com.library.libraryapi.repository.ReviewRepository;
import com.library.libraryapi.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Review> getAllReviews(){
        List<Review> review = reviewRepository.findAll();
        if(review.isEmpty()){
            throw new InformationNotFoundException("No reviews found on the database.");
        } else {
            return review;
        }
    }

    public List<Review> getAllReviewsByBook(Long bookId){
        List<Review> review = reviewRepository.findByBookId(bookId);
        if(review.isEmpty()){
            throw new InformationNotFoundException("No reviews found for book with id: " + bookId);
        } else {
            return review;
        }
    }

    public Review addBookReview(Long bookId, Review reviewObject){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Review review = reviewRepository.findByBookIdAndUserId(bookId, userDetails.getUser().getId());
        if(review == null){
            Optional<Book> book = bookRepository.findById(bookId);
            if(book.isEmpty()){
                throw new InformationNotFoundException("Book with id " + bookId + " not found.");
            } else {
                if(reviewObject.getRating() == null){
                    throw new IncompleteInformationException("Review is missing rating.");
                } else if(reviewObject.getRating() > 5 || reviewObject.getRating() < 0){
                    throw new IncompleteInformationException("Rating should be 0,1,2,3,4 or 5.");
                } else{
                    reviewObject.setBook(book.get());
                    reviewObject.setUser(userDetails.getUser());
                    reviewRepository.save(reviewObject);
                    updateBookRating(bookId);
                    return reviewObject;
                }
            }
        } else {
            throw new InformationExistException("User with id " + userDetails.getUser().getId() + " already has reviewed the book with id" + bookId);
        }


    }

    public Review getReviewById(Long reviewId){
        Optional<Review> review = reviewRepository.findById(reviewId);
        if(review.isEmpty()){
            throw new InformationNotFoundException("Review with id " + reviewId + " not found.");
        } else {
            return review.get();
        }
    }

    public List<Review> getAllReviewsByUser(Long userId){
        List<Review> review = reviewRepository.findByUserId(userId);
        if(review.isEmpty()){
            throw new InformationNotFoundException("No reviews found made by user with id: " + userId);
        } else {
            return review;
        }
    }

    public Review updateReview(Long reviewId, Review reviewObject){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Review review = reviewRepository.findByIdAndUserId(reviewId, userDetails.getUser().getId());
        if(review == null){
            throw new InformationNotFoundException("Review with Id " + reviewId + " not found for user with id " + userDetails.getUser().getId());
        } else {
            review.setComment(reviewObject.getComment());
            review.setRating(reviewObject.getRating());
            reviewRepository.save(review);
            updateBookRating(review.getBook().getId());
            return reviewObject;
        }

    }

    public Review deleteReview(Long reviewId){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Review review = reviewRepository.findByIdAndUserId(reviewId, userDetails.getUser().getId());
        if(review == null){
            throw new InformationNotFoundException("Review with Id " + reviewId + " not found for user with id " + userDetails.getUser().getId());
        } else {
            reviewRepository.deleteById(reviewId);
            updateBookRating(review.getBook().getId());
            return review;
        }
    }

    public void updateBookRating(Long bookId){
        Optional<Book> book = bookRepository.findById(bookId);
        double rating = reviewRepository.findByBookId(bookId).stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0);
        book.get().setRating(Integer.valueOf((int)Math.round(rating)));
        bookRepository.save(book.get());
    }
}
