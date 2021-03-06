package me.nes0x.book;

import me.nes0x.author.Author;
import me.nes0x.comment.Comment;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;


@Entity
@Table(name = "books")
public class Book {
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    @Id
    private int id;
    @NotBlank(message = "Title must be not empty!")
    private String title;
    @NotBlank(message = "Description must be not empty!")
    private String description;
    @NotBlank(message = "Image must be not empty!")
    private String image;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private Set<Comment> comments;
    @Lob
    private byte[] data;

    Book() {
    }

    Book(String title, String description, String image, Author author, byte[] data) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.author = author;
        this.data = data;
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

    String getDescription() {
        return description;
    }

    void setDescription(final String description) {
        this.description = description;
    }

    String getImage() {
        return image;
    }

    void setImage(final String image) {
        this.image = image;
    }

    Author getAuthor() {
        return author;
    }

    void setAuthor(final Author author) {
        this.author = author;
    }

    Set<Comment> getComments() {
        return comments;
    }

    void setComments(final Set<Comment> comments) {
        this.comments = comments;
    }

    byte[] getData() {
        return data;
    }

    void setData(final byte[] data) {
        this.data = data;
    }
}
