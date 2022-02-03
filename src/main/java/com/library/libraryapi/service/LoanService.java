package com.library.libraryapi.service;

import com.library.libraryapi.exceptions.ForbiddenException;
import com.library.libraryapi.exceptions.InformationNotFoundException;
import com.library.libraryapi.exceptions.NotAvailableException;
import com.library.libraryapi.model.Book;
import com.library.libraryapi.model.Loan;
import com.library.libraryapi.repository.BookRepository;
import com.library.libraryapi.repository.LoanRepository;
import com.library.libraryapi.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private LoanRepository loanRepository;

    @Autowired
    public void setLoanRepository(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Loan> getAllLoans(){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getUser().getIsLibrarian()){
            List<Loan> loan = loanRepository.findAll();
            if(loan.isEmpty()){
                throw new InformationNotFoundException("No loans found on the database.");
            } else {
                return loan;
            }
        } else {
            List<Loan> loan = loanRepository.findByUserId(userDetails.getUser().getId());
            if(loan.isEmpty()){
                throw new InformationNotFoundException("No loans found for user id: " + userDetails.getUser().getId());
            } else {
                return loan;
            }
        }

    }

    public Loan loanBook(Long bookId){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty()){
            throw new InformationNotFoundException("Book with id " + bookId + " not found.");
        } else if (book.get().getIsAvailable() == false) {
            throw new NotAvailableException("Book with id " + bookId + " is not available.");
        } else {
            Loan loanObject = new Loan();
            loanObject.setBook(book.get());
            loanObject.setUser(userDetails.getUser());
            loanObject.setBorrowDate(LocalDate.now());
            loanObject.setExpirationDate(loanObject.getBorrowDate().plus(3, ChronoUnit.WEEKS));
            book.get().setIsAvailable(false);
            return loanRepository.save(loanObject);
        }
    }

    public Loan getLoanById(Long loanId){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getUser().getIsLibrarian()){
            Optional<Loan> loan = loanRepository.findById(loanId);
            if(loan.isEmpty()){
                throw new InformationNotFoundException("Loan with id " + loanId + " not found.");
            } else {
                return loan.get();
            }
        } else {
            Loan loan = loanRepository.findByIdAndUserId(loanId, userDetails.getUser().getId());
            if(loan != null){
                return loan;
            } else {
                throw new InformationNotFoundException("Loan with id " + loanId + " and user id " + userDetails.getUser().getId() + " not found.");
            }
        }

    }

    public List<Loan> getLoansByUserId(Long userId){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getUser().getIsLibrarian()){
            List<Loan> loan = loanRepository.findByUserId(userId);
            if(loan.isEmpty()){
                throw new InformationNotFoundException("No loans found for user id: " + userId);
            } else {
                return loan;
            }
        } else if(userDetails.getUser().getId() == userId){
            List<Loan> loan = loanRepository.findByUserId(userId);
            if(loan.isEmpty()){
                throw new InformationNotFoundException("No loans found for user id " + userId);
            } else {
                return loan;
            }
        } else {
            throw new ForbiddenException("User with id " + userDetails.getUser().getId() + " cannot access user id " + userId + " loans.");
        }

    }

    public List<Loan> getLoansByBookId(Long bookId){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getUser().getIsLibrarian()){
            List<Loan> loan = loanRepository.findByBookId(bookId);
            if(loan.isEmpty()){
                throw new InformationNotFoundException("No loans found for book id: " + bookId);
            } else {
                return loan;
            }
        } else {
            List<Loan> loan = loanRepository.findByBookIdAndUserId(bookId, userDetails.getUser().getId());
            if(loan.isEmpty()){
                throw new InformationNotFoundException("No loans found for user id " + userDetails.getUser().getId() + " and book id " + bookId);
            } else {
                return loan;
            }
        }
    }

    public Loan updateLoan(Long loanId, Loan loanObject){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getUser().getIsLibrarian()){
            Optional<Loan> loan = loanRepository.findById(loanId);
            if(loan.isEmpty()){
                throw new InformationNotFoundException("Loan with id: " + loanId + " not found.");
            } else {
                Optional<Book> book = bookRepository.findById(loan.get().getBook().getId());
                loan.get().setBorrowDate(loanObject.getBorrowDate());
                loan.get().setExpirationDate(loanObject.getExpirationDate());
                loan.get().setReturnDate(loanObject.getReturnDate());
                if(loanObject.getReturnDate() != null){
                    book.get().setIsAvailable(true);
                } else {
                    book.get().setIsAvailable(false);
                }
                return loanRepository.save(loan.get());
            }
        } else {
            Loan loan = loanRepository.findByIdAndUserId(loanId, userDetails.getUser().getId());
            if(loan == null){
                throw new InformationNotFoundException("Loan with id: " + loanId + " for user id "+ userDetails.getUser().getId() + " not found.");
            } else {
                Optional<Book> book = bookRepository.findById(loan.getBook().getId());
                loan.setExpirationDate(loanObject.getExpirationDate());
                loan.setReturnDate(loanObject.getReturnDate());
                if(loanObject.getReturnDate() != null){
                    book.get().setIsAvailable(true);
                } else {
                    book.get().setIsAvailable(false);
                }
                return loanRepository.save(loan);
            }
        }

    }

    public Loan deleteLoan(Long loanId){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getUser().getIsLibrarian()){
            Optional<Loan> loan = loanRepository.findById(loanId);
            if(loan.isEmpty()){
                throw new InformationNotFoundException("Book with id: " + loanId + "not found.");
            } else {
                loanRepository.deleteById(loanId);
                return loan.get();
            }
        } else {
            throw new ForbiddenException("Librarian account needed to delete loan.");
        }

    }
}
