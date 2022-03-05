package me.nes0x.comment;

import me.nes0x.book.Book;

public class CommentReadModel {
    private String title;
    private String content;


    public CommentReadModel(Comment source) {
        title = source.getTitle();
        content = source.getContent();
    }

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

}
