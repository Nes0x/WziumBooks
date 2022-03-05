package me.nes0x.comment;

import me.nes0x.book.Book;

import javax.validation.constraints.NotBlank;

public class CommentWriteModel {
    @NotBlank(message = "Title must be not empty!")
    private String title;
    @NotBlank(message = "Description must be not empty!")
    private String content;
    private Book book;

     public String getTitle() {
        return title;
    }

     public void setTitle(final String title) {
        this.title = title;
    }

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

    public Comment toComment() {
        return new Comment(title, content, book);
    }
}
