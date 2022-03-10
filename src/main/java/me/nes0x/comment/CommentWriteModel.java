package me.nes0x.comment;

import me.nes0x.author.Author;
import me.nes0x.book.Book;

import javax.validation.constraints.NotBlank;

public class CommentWriteModel {
    @NotBlank(message = "Description must be not empty!")
    private String content;
    private Book book;
    private Author author;
    private int stars;

     public String getContent() {
        return content;
    }

     public void setContent(final String content) {
        this.content = content;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(final Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(final Author author) {
        this.author = author;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(final int stars) {
        this.stars = stars;
    }

    public Comment toComment() {
        return new Comment(content, book, author, stars);
    }
}
