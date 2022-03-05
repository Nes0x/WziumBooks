package me.nes0x.author;

import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class AuthorWriteModel {
    @NotBlank(message = "Name must be not empty!")
    private String name;
    @NotBlank(message = "Surname must be not empty!")
    private String surname;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime date;

    public Author toAuthor() {
        var result = new Author();
        result.setName(name);
        result.setSurname(surname);
        result.setDate(date);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(final LocalDateTime date) {
        this.date = date;
    }
}
