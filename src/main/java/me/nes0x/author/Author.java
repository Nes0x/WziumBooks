package me.nes0x.author;

import me.nes0x.book.Book;
import org.hibernate.annotations.GenericGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    @Id
    private int id;
    @NotBlank(message = "Name must be not empty!")
    private String name;
    @NotBlank(message = "Surname must be not empty!")
    private String surname;
    private LocalDateTime date;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Set<Book> books;


    Author() {
    }

    public int getId() {
        return id;
    }

    void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    String getSurname() {
        return surname;
    }

    void setSurname(final String surname) {
        this.surname = surname;
    }

    LocalDateTime getDate() {
        return date;
    }

    void setDate(final LocalDateTime date) {
        this.date = date;
    }

    Set<Book> getBooks() {
        return books;
    }

    void setBooks(final Set<Book> books) {
        this.books = books;
    }
}
