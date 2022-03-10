package me.nes0x.comment;

import me.nes0x.author.Author;

public class CommentReadModel {
    private int id;
    private String content;
    private Author author;


    public CommentReadModel(Comment source) {
        id = source.getId();
        content = source.getContent();
        author = source.getAuthor();
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(final Author author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }
}
