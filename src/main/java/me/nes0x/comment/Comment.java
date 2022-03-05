package me.nes0x.comment;

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
    @NotBlank(message = "Title must be not empty!")
    private String title;
    @NotBlank(message = "Content must be not empty!")
    private String content;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    Comment() {
    }

    Comment(final String title, final String content, final Book book) {
        this.title = title;
        this.content = content;
        this.book = book;
    }

    int getId() {
        return id;
    }

    void setId(final int id) {
        this.id = id;
    }

    String getTitle() {
        return title;
    }

    void setTitle(final String title) {
        this.title = title;
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
}
