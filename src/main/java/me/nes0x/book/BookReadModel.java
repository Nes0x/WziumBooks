package me.nes0x.book;

import me.nes0x.author.Author;

public class BookReadModel {
    private int id;
    private String title;
    private String description;
    private String image;
    private int stars;
    private Author author;

    public BookReadModel(Book source) {
        id = source.getId();
        title = source.getTitle();
        description = source.getDescription();
        image = source.getImage();
        author = source.getAuthor();
        stars = source.getStars();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
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
}