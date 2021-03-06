package me.nes0x.author;


import me.nes0x.book.BookReadModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;


@Controller
@RequestMapping("/authors")
class AuthorController {
    private final AuthorService service;

    AuthorController(final AuthorService service) {
        this.service = service;
    }

    @GetMapping
    String showAllBooks(Model model, @RequestParam(value = "name", defaultValue = "") String name) {
        List<AuthorReadModel> authors;

        if (name.equalsIgnoreCase("")) {
            authors = service.getAllAuthors();
        } else {
            authors = service.getAllAuthorsOfName(name);
        }

        if (authors.isEmpty()) {
            model.addAttribute("message", "Brak znalezionych autorów");
        } else {
            model.addAttribute("authors", authors);
        }

        return "author/authors";
    }

    @GetMapping("/{id}")
    String showBook(@PathVariable int id, Model model) {
        try {
            AuthorReadModel author = service.getAuthorById(id);
            model.addAttribute("author", author);
        } catch (NoSuchElementException exception) {
            model.addAttribute("message", "Autor z id " + id + " nie istnieje!");
            model.addAttribute("title", true);
        }

        return "author/author";
    }

}
