package com.library.libraryapi.model;

import java.util.Date;

public class Loan {

    private Long id;
    private Date borrowDate;
    private Date expirationDate;
    private Date returnDate;
    private Book book;
    private User user;

    public Loan() {
    }

    public Loan(Long id, Date borrowDate, Date expirationDate, Date returnDate) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.expirationDate = expirationDate;
        this.returnDate = returnDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", borrowDate=" + borrowDate +
                ", expirationDate=" + expirationDate +
                ", returnDate=" + returnDate +
                ", book=" + book +
                ", user=" + user +
                '}';
    }
}
