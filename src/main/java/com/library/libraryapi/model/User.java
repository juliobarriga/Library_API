package com.library.libraryapi.model;

public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String cardNumber;
    private String address;
    private Integer zipCode;
    private Boolean isLibrarian;

    public User() {
    }

    public User(Long id, String name, String email, String password, String cardNumber, String address, Integer zipCode, Boolean isLibrarian) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.cardNumber = cardNumber;
        this.address = address;
        this.zipCode = zipCode;
        this.isLibrarian = isLibrarian;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public Boolean getLibrarian() {
        return isLibrarian;
    }

    public void setLibrarian(Boolean librarian) {
        isLibrarian = librarian;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", address='" + address + '\'' +
                ", zipCode=" + zipCode +
                ", isLibrarian=" + isLibrarian +
                '}';
    }
}
