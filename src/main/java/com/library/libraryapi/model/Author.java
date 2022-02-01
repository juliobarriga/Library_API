package com.library.libraryapi.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String about;

    @OneToMany(mappedBy = "author", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Book> bookList;

    public Author() {
    }
    public Author(Long id, String name, String about) {
        this.id = id;
        this.name = name;
        this.about = about;
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
