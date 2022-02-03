package com.library.libraryapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column
    private String genre;

    @Column
    private Integer pages;

    @Column
    private String language;

    @Column
    private Integer rating;

    @Column
    private Boolean isAvailable;

    @Column String author;

    public Book() {
    }

    public Book(Long id, String title, String summary, String genre, Integer pages, String language, Integer rating, Boolean isAvailable, String author) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.genre = genre;
        this.pages = pages;
        this.language = language;
        this.rating = rating;
        this.isAvailable = isAvailable;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", genre='" + genre + '\'' +
                ", pages=" + pages +
                ", language='" + language + '\'' +
                ", rating=" + rating +
                ", isAvailable=" + isAvailable +
                ", author=" + author +
                '}';
    }
}
