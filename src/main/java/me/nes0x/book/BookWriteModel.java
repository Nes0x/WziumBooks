package me.nes0x.book;



import me.nes0x.author.Author;



import javax.validation.constraints.NotBlank;

public class BookWriteModel {
    @NotBlank(message = "Title must be not empty!")
    private String title;
    @NotBlank(message = "Description must be not empty!")
    private String description;
    @NotBlank(message = "Image must be not empty!")
    private String image;
    private int stars;
    private Author author;
    private byte[] data;

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

    public byte[] getData() {
        return data;
    }

    public void setData(final byte[] data) {
        this.data = data;
    }

    public Book toBook() {
        return new Book(title, description, image, author, stars, data);
    }
}
