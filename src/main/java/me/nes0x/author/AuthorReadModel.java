package me.nes0x.author;

import me.nes0x.book.BookReadModel;
import me.nes0x.comment.CommentReadModel;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorReadModel {
    private int id;
    private String name;
    private LocalDateTime date;
    private Set<BookReadModel> books;
    private Set<CommentReadModel> comments;

    public AuthorReadModel(Author source) {
        id = source.getId();
        name = source.getName();
        date = source.getDate();
        if (source.getBooks() != null) {
            books = source.getBooks().stream().map(BookReadModel::new).collect(Collectors.toSet());
        }
        if (source.getComments() != null) {
            comments = source.getComments().stream().map(CommentReadModel::new).collect(Collectors.toSet());
        }

    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(final LocalDateTime date) {
        this.date = date;
    }

    public Set<BookReadModel> getBooks() {
        return books;
    }

    public void setBooks(final Set<BookReadModel> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Set<CommentReadModel> getComments() {
        return comments;
    }

    public void setComments(final Set<CommentReadModel> comments) {
        this.comments = comments;
    }
}
