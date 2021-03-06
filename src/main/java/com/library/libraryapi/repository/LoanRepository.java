package com.library.libraryapi.repository;

import com.library.libraryapi.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserId(Long userId);
    List<Loan> findByBookId(Long bookId);
    Loan findByIdAndUserId(Long loanId, Long userId);
    List<Loan> findByBookIdAndUserId(Long bookId, Long userId);
}
