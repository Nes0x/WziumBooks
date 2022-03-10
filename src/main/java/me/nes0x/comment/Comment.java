package me.nes0x.comment;

import me.nes0x.author.Author;
import me.nes0x.book.Book;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

//TODO dodanie godziny kiedy zostal dodany komentarz
@Entity
@Table(name = "comments")
public class Comment {
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    @Id
    private int id;
    @NotBlank(message = "Content must be not empty!")
    private String content;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    private int stars;

    Comment() {
    }

    Comment(final String content, final Book book, final Author author, final int stars) {
        this.content = content;
        this.book = book;
        this.author = author;
        this.stars = stars;
    }

    int getId() {
        return id;
    }

    void setId(final int id) {
        this.id = id;
    }

    String getContent() {
        return content;
    }

    void setContent(final String content) {
        this.content = content;
    }

    Book getBook() {
        return book;
    }

    void setBook(final Book book) {
        this.book = book;
    }

    Author getAuthor() {
        return author;
    }

    void setAuthor(final Author author) {
        this.author = author;
    }

    int getStars() {
        return stars;
    }

    void setStars(final int stars) {
        this.stars = stars;
    }
}
